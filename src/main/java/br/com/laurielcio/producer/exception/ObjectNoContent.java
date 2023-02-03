package br.com.laurielcio.producer.exception;

/**
 * 
 * @author Lau
 *
 */

public class ObjectNoContent extends RuntimeException{

	private static final long serialVersionUID = 1327036249750493143L;

	public ObjectNoContent(String msg) {
		super(msg);
	}
	
	public ObjectNoContent(String msg, Throwable cause) {
		super(msg, cause);
	}

}
