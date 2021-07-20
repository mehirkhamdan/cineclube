package br.com.cineclube.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.cineclube.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNome(String nome);
	
	List<Categoria> findByNomeIgnoreCaseContaining(String nome);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Categoria c where c.id = :id")
	void removerCategoria(Long id);
}
