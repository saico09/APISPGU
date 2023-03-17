package co.api.producer.apiproducerreglas.dao;

import java.util.List;
import co.api.producer.apiproducerreglas.vo.GestionDelBeneficio;

public interface GestionDelBeneficioDAO {

	List<GestionDelBeneficio> listDataGestionDelBeneficio();

	List<GestionDelBeneficio> listDataGestionDelBeneficioEnLinea(Integer numDocumento);

}
