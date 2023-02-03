package br.com.laurielcio.producer.service;

import br.com.laurielcio.producer.entity.Usuario;

/**
 * 
 * @author Lau
 *
 */

public interface EnderecoService {

	void salvarEndereco(Usuario usuario, String cep, String nrResidencia, String complementoResidencia);

}
