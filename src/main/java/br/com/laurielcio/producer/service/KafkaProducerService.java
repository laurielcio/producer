package br.com.laurielcio.producer.service;

import br.com.laurielcio.producer.dto.UsuarioDto;

/**
 * 
 * @author Lau
 *
 */

public interface KafkaProducerService {

	void producer(UsuarioDto dto);

}
