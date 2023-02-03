package br.com.laurielcio.producer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Lau
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage {
	
	private String fieldName;

	private String message;

}
