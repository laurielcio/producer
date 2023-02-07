package br.com.laurielcio.producer.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.laurielcio.producer.dto.UsuarioDto;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;
import br.com.laurielcio.producer.form.UsuarioEnderecoForm;
import br.com.laurielcio.producer.form.UsuarioForm;
import br.com.laurielcio.producer.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Lau
 *
 */

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuarios", description = "Endpoints de gerenciamento de Usuários")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@ApiOperation(value = "Endpoint para cadastro de Usuários", 
			      notes = "Todos os parâmetros são obrigatórios e deve ser enviados via RequestBody. "
			      		+ "O parâmetro dtNascimento deve ser no formato yyyy-MM-dd")
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		UsuarioDto dto = usuarioService.cadastrar(form);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(dto.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@ApiOperation(value = "Endpoint para buscar registro específico de Usuário", 
		          notes = "O parâmetro id é obrigatório e deve ser enviado via PathVariable.")
	@GetMapping(value = "/{idUsuario}")
	public ResponseEntity<UsuarioDto> buscar(@PathVariable Long idUsuario) {
		UsuarioDto dto = usuarioService.buscar(idUsuario);
		return ResponseEntity.ok().body(dto);
	}
	
	@ApiOperation(value = "Endpoint para listar todos os Usuários por status", 
			      notes = "O parâmetro status é obrigatório e deve ser enviado via PathVariable.")
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<UsuarioDto>> listar(@PathVariable UsuarioStatusEnum status) {
		List<UsuarioDto> dtos = usuarioService.listar(status);
		return ResponseEntity.ok().body(dtos);
	}
	
	@ApiOperation(value = "Endpoint para alterar status de um Usuário específico", 
	     	      notes = "Os parâmetros idUsuario e status são obrigatórios e devem ser enviados via PathVariable.")
	@PutMapping(value = "/{idUsuario}/{status}")
	public ResponseEntity<UsuarioDto> alterarStatus(@PathVariable String idUsuario, @PathVariable UsuarioStatusEnum status){
		UsuarioDto dto = usuarioService.alterarStatus(Long.parseLong(idUsuario), status);
		return ResponseEntity.ok().body(dto);
	}
	
	
	@ApiOperation(value = "Endpoint para alterar endereco de um Usuário específico",
			      notes = "Os parâmetros idUsuario, cep, nrResidencia são obrigatório."
			      		+ "O parâmetro complemento é opcional. "
			      		+ "Todos os parâmetros deve ser enviados via RequestBody.")
	@PutMapping
	public ResponseEntity<UsuarioDto> alterarEndereco(@RequestBody @Valid UsuarioEnderecoForm form, UriComponentsBuilder uriBuilder) {
		UsuarioDto dto = usuarioService.alterarEndereco(form);
		return ResponseEntity.ok().body(dto);
	}
	
	@ApiOperation(value = "Endpoint para excluir registro de um Usuário específico", 
   	              notes = "O parâmetro idUsuario é obrigatório e deve ser enviado via PathVariable.")
	@DeleteMapping(value = "/{idUsuario}")
	public ResponseEntity<?> excluir(@PathVariable Long idUsuario) {
		
		usuarioService.excluir(idUsuario);
		return ResponseEntity.ok().build();
	}

}
