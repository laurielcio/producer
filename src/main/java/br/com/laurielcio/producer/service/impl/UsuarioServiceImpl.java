package br.com.laurielcio.producer.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.laurielcio.producer.dto.UsuarioDto;
import br.com.laurielcio.producer.entity.Usuario;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
import br.com.laurielcio.producer.exception.CPFValidationException;
import br.com.laurielcio.producer.exception.DatabaseAccessException;
import br.com.laurielcio.producer.exception.ObjectNoContent;
import br.com.laurielcio.producer.exception.ValidationException;
import br.com.laurielcio.producer.form.UsuarioEnderecoForm;
import br.com.laurielcio.producer.form.UsuarioForm;
import br.com.laurielcio.producer.repository.UsuarioRepository;
import br.com.laurielcio.producer.service.EnderecoService;
import br.com.laurielcio.producer.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Lau
 *
 */

@Slf4j
@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public UsuarioServiceImpl() {
		
	}
	
	public UsuarioServiceImpl(final UsuarioRepository usuarioRepository, final EnderecoService enderecoService) {
		this.usuarioRepository = usuarioRepository;
		this.enderecoService = enderecoService;
	}

	@Override
	public UsuarioDto cadastrar(UsuarioForm form) {
		log.info("==== cadastrar init... ====");		
		
		validarCpf(form.getCpf());
		
		Usuario usuario = form.convert();

		usuario.setDtCadastro(LocalDate.now());
		usuario.setStatus(UsuarioStatusEnum.ENDERECO_PENDENTE);
		
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao gravar o registro de Usuario! " + e.getMessage());
		}	
		
		enderecoService.salvarEndereco(usuario, form.getCep(), form.getNrResidencia(), form.getComplementoResidencia());		
		
		log.info("==== cadastrar end! ====");		
		return new UsuarioDto(usuario);
	}

	private void validarCpf(String cpf) {
		log.info("==== validarCpf init... ====");
		
		Usuario usuario = new Usuario();
		CPFValidator cpfValidator = new CPFValidator();
		
		try {
			cpfValidator.assertValid(cpf);
		} catch (Exception e) {
			throw new CPFValidationException("CPF invalido! " + e.getMessage());
		}
		
		try {
			usuario = usuarioRepository.findByCpf(cpf);						
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco de dados para validar cpf! " + e.getMessage());
		}
		
		if(usuario != null) {
			throw new CPFValidationException("CPF ja cadastrado!");
		}
		
		log.info("==== validarCpf end! ====");		
	}

	@Override
	public List<UsuarioDto> listar(UsuarioStatusEnum status) {
		log.info("==== listar init... ====");
		
		List<Usuario> usuarios = new ArrayList<>();
		
		try {
			usuarios = usuarioRepository.findAllByStatus(status);
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco ao buscar lista de Usuarios por status! " + e.getMessage());
		}
		
		if(usuarios.isEmpty()) throw new ObjectNoContent("Nenhum Usuario encontrado no bando de dados!");
		
		log.info("==== listar end! ====");		
		return UsuarioDto.convert(usuarios);
	}

	@Override
	public UsuarioDto buscar(Long idUsuario) {
		log.info("==== buscar init... ====");
		
		Usuario usuario = null;
		
		try {
			usuario = usuarioRepository.findByIdUsuario(idUsuario);
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco para buscar Usuario! " + e.getMessage());
		}
		
		if(usuario == null) throw new ObjectNoContent("Nenhum Usuario encontrado com o idUsuario informado!");
		
		log.info("==== buscar end! ====");
		return new UsuarioDto(usuario);
	}

	@Override
	public UsuarioDto alterarStatus(Long idUsuario, UsuarioStatusEnum status) {
		log.info("==== alterarStatus init... ====");
		
		if(status == UsuarioStatusEnum.ENDERECO_PENDENTE) throw new ValidationException("Proibido alterar o status para  ENDERECO_PENDENTE!");
		
		Usuario usuario = new Usuario();
		
		try {
			usuario = usuarioRepository.findByIdUsuario(idUsuario);
			usuario.setStatus(status);
			usuario.setDtAlteracao(LocalDate.now());
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco para alterar o status de Usuario! " + e.getMessage());
		}
		
		log.info("==== alterarStatus end! ====");
		return new UsuarioDto(usuario);
	}

	@Override
	public void excluir(Long idUsuario) {
		log.info("==== excluir init... ====");
		
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioRepository.findByIdUsuario(idUsuario);			
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco para excluir registro de Usuario! " + e.getMessage());
		}
		
		if(usuario == null) throw new ObjectNoContent("Nenhum Usuario encontrado com o idUsuario informado!");
		
		if(usuario.getStatus() == UsuarioStatusEnum.ATIVO) throw new ValidationException("Proibido a exclusao de registro de Usuario com status ATIVO!");	
		
		try {
			usuarioRepository.deleteById(idUsuario);		
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco para excluir registro de Usuario! " + e.getMessage());
		}		
		
		log.info("==== excluir end! ====");		
	}

	@Override
	public UsuarioDto alterarEndereco(UsuarioEnderecoForm form) {
		log.info("==== alterarEndereco init... ====");
		
		Usuario usuario = new Usuario();
		
		try {
			usuario = usuarioRepository.findByIdUsuario(form.getIdUsuario());
		}catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao acessar o banco para buscar registro de Usuario! " + e.getMessage());
		}
		
		if(usuario == null) throw new ValidationException("Nao foi encontrado Usuario com o id infomado!");
		
		enderecoService.salvarEndereco(usuario, form.getCep(), form.getNrResidencia(), form.getComplemento());
		
		log.info("==== alterarEndereco end! ====");	
		return new UsuarioDto(usuario);
	}

}
