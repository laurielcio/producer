package br.com.laurielcio.producer.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.kafka.common.KafkaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Lau
 *
 */

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError standardError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", System.currentTimeMillis());
		
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			standardError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(DatabaseAccessException.class)
	public ResponseEntity<StandardError> dataAccess(DatabaseAccessException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
	}
	
	@ExceptionHandler(ObjectNoContent.class)
	public ResponseEntity<StandardError> objectNoContent(ObjectNoContent e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.NO_CONTENT.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(standardError);
	}
	
	@ExceptionHandler(ConvertException.class)
	public ResponseEntity<StandardError> convertException(ConvertException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
	}
	
	@ExceptionHandler(CPFValidationException.class)
	public ResponseEntity<StandardError> cpfValidationException(CPFValidationException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(RestTemplateException.class)
	public ResponseEntity<StandardError> restTemplateException(RestTemplateException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.BAD_GATEWAY.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(standardError);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<StandardError> validationException(ValidationException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(KafkaException.class)
	public ResponseEntity<StandardError> kafkaException(KafkaException e, HttpServletRequest request){
		StandardError standardError = new StandardError(HttpStatus.BAD_GATEWAY.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(standardError);
	}

}
