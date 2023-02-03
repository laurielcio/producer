package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */

public class ConvertException extends RuntimeException{

	private static final long serialVersionUID = 2576371606055188117L;

	public ConvertException(String msg) {
		super(msg);
	}
	
	public ConvertException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
