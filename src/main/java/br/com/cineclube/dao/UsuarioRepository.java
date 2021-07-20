package br.com.cineclube.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.cineclube.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);	
	
	Usuario findUsuarioById(Long id);
	
    List<Usuario> findByNomeIgnoreCaseContaining(String nome);
    
   	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Usuario u where u.id = :id")
	void removerUsuario(Long id);
}
