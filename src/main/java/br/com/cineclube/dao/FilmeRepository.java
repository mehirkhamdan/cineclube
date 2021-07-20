package br.com.cineclube.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.cineclube.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	
	List<Filme> findByNome(String nome);
	List<Filme> findByNotaGreaterThan(Float nota);
	List<Filme> findByNotaGreaterThanEqual(Float nota);
	List<Filme> findByNotaLessThanEqual(Float nota);
	List<Filme> findTop3ByNotaGreaterThanEqualOrderByNotaDesc(Float nota);
	List<Filme> findByNotaBetween(Float min, Float max);
	
	@Query("select f from Filme f join f.categorias c where c.id = ?1")
	List<Filme> findByCategoria(Long id);
	
	
}
