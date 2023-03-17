package cl.ips.pacs.exception;

/**
 * Esta clase representa el manejo de excepciones particulares de flujo para la capa de servicios
 * con esto se podrá reconocer cualquier tipo de excepción dentro de flujos java 
 * 
 * @author Red Hat
 */
public class RuntimeServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuntimeServiceException(String errorMessage) {
		super(errorMessage);
	}
}