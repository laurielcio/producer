package br.com.laurielcio.producer.service.impl;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.laurielcio.producer.dto.UsuarioDto;
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
	
	private static final String TOPIC = "USUARIO_TOPIC";
	
	@Autowired
	KafkaTemplate<String, UsuarioDto> kafkaTemplate;
	
	@Async
	@Override
	public void producer(UsuarioDto dto) {
		log.info("==== producer init... ====");
		
		try {
			kafkaTemplate.send(TOPIC, dto);
		}catch (Exception e) {
			throw new KafkaException("Ocorreu um erro ao produzir mensagem no servidor Kafka!");
		}				
		
		log.info("==== producer end! ====");		
	}

}
