package br.com.laurielcio.producer.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.laurielcio.producer.entity.Usuario;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
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
public class UsuarioDto {

	private Long idUsuario;

	private String nome;

	private String cpf;

	private LocalDate dtNascimento;

	private String email;

	private String telefone;

	private UsuarioStatusEnum status;

	private LocalDate dtCadastro;

	private LocalDate dtAlteracao;

	private EnderecoDto endereco;
	
	public UsuarioDto(Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.dtNascimento = usuario.getDtNascimento();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.status = usuario.getStatus();
		this.dtCadastro = usuario.getDtCadastro();
		this.dtAlteracao = usuario.getDtAlteracao();
		if(usuario.getEndereco() == null) {
			this.endereco = null;
		}else {
			this.endereco = new EnderecoDto(usuario.getEndereco());
		}		
	}
	
	public static List<UsuarioDto> convert(List<Usuario> usuarios){
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

}
