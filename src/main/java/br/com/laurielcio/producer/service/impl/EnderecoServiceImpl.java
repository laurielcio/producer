package br.com.laurielcio.producer.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.laurielcio.producer.dto.UsuarioDto;
import br.com.laurielcio.producer.entity.Endereco;
import br.com.laurielcio.producer.entity.Usuario;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
import br.com.laurielcio.producer.exception.DatabaseAccessException;
import br.com.laurielcio.producer.exception.RestTemplateException;
import br.com.laurielcio.producer.form.EnderecoForm;
import br.com.laurielcio.producer.repository.UsuarioRepository;
import br.com.laurielcio.producer.service.EnderecoService;
import br.com.laurielcio.producer.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Lau
 *
 */

@Slf4j
@Service("EnderecoService")
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private UsuarioRepository professorRepository;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	public EnderecoServiceImpl(UsuarioRepository professorRepository, KafkaProducerService kafkaProducerService) {
		this.professorRepository = professorRepository;
		this.kafkaProducerService = kafkaProducerService;
	}
	
	@Async
	@Override
	public void salvarEndereco(Usuario usuario, String cep, String nrResidencia, String complemento) {
		log.info("===== salvarEndereco init... ===== ");
		
		EnderecoForm enderecoForm = new EnderecoForm();
		Endereco endereco = new Endereco();
		
		try {
			enderecoForm = new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", EnderecoForm.class).getBody();
		} catch (Exception e) {
			throw new RestTemplateException("O servidor VIACEP esta apresentando erro! " + e.getMessage());		
		}
		
		if(enderecoForm.getCep() != null) {
			
			endereco = enderecoForm.convert(nrResidencia, complemento);
			usuario.setEndereco(endereco);
			usuario.setStatus(UsuarioStatusEnum.ATIVO);
			usuario.setDtAlteracao(LocalDate.now());
			
			try {
				professorRepository.save(usuario);
			} catch (Exception e) {
				throw new DatabaseAccessException("Ocorreu um erro ao gravar o endereço! " + e.getMessage());
			}						
			
		}else {
			throw new RestTemplateException("CEP não encontrado!");	
		}
		
		kafkaProducerService.producer(new UsuarioDto(usuario));
		
		log.info("===== salvarEndereco end! ===== ");		
	}

}
