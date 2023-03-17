package co.api.producer.apiproducerreglas.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.api.producer.apiproducerreglas.rowmappers.GestionDelBeneficioRowMapper;
import co.api.producer.apiproducerreglas.vo.GestionDelBeneficio;

@Repository
public class GestionDelBeneficioDAOImpl implements GestionDelBeneficioDAO {

	private static final Logger logger = LoggerFactory.getLogger(GestionDelBeneficioDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	// Masivo
	@Override
	public List<GestionDelBeneficio> listDataGestionDelBeneficio() {
		String sqlGestionDelBeneficio = "select DISTINCT pos.POS_NUM_DOCUMENTO as posNumDocumento,POS.POS_TIP_BOLSA as TIPO_BOLSA, CASE WHEN  HOG.[Numero Documento] IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_HOGARES_CENSO, CASE WHEN  HOG.[Numero Documento] IS NOT NULL then 'true' 	ELSE 'false' END as CONFIRMA_DESASTRE, CASE WHEN DPS.FOLIO IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_FOLIO, CASE WHEN DPS.NUMERODOCUMENTO IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_INFORMACION_DPS, CASE WHEN MHO.mho_num_documento IS NOT NULL then 'true'	ELSE 'false' END AS CONFIMA_SITIO_PROYECTO, CASE WHEN RSS.NUMERODOCUMENTO IS NOT NULL then 'true' ELSE 'false' END as CONFIRMA_SITIO_EXP FROM 	POSTULANTES pos LEFT JOIN INFORMACION_DPS DPS ON pos.POS_NUM_DOCUMENTO = DPS.NUMERODOCUMENTO LEFT JOIN Hogares_Censo HOG ON pos.POS_NUM_DOCUMENTO = HOG.[Numero Documento] LEFT JOIN MIEMBROS_HOGAR MHO ON pos.POS_NUM_DOCUMENTO = mho.mho_num_documento FULL JOIN proyectos pro ON mho.mho_pos_pro_mun_dep_codigo = pro.pro_mun_dep_codigo	AND	mho.mho_pos_pro_mun_codigo = pro.pro_mun_codigo AND mho.mho_pos_pro_tipo = pro.pro_tipo		AND mho.mho_pos_pro_anio = pro.pro_anio AND mho.mho_pos_pro_consecutivo = pro.pro_consecutivo LEFT JOIN  rss_consolidada rss ON pos.POS_NUM_DOCUMENTO = rss.NUMERODOCUMENTO"; 
		long init = System.currentTimeMillis();
		List<GestionDelBeneficio> list = jdbcTemplateObject.query(sqlGestionDelBeneficio, new GestionDelBeneficioRowMapper());
		logger.info("Tiempo en obtener Lista de Años en Base de Datos : {} ms.", (System.currentTimeMillis() - init));
		return list;
	}

	// En Linea
	@Override
	public List<GestionDelBeneficio> listDataGestionDelBeneficioEnLinea(Integer numDocumento) {
		String sqlGestionDelBeneficio = "select DISTINCT pos.POS_NUM_DOCUMENTO as posNumDocumento,POS.POS_TIP_BOLSA as TIPO_BOLSA, CASE WHEN  HOG.[Numero Documento] IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_HOGARES_CENSO, CASE WHEN  HOG.[Numero Documento] IS NOT NULL then 'true'	ELSE 'false' END as CONFIRMA_DESASTRE, CASE WHEN DPS.FOLIO IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_FOLIO, CASE WHEN DPS.NUMERODOCUMENTO IS NOT NULL then 'true' ELSE 'false' END AS CONFIRMA_INFORMACION_DPS, CASE WHEN MHO.mho_num_documento IS NOT NULL then 'true' ELSE 'false' END AS CONFIMA_SITIO_PROYECTO, CASE WHEN RSS.NUMERODOCUMENTO IS NOT NULL then 'true' ELSE 'false' END as CONFIRMA_SITIO_EXP FROM 	POSTULANTES pos LEFT JOIN INFORMACION_DPS DPS ON pos.POS_NUM_DOCUMENTO = DPS.NUMERODOCUMENTO LEFT JOIN Hogares_Censo HOG ON pos.POS_NUM_DOCUMENTO = HOG.[Numero Documento] LEFT JOIN MIEMBROS_HOGAR MHO	ON pos.POS_NUM_DOCUMENTO = mho.mho_num_documento FULL JOIN proyectos pro ON mho.mho_pos_pro_mun_dep_codigo = pro.pro_mun_dep_codigo	AND	mho.mho_pos_pro_mun_codigo = pro.pro_mun_codigo	AND mho.mho_pos_pro_tipo = pro.pro_tipo		AND mho.mho_pos_pro_anio = pro.pro_anio	AND mho.mho_pos_pro_consecutivo = pro.pro_consecutivo LEFT JOIN  rss_consolidada rss ON pos.POS_NUM_DOCUMENTO = rss.NUMERODOCUMENTO WHERE pos.POS_NUM_DOCUMENTO ="+numDocumento;
		long init = System.currentTimeMillis();
		List<GestionDelBeneficio> list = jdbcTemplateObject.query(sqlGestionDelBeneficio, new GestionDelBeneficioRowMapper());
		logger.info("Tiempo en obtener Lista de Años en Base de Datos : {} ms.", (System.currentTimeMillis() - init));
		return list;
	}
}
