package br.com.laurielcio.producer.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Lau
 *
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 5903237391089922567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long idUsuario;
	
	@Column(name = "NOME", nullable = false)
	private String nome;	
	
	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private LocalDate dtNascimento;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "TELEFONE", nullable = false)
	private String telefone;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private UsuarioStatusEnum status;
	
	@Column(name = "DT_CADASTRO", nullable = false)
	private LocalDate dtCadastro;
	
	@Column(name = "DT_Alteracao")
	private LocalDate dtAlteracao;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ID")
    private Endereco endereco;
	
	public Usuario(String nome, String cpf, LocalDate dtNascimento, String email, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.email = email;
		this.telefone = telefone;
	}	

}
