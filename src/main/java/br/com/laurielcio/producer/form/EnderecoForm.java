package br.com.laurielcio.producer.form;

import java.io.Serializable;

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
public class EnderecoForm implements Serializable {

	private static final long serialVersionUID = 6201049905342282236L;
	
	private String cep;		

	private String logradouro;	

	private String complemento;	

	private String bairro;	

	private String localidade;	

	private String uf;	

	private String ibge;
	
	private String gia;
	
	private String ddd;
	
	private String siafi;

	public Endereco convert(String nrResidencia, String complemento) {
		return new Endereco(cep, logradouro, nrResidencia, complemento, bairro, localidade, uf);
	}

}
