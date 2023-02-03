package br.com.laurielcio.producer.dto;

import br.com.laurielcio.producer.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Lau
 *
 */

@Getter
@Setter
public class EnderecoDto {

	private Long idEndereco;

	private String cep;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String localidade;

	private String uf;
	
	public EnderecoDto(Endereco endereco) {
		this.idEndereco = endereco.getIdEndereco();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.localidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
	}

}
