package br.com.cineclube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.CategoriaRepository;
import br.com.cineclube.model.Categoria;


@RestController
@RequestMapping("/apicat")
@CrossOrigin()
public class ApiCategoriaController {
	
	@Autowired
	CategoriaRepository daoCat;
	
	 @GetMapping("categoria")
	   public List<Categoria> getCategorias(@RequestParam(value = "q", required = false) String query) {
	       if (!StringUtils.hasLength(query)) {
	           return daoCat.findAll();
	       }
	       return daoCat.findByNomeIgnoreCaseContaining(query);
	   }	
	
	@GetMapping(value = "/categorias") 
	Iterable<Categoria> getCategorias() { 
		return daoCat.findAll();
	}	
	@PostMapping("/categoria")
	Categoria postCategoria(@RequestBody Categoria categoria) {
		daoCat.save(categoria);
		return categoria;
	}
	@PutMapping("/categoria/{id}")
	ResponseEntity<Categoria> putCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
		Categoria p = daoCat.save(categoria);
		if(p!=null)
			return new ResponseEntity<>(categoria, HttpStatus.CREATED);
		
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}
  @DeleteMapping("/categoria/{id}")
  void deleteCategoria(@PathVariable Long id) {
      daoCat.removerCategoria(id);
  }

	
	



}
