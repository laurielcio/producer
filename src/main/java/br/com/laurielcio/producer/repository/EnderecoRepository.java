package br.com.laurielcio.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.laurielcio.producer.entity.Endereco;


/**
 * 
 * @author Lau
 *
 */

@Component
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
