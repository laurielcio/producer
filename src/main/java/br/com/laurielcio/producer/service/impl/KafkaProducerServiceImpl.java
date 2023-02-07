package br.com.laurielcio.producer.service.impl;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.laurielcio.producer.dto.UsuarioDto;
import br.com.laurielcio.producer.entity.Usuario;
import br.com.laurielcio.producer.exception.DatabaseAccessException;
import br.com.laurielcio.producer.repository.UsuarioRepository;
import br.com.laurielcio.producer.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Lau
 *
 */

@Slf4j
@Service("KafkaProducerService")
public class KafkaProducerServiceImpl implements KafkaProducerService{
	
	private static final String TOPIC = "TRANSFERENCIA_TESTE";
	
	@Autowired
	private KafkaTemplate<String, UsuarioDto> kafkaTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Override
	public void producer(Long idUsuario) {
		log.info("==== producer init... ====");
		Usuario usuario = new Usuario();
		
		try {
			usuario = usuarioRepository.findByIdUsuario(idUsuario);
		} catch (Exception e) {
			throw new DatabaseAccessException("Ocorreu um erro ao buscar registro de Usuario pra envio ao servidor Kafka!");
		}
		
		if(usuario != null) {
			try {
				kafkaTemplate.send(TOPIC, usuario.getCpf().toString(), new UsuarioDto(usuario));
			}catch (Exception e) {
				e.printStackTrace();
				throw new KafkaException("Ocorreu um erro ao produzir mensagem no servidor Kafka!");
			}
		}						
		
		log.info("==== producer end! ====");		
	}

}
