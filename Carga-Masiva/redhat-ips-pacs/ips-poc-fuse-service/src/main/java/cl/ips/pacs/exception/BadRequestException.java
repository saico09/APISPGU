package cl.ips.pacs.exception;

/**
 * Esta clase representa el manejo de excepciones particulares del Request  
 * que genera el Bad Request
 * 
 * @author Red Hat
 * @version 1.0
 * @since 1.0
 */
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}
}
