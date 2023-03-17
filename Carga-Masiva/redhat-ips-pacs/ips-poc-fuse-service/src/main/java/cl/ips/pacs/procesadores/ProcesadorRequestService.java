package cl.ips.pacs.procesadores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import org.kie.internal.io.ResourceFactory;

import cl.ips.pacs.utils.JdbcSQLServerConnection;
import cl.ips.pacs.vo.DatosBeneficiarioPacsFijoVector;
import cl.ips.pacs.vo.ElegibilidadNewRequest;
import cl.ips.pacs.vo.EvaluaPACSFijoRequest;

public class ProcesadorRequestService implements Processor {

	private static final Logger logger = Logger.getLogger(ProcesadorRequestService.class);

	@Override
	public void process(Exchange exchange)  {
		try {
			long init = System.currentTimeMillis();
			executeDrools();
			System.out.println("Ejecución total en: " + (System.currentTimeMillis() - init) + " ms.");
		} catch (DroolsParserException | IOException e) {
			e.printStackTrace();
		}
	}

	private static final String[] drlFiles = { "Rules.drl" };

	public KieContainer kieContainer() {
		KieServices kieServices = KieServices.Factory.get();
		// Load Rules and Ecosystem Definitions
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		for (String ruleFile : drlFiles) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFile));
		}
		// Generate Modules and all internal Structures
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		KieModule kieModule = kieBuilder.getKieModule();
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}

	public void executeDrools() throws DroolsParserException, IOException {

		KieSession kieSession = kieContainer().newKieSession();
		List<EvaluaPACSFijoRequest> listProduct = new ArrayList<EvaluaPACSFijoRequest>();
		retornaListaEvaluarDataBase(listProduct, kieSession);

		long init = System.currentTimeMillis();
		kieSession.fireAllRules();
		kieSession.dispose();
		System.out.println("Ejecución Reglas en: " + (System.currentTimeMillis() - init) + " ms.");
		
//		System.out.println("listProduct: "+listProduct.toString());

//		actualizaListaEvaluarDataBase(listProduct);
	}

//	Lectura de 1316104 registros en: 235953 ms.
//	Reglas ejecutadas en 603 ms.
	public List<EvaluaPACSFijoRequest> retornaListaEvaluarDataBase(List<EvaluaPACSFijoRequest> listProduct, KieSession kieSession) {
		long init = System.currentTimeMillis();

		try {
			Connection conn = JdbcSQLServerConnection.getConnection();
			ResultSet rs = conn.createStatement().executeQuery("select TOP 10 * from tblPACSElegibilidad ORDER BY iRUN DESC");
			
			while (rs.next()) {
//				DatosBeneficiarioPacsFijoVector datosBeneficiarioPacsFijoVector = new DatosBeneficiarioPacsFijoVector();
//				datosBeneficiarioPacsFijoVector.setFechaDefuncion("1900-01-01");
//				datosBeneficiarioPacsFijoVector.setEdad(rs.getInt("iEdad"));
//				datosBeneficiarioPacsFijoVector.setRun(String.valueOf(rs.getInt("iRUN")));
//				
//				kieSession.insert(datosBeneficiarioPacsFijoVector);
//				listProduct.add(datosBeneficiarioPacsFijoVector);
				
				

				
//				ElegibilidadNewRequest elegibilidadNewRequest = iteradorListaElegibilidadRequest.next();
				EvaluaPACSFijoRequest evaluaPACSFijoRequest = new EvaluaPACSFijoRequest();
				
				evaluaPACSFijoRequest.setRun(String.valueOf(String.valueOf(rs.getInt("iRUN"))));
				evaluaPACSFijoRequest.setEdad(String.valueOf(rs.getInt("iEdad")));
				evaluaPACSFijoRequest.setBeneficiarioPACS(String.valueOf(rs.getInt("iBeneficiarioPACSFijo")));
				evaluaPACSFijoRequest.setFechaDefuncion(rs.getString("dFechaDefuncion"));
				
				//TODO  (1) no es pensionado - (0) es pensionado
				evaluaPACSFijoRequest.setPensionado(String.valueOf(rs.getInt("iInformadoPCPVID")));
				
				//TODO montoInconsistencia = 10, entonces presenta inconsistencia
				evaluaPACSFijoRequest.setMontoInconsistencia(String.valueOf(rs.getInt("iMontoInconsistencia")));
				evaluaPACSFijoRequest.setPensionUF(String.valueOf(rs.getInt("nPensionTotalUF")));
				evaluaPACSFijoRequest.setCotizacionesExigidas(String.valueOf(rs.getInt("iNumeroCotizacionesPACS"))); //TODO: confirmar
				
				//TODO cotizacionesPoseeAFPDiCa = cotizacionesAFP + cotizacionesDIPRECA + cotizacionesCAPREDENA
				evaluaPACSFijoRequest.setCotizacionesPoseeAFPDiCa(String.valueOf(rs.getInt("iCotizacionesAFP")
						+ rs.getInt("iCotizacionesDIPRECA") + rs.getInt("iCotizacionesCAPREDENA")));
				
				//TODO 1 en caso de inconsistente y 0 en caso de no inconsistente
				//Si tiene BonoReconocimient de IPS (valor 1) y las CotizacionesIPS son 0 es inconsistencia
				if ((Integer.valueOf(rs.getInt("cBonoReconocimientoIPS")) == 1) && (rs.getInt("iCotizacionesIPS") == 0) ) {
					// valor 1 para inconsistente
					evaluaPACSFijoRequest.setInconsistenciaIPS("1");
				} else {
					// valor 0 para no inconsistente
					evaluaPACSFijoRequest.setInconsistenciaIPS("0");
				}
				
				//TODO campo TotalPensiones debe ser entero
				evaluaPACSFijoRequest.setCotizacionesPoseeTotal(String.valueOf(Math.round(rs.getInt("nTotalPensiones"))));
				
				listProduct.add(evaluaPACSFijoRequest);
				
			}	
			
			
			
		
			
			
			
			
//			while (rs.next()) {
//				Product product = new Product();
//				product.setType("gold");
//				product.setiRUN(rs.getInt("iRUN"));
//				product.setvApellidoPaterno(rs.getString("vcApellidoPaterno"));
//				kieSession.insert(product);
//				listProduct.add(product);
//			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Lectura de " + listProduct.size() + " registros en: " + (System.currentTimeMillis() - init) + " ms.");
		return listProduct;
	}

	public List<EvaluaPACSFijoRequest> actualizaListaEvaluarDataBase(List<EvaluaPACSFijoRequest> listProduct) {
		long init = System.currentTimeMillis();

		try {
			Connection conn = JdbcSQLServerConnection.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement prepStmt = conn
					.prepareStatement("UPDATE tblPACSElegibilidad SET iEdad=? WHERE iRUN=?");

			for (EvaluaPACSFijoRequest evaluaPACSFijoRequest : listProduct) {
				prepStmt.setString(1, evaluaPACSFijoRequest.getEdad());
				prepStmt.setInt(2, Integer.parseInt(evaluaPACSFijoRequest.getRun()));
				prepStmt.addBatch();
			}
			prepStmt.executeBatch();
			

			
			conn.commit();
			prepStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Actualiza de " + listProduct.size() + " registros en: "
				+ (System.currentTimeMillis() - init) + " ms.");
		return listProduct;
	}

}
