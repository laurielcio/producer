package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */

public class RestTemplateException extends RuntimeException {
	
	private static final long serialVersionUID = 2079798002767315687L;

	public RestTemplateException(String msg) {
		super(msg);
	}
	
	public RestTemplateException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
