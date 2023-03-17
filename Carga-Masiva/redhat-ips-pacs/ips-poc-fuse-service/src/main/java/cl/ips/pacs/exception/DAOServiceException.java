package cl.ips.pacs.exception;
/**
 * Esta clase representa el manejo de excepciones particulares de flujo para la capa de servicios
 * con esto se podrá reconocer cualquier tipo de excepción dentro de flujos java 
 * 
 * @author Red Hat
 */
public class DAOServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOServiceException(String errorMessage) {
		super(errorMessage);
	}
}