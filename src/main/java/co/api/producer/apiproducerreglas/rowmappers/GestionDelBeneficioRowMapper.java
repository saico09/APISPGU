package co.api.producer.apiproducerreglas.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import co.api.producer.apiproducerreglas.vo.GestionDelBeneficio;

public class GestionDelBeneficioRowMapper implements RowMapper<GestionDelBeneficio> {

	/* VA
	private Boolean informacionDPS;
	private Boolean infoHogaresCenso;

	// CP
	private Boolean condUnidos;
	private Boolean condDesastres;
	private Boolean sitioProyecto;
	private Boolean sitioExpulsion;

	// VARS
	private Integer posNumDocumento;
	private String tipoBolsa;
	private Integer folio;
	private Integer consecutivo;
	private Integer expulsion;*/
	
	private String run;
	private Integer tipoBeneficio;
	private Integer beneficioVigente;
	private Integer fechaActual;
	private Integer estadoPostulacion;
	private Integer edadMeses;
	private String derechoPension;
	private Integer fechaNacimiento;
	private Integer codDipreca;
	private Integer codCapredena;
	private Boolean rshVigente;
	private Integer garantiaEstatal;
	private Integer pgu;
	private Integer pensionGracia;
	private Integer leyesEspeciales;
	private Integer pensionBase;
	private Integer pensionSuperior;
	private Boolean residencia;
	private Boolean tiempoEstadia;
	private Integer fechaDefuncion;

	@Override
	public GestionDelBeneficio mapRow(ResultSet rs, int rowNum) throws SQLException {

		run = "25";
		tipoBeneficio = null;
		beneficioVigente=null;
		fechaActual = 20240220;
		estadoPostulacion = 0;
		edadMeses = 778;
		derechoPension = "";
		fechaNacimiento = 19300220;
		codDipreca = 20;
		codCapredena = 20;
		rshVigente = true;
		garantiaEstatal = 0;
		pgu = 250000;
		pensionGracia = 0;
		leyesEspeciales = 0;
		pensionBase = 250000;
		pensionSuperior = 300000;
		residencia = true;
		tiempoEstadia = false;
		fechaDefuncion = null;
		/* int to Integer
		posNumDocumento = rs.getInt("posNumDocumento");
		tipoBolsa = rs.getString("TIPO_BOLSA");
		infoHogaresCenso = rs.getBoolean("CONFIRMA_HOGARES_CENSO");
		condDesastres = rs.getBoolean("CONFIRMA_DESASTRE");
		condUnidos = rs.getBoolean("CONFIRMA_FOLIO");
		informacionDPS = rs.getBoolean("CONFIRMA_INFORMACION_DPS");
		sitioProyecto = rs.getBoolean("CONFIMA_SITIO_PROYECTO");
		sitioExpulsion = rs.getBoolean("CONFIRMA_SITIO_EXP");*/

		return new GestionDelBeneficio(run, tipoBeneficio, beneficioVigente, fechaActual, estadoPostulacion,
		edadMeses ,derechoPension ,fechaNacimiento ,codDipreca ,codCapredena ,rshVigente ,garantiaEstatal,
		garantiaEstatal ,pgu ,pensionGracia ,leyesEspeciales ,pensionBase ,pensionSuperior ,residencia,
		tiempoEstadia ,tiempoEstadia,fechaDefuncion);

		/* return new GestionDelSubsidio(posNumDocumento, tipoBolsa, infoHogaresCenso, informacionDPS, condUnidos,
				condDesastres, sitioProyecto, sitioExpulsion, 0);*/
	}

}
