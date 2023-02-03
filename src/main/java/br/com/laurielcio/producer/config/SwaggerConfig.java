package br.com.laurielcio.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.laurielcio.producer.entity.Endereco;
import br.com.laurielcio.producer.entity.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Lau
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String SWAGGER_TITULO = "API PRODUCER";
	
	@Bean
    public Docket api() {        
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        		.apis(RequestHandlerSelectors.basePackage( "br.com.laurielcio.producer" ))
    			.paths(PathSelectors.ant("/**"))
        		.build()
        		.apiInfo(apiInfo())
        		.ignoredParameterTypes(Usuario.class)
        		.ignoredParameterTypes(Endereco.class);
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder().title(SWAGGER_TITULO)
	    						   .description("API de gerenciamento professores e cursos")
	    						   .version("0.0.1")
	    						   .contact(new Contact("LAURIELCIO", "https://www.linkedin.com/in/laurielcio", "laurielcio@yahoo.com.br"))
	    						   .license("Para uso interno")
	    						   .licenseUrl("https://swagger.io/license/")
	    						   .build();
	}
}
