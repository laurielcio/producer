package br.com.laurielcio.producer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.laurielcio.producer.entity.Usuario;
import br.com.laurielcio.producer.enums.UsuarioStatusEnum;

/**
 * 
 * @author Lau
 *
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findAllByStatus(UsuarioStatusEnum status);

	Usuario findByCpf(String cpf);

	Usuario findByIdUsuario(Long idUsuario);

}
