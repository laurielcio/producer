package br.com.laurielcio.producer.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.laurielcio.producer.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Lau
 *
 */

@Getter
@Setter
public class UsuarioForm {
	
	@NotEmpty(message = "O campo nome não pode ser nullo.")
	@Length(min = 6, max = 100)
	private String nome;	
	
	@NotEmpty(message = "O campo cpf não pode ser nullo.")
	@Length(min = 11, max = 11)
	private String cpf;
	
	@NotNull(message = "O campo dtNascimento não pode ser nullo.")
	private LocalDate dtNascimento;	
	
	@NotEmpty(message = "O campo email não pode ser nullo.")
	private String email;
	
	@NotEmpty(message = "O campo telefone não pode ser nullo.")
	@Length(min = 10, max = 11)
	private String telefone;
	
	@NotEmpty(message = "O campo cep não pode ser nullo.")
	@Length(min = 8, max = 8)
	private String cep;
	
	@NotEmpty(message = "O campo nrResidencia não pode ser nullo.")
	private String nrResidencia;
	
	private String complementoResidencia;

	public Usuario convert() {
		return new Usuario(nome, cpf, dtNascimento, email, telefone);
	}

}
