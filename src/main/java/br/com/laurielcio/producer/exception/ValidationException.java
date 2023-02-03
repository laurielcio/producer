package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = -8790376084032311332L;

	public ValidationException(String msg) {
		super(msg);
	}
	
	public ValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
