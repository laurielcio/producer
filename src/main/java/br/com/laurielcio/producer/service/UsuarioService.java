package br.com.laurielcio.producer.service;

import java.util.List;

import br.com.laurielcio.producer.dto.UsuarioDto;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
import br.com.laurielcio.producer.form.UsuarioEnderecoForm;
import br.com.laurielcio.producer.form.UsuarioForm;

/**
 * 
 * @author Lau
 *
 */

public interface UsuarioService {

	UsuarioDto cadastrar(UsuarioForm form);

	List<UsuarioDto> listar(UsuarioStatusEnum status);

	UsuarioDto buscar(Long idUsuario);

	UsuarioDto alterarStatus(Long idUsuario, UsuarioStatusEnum status);

	void excluir(Long idUsuario);

	UsuarioDto alterarEndereco(UsuarioEnderecoForm form);

}
