package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */

public class DatabaseAccessException extends RuntimeException {
	
	private static final long serialVersionUID = 7737318935963336486L;

	public DatabaseAccessException(String msg) {
		super(msg);
	}
	
	public DatabaseAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
