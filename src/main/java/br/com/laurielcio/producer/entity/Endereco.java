package br.com.laurielcio.producer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "ENDERECO")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long idEndereco;
	
	@Column(name = "CEP", nullable = false)
	private String cep;	
	
	@Column(name = "LOGRADOURO", nullable = false)
	private String logradouro;
	
	@Column(name = "NUMERO", nullable = false)
	private String numero;
	
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Column(name = "BAIRRO", nullable = false)
	private String bairro;
	
	@Column(name = "LOCALIDADE", nullable = false)
	private String localidade;
	
	@Column(name = "UF", nullable = false)
	private String uf;
		
	@OneToOne(mappedBy = "endereco")
    private Usuario usuario;	
	
	public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}	

}
