package cl.ips.pacs.procesadores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;

import cl.ips.pacs.BeneficiarioCantidadCotizaciones;
import cl.ips.pacs.BeneficiarioCodTipoElegibilidad;
import cl.ips.pacs.BeneficiarioVectorFijo;
import cl.ips.pacs.BeneficiarioVectorVariable;
import cl.ips.pacs.utils.JdbcSQLServerConnection;
import org.apache.camel.PropertyInject;

/**
* Clase procesadora principal que genera la llamada a base de datos, ejecución de reglas
* y actualización de los registros sobre base de datos correspondiente.
*
* @author  Red Hat
* @version 1.0
* @since   2020-12-15 
**/

public class ProcesadorRequestService implements Processor {

	private static final Logger logger = Logger.getLogger(ProcesadorRequestService.class);
	
	//TODO: Verificar cual de las listas no se ocupa y eliminarla.
	private List<BeneficiarioVectorVariable> listBeneficiarioVectorVariable;
	private List<BeneficiarioVectorFijo> listBeneficiarioVectorFijo;
	private List<BeneficiarioCantidadCotizaciones> listBeneficiarioCantidadCotizaciones;
	private List<BeneficiarioCodTipoElegibilidad> listBeneficiarioCodTipoElegibilidad;
	
	
	//Properties querys
	@PropertyInject(value = "obtenerDatosBeneficiarios")
	String obtDatosBeneficiarios;
	@PropertyInject(value = "obtienePeriodo")
	String obtienePeriodo;	
	@PropertyInject(value = "obtieneFechaVigencia")
	String obtieneFechaVigencia;
	@PropertyInject(value = "obtieneValorUF")
	String obtieneValorUF;	
	@PropertyInject(value = "actualizaBeneficiarios")
	String actualizaBeneficiarios;		

	//Properties conexion Base de Datos
	@PropertyInject(value = "dbURL")
	String dbURLBD;
	@PropertyInject(value = "user")
	String userBD;
	@PropertyInject(value = "pass")
	String passBD;
	
	
	private static final String[] drlFiles = { 
			"elegibilidad-variable.drl", 
			"elegibilidad-fija.drl", 
			"cantidad-cotizaciones.drl", 
			"codigo-tipo-elegibilidad.drl" 
			};

	/**
	  * Este método corresponde al método principal que es llamado
	  * desde la ruta camel que expone el servicio de arranque
	  * @param exchange este parámetro contiene todo el contexto de la petición camel
	  * generada por la llamada principal del servicio
	**/	
	
	@Override
	public void process(Exchange exchange)  {
		try {
			listBeneficiarioVectorVariable = new ArrayList<BeneficiarioVectorVariable>();
			listBeneficiarioVectorFijo = new ArrayList<BeneficiarioVectorFijo>();
			listBeneficiarioCantidadCotizaciones = new ArrayList<BeneficiarioCantidadCotizaciones>();
			listBeneficiarioCodTipoElegibilidad = new ArrayList<BeneficiarioCodTipoElegibilidad>();
			
			long init = System.currentTimeMillis();
			executeDrools();
			logger.info("Ejecución total en: " + (System.currentTimeMillis() - init) + " ms.");
			
			listBeneficiarioVectorVariable.clear();
			listBeneficiarioVectorFijo.clear();
			listBeneficiarioCantidadCotizaciones.clear();
			listBeneficiarioCodTipoElegibilidad.clear();
			
			//liberación forzada de la garbage collector para efecto de rendimiento de memoria.
			System.gc();
		} catch (DroolsParserException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método inicializadorr del contexto KieServer quien se encargará
	 * de ejecutar las reglas y gestionar la sesión de llamada de los recurso
	 * @return KieContainer instancia del contenedor kieserver
	**/	
	
	//TODO: En este metodo se hace la conexion al DM
	public KieContainer kieContainer() {
		KieServices kieServices = KieServices.Factory.get();
		
		// Carga las reglas dentro de las definiciones del ecosistema
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		for (String ruleFile : drlFiles) { //Aqui se cargan los DRL
			kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFile));
		}
		
		// Genera módulo y todas las estructuras internas
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();//Hasta esta linea cambia se genera un kieModule
		
		KieModule kieModule = kieBuilder.getKieModule();
		
		// Genera el retorno de la instancia
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}
	
	/**
	 * Método de ejecución de las reglas y actualización 
	 * independiente.
	 * @return KieContainer instancia del contenedor kieserver
	**/	
	
	public void executeDrools() throws DroolsParserException, IOException {

		// Cantidad de cotizaciones como lista de resultados a ser actualizada
		// sobre la base de datos
		KieSession kieSession = kieContainer().newKieSession();
		getBeneficiarioCantidadCotizaciones(kieSession);
		
		// Método que genera la actualización masiva
		actualizaListaEvaluarDataBase(listBeneficiarioCodTipoElegibilidad);
	}
	
	/**
	 * Método que genera la carga de resultados y la generación del objeto 
	 * de tipo lista el cual ejecutará reglas como además será utilizado
	 * para la actualización masiva
	 * @param kieSession sesión de kieserver inicializada
	**/	
	
	public void getBeneficiarioCantidadCotizaciones(KieSession kieSession) {
		long init = System.currentTimeMillis();

		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcSQLServerConnection.getConnection(dbURLBD, userBD, passBD);

		    // Obtiene Ultimo Periodo
	        ResultSet rsPeriodo = conn.createStatement().executeQuery(obtienePeriodo);
	        String periodo = "";
	        if (rsPeriodo.next())
	          periodo = rsPeriodo.getString(1);
			
	        rsPeriodo.close();
			
	        logger.info("Periodo::::: " + periodo);
	        
	        // Obtiene Fecha Vigencia
			//ResultSet rsFechaVigencia = conn.createStatement().executeQuery("select convert(datetime,vcValor) from dbAdministracion.dbo.tblADMParametro\n" + 
					//"where vcCodigoAplicacion = 'apli00000003'\n" + 
					//"and vcParametro = 'FECHAINICIOVIGENCIA'");
			ResultSet rsFechaVigencia = conn.createStatement().executeQuery(obtieneFechaVigencia);	
			String fechaVigencia = "";
			if (rsFechaVigencia.next())
				fechaVigencia = rsFechaVigencia.getString(1).split(" ")[0];
			rsFechaVigencia.close();
			
			// Obtiene Valor UF
	        //ResultSet rsUF = conn.createStatement().executeQuery("select nMontoFactor = nMontoFactor from dbAdministracion.dbo.tblADMIndicadorEconomico WHERE iCodigoFactor = 1 and dFechaMontoIndicador = convert(datetime,concat(convert(varchar,202005),'09'),103);");
	        ResultSet rsUF = conn.createStatement().executeQuery(obtieneValorUF + periodo +"),'09'),103);");
	        float valorUF = 0;
	        if (rsUF.next())
	          valorUF = rsUF.getFloat(1);
	        rsUF.close();

	        logger.info("VALOR UF: " + valorUF);
	       
	        String SQL = obtDatosBeneficiarios;
								
			logger.info("SQL: "+SQL);
			
			rs = conn.createStatement().executeQuery(SQL);
			
			while (rs.next()) {
				
				//float nMontoPensionPagadaRP = rs.getFloat("nMontoIngresoUF") + rs.getFloat("nMontoGEPensionMinimaPesos")/valorUF;
				//float nMontoPensionPagadaRV = rs.getFloat("nMontoIngresoUF") + rs.getFloat("nMontoGEPensionMinimaPesos")/valorUF + rs.getFloat("nMontoGEQuiebraPesos")/valorUF;
				//float nMontoPensionMutual = rs.getFloat("nMontoIngresoPesos");
				//float nMontoTotal = nMontoPensionPagadaRP + nMontoPensionPagadaRV + nMontoPensionMutual/valorUF;
				
				//Objeto cantidad de cotizaciones
				BeneficiarioCantidadCotizaciones beneficiarioCantidadCotizaciones = new BeneficiarioCantidadCotizaciones();
				beneficiarioCantidadCotizaciones.setRun(rs.getString("iRUN"));
				beneficiarioCantidadCotizaciones.setFechaPension(rs.getString("dFechaPension"));
				beneficiarioCantidadCotizaciones.setSexo(rs.getString("cSexo"));
				FactHandle handleCantidadCotizacion = kieSession.insert(beneficiarioCantidadCotizaciones);
				kieSession.fireAllRules();
				kieSession.delete(handleCantidadCotizacion);
				
				//Objeto Variable
				BeneficiarioVectorVariable beneficiarioVectorVariable = new BeneficiarioVectorVariable();
				beneficiarioVectorVariable.setFechaDefuncion(rs.getString("dFechaDefuncion").split(" ")[0]);
				beneficiarioVectorVariable.setBeneficiarioPACS(rs.getInt("ibeneficiarioPacsVariable")); //-> sql.spPACSBeneficiarioVariable (OK)
				beneficiarioVectorVariable.setEdad(rs.getInt("iEdad"));
				beneficiarioVectorVariable.setRun(rs.getString("iRUN"));
				//beneficiarioVectorVariable.setPensionado(rs.getInt("nTotalPensiones")); 
				beneficiarioVectorVariable.setMontoInconsistencia(rs.getInt("iMontoInconsistencia"));
				beneficiarioVectorVariable.setPensionUF(rs.getInt("nPensionTotalUF")); //TODO -> sql.spPACSCumpleMonto
				//beneficiarioVectorVariable.setPensionUF(nMontoTotal); // nMontoTotalUF - nPensionTotalUF
				beneficiarioVectorVariable.setFechaPension(rs.getString("dFechaPension").split(" ")[0]);
				beneficiarioVectorVariable.setPoseeCotizacionesPACS(rs.getInt("cotizaPACS")); //sql.spPACSCotizaPACS (OK)
				beneficiarioVectorVariable.setFechaInicioVigenciaPACS(fechaVigencia);  //sql.spPACSTraeFechaInicioVigenciaPACS (OK)
				beneficiarioVectorVariable.setVcEntidadPagadora(rs.getString("vcEntidadPagadora"));
				//
				FactHandle handleVariable = kieSession.insert(beneficiarioVectorVariable);
				kieSession.fireAllRules();
				kieSession.delete(handleVariable);
				
				//Objeto Fijo
				BeneficiarioVectorFijo beneficiarioVectorFijo = new BeneficiarioVectorFijo();
				
				beneficiarioVectorFijo.setRun(beneficiarioCantidadCotizaciones.getRun());
				beneficiarioVectorFijo.setFechaDefuncion(beneficiarioVectorVariable.getFechaDefuncion());
				beneficiarioVectorFijo.setEdad(beneficiarioVectorVariable.getEdad());
				beneficiarioVectorFijo.setMontoInconsistencia(beneficiarioVectorVariable.getMontoInconsistencia());
				beneficiarioVectorFijo.setPensionUF(beneficiarioVectorVariable.getPensionUF());
				beneficiarioVectorFijo.setCotizacionesExigidas(beneficiarioCantidadCotizaciones.getCantidadCotizaciones()); 
				beneficiarioVectorFijo.setInconsistenciaIPS(rs.getInt("inconsistenciaIPS")); // sql.spPACSInconsistenciaIPS
				beneficiarioVectorFijo.setBeneficiarioPACS(rs.getInt("ibeneficiarioPacsFijo")); // sql.spPACSBeneficiarioFijo
				beneficiarioVectorFijo.setSumatoriaCotizaciones(rs.getInt("nTotalPensiones"));
				beneficiarioVectorFijo.setBonoReconocimientoDIPRECA(rs.getInt("cBonoReconocimientoDIPRECA"));
				beneficiarioVectorFijo.setBonoReconocimientoCAPREDENA(rs.getInt("cBonoReconocimientoCAPREDENA"));
				//beneficiarioVectorFijo.setVcEntidadPagadora(rs.getString("vcEntidadPagadora"));
				beneficiarioVectorFijo.setVcEntidadPagadora(beneficiarioVectorVariable.getVcEntidadPagadora());
				beneficiarioVectorFijo.setNumeroCotzCapredena(rs.getInt("iNumeroMesesCotizadosCapredena"));
				beneficiarioVectorFijo.setNumeroCotzDipreca(rs.getInt("iNumeroMesesCotizadosDipreca"));
				beneficiarioVectorFijo.setBonoRecIPSAlternativa(rs.getInt("iBonoReconocimientoIPSTipoAlternativa"));
				//
				FactHandle handleFijo = kieSession.insert(beneficiarioVectorFijo);
				kieSession.fireAllRules();
				kieSession.delete(handleFijo);
				
				//Objeto Tipo Codigo
				BeneficiarioCodTipoElegibilidad beneficiarioCodTipoElegibilidad = new BeneficiarioCodTipoElegibilidad();
				beneficiarioCodTipoElegibilidad.setRun(beneficiarioVectorFijo.getRun());
				beneficiarioCodTipoElegibilidad.setVectorPACSFijo(beneficiarioVectorFijo.getVector());
				beneficiarioCodTipoElegibilidad.setVectorPACSVariable(beneficiarioVectorVariable.getVector());
				FactHandle handleCodTipoElegibilidad = kieSession.insert(beneficiarioCodTipoElegibilidad);
				kieSession.fireAllRules();
				kieSession.delete(handleCodTipoElegibilidad);
				listBeneficiarioCodTipoElegibilidad.add(beneficiarioCodTipoElegibilidad);
				
				//logger.info("beneficiarioCantidadCotizaciones: \n"+beneficiarioCantidadCotizaciones.toString());
				//logger.info("beneficiarioVectorFijo: \n"+beneficiarioVectorFijo.toString());
				//logger.info("beneficiarioVectorVariable: \n"+beneficiarioVectorVariable.toString());
				//logger.info("beneficiarioCodTipoElegibilidad: \n"+beneficiarioCodTipoElegibilidad.toString());

			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null)	try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
			
		logger.info("listBeneficiarioCodTipoElegibilidad: " + listBeneficiarioCodTipoElegibilidad.size() + " registros en: " + (System.currentTimeMillis() - init) + " ms.");
	}
	
	/**
	 * Método que se encarga de recibir una lista con todos los objetos
	 * actualizados con las reglas ejecutadas para luego
	 * actualizar tablas correspondientes.
	 * @param kieSession sesión de kieserver inicializada
	**/
	
	public void actualizaListaEvaluarDataBase(List<BeneficiarioCodTipoElegibilidad> listBeneficiarioCodTipoElegibilidad) {
		long init = System.currentTimeMillis();

		Connection conn = null;
		try {
			conn =JdbcSQLServerConnection.getConnection(dbURLBD, userBD, passBD);
			conn.setAutoCommit(false);

			PreparedStatement prepStmt = conn
					.prepareStatement(actualizaBeneficiarios);
			//.prepareStatement("UPDATE tblPACSElegibilidad SET dFechaActualizacion = CURRENT_TIMESTAMP, "
					//+ "iCodigoTipoElegibilidad = ?, "
					//+ "vcVectorPACSBFijo=?, "
					//+ "vcVectorPACSBVariable=? WHERE iRUN=?");
			
			// Iteración donde se ejecuta el registro de las reglas en base de datos
			// a través de un batch
			for (BeneficiarioCodTipoElegibilidad e : listBeneficiarioCodTipoElegibilidad) {
				prepStmt.setInt(1, e.getCodigoTipoElegibilidad());
				prepStmt.setString(2, e.getVectorPACSFijo().stream().map(Object::toString).collect(Collectors.joining(",")));
				prepStmt.setString(3, e.getVectorPACSVariable().stream().map(Object::toString).collect(Collectors.joining(",")));
				prepStmt.setString(4, e.getRun());
				prepStmt.addBatch();
			}
			prepStmt.executeBatch();
			
			conn.commit();
			prepStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Actualiza de " + listBeneficiarioCodTipoElegibilidad.size() + " registros en: "
				+ (System.currentTimeMillis() - init) + " ms.");
	}

}
