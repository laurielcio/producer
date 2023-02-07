package br.com.laurielcio.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Lau
 *
 */

@SpringBootApplication
@EnableAsync
@EnableSwagger2
@EnableKafka
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}
