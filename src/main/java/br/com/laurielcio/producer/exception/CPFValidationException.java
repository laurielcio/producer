package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */
public class CPFValidationException extends RuntimeException{

	
	private static final long serialVersionUID = 1838560098316859219L;

	public CPFValidationException(String msg) {
		super(msg);
	}
	
	public CPFValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
