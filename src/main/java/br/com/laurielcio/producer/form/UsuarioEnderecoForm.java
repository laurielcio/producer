package br.com.laurielcio.producer.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Lau
 *
 */

@Getter
@Setter
public class UsuarioEnderecoForm {
	
	@NotNull(message = "O campo idUsuario não pode ser nullo.")
	private Long idUsuario;	
	
	@NotEmpty(message = "O campo cep não pode ser nullo.")
	@Length(min = 8, max = 8)
	private String cep;
	
	@NotNull(message = "O campo nrResidencia não pode ser nullo.")
	private String nrResidencia;	
	
	private String complemento;

}
