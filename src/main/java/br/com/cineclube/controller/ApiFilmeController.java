package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Filme;
import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.service.MoviedbService;

@RestController
@RequestMapping("/apifilme")
@CrossOrigin()
public class ApiFilmeController {	
	
	@Autowired
	FilmeRepository daof;		
	
	@Autowired
	MoviedbService apiService;	
	
	@GetMapping(value = "/filmes") 
	Iterable<Filme> getFilmes() { 
		return daof.findAll();
	}	
	@GetMapping("/filme/{id}")
	Filme getFilme(@PathVariable Long id) {
		
		Filme filme = daof.findById(id).get();
		
		MovieTMDB moviedb = apiService.searchOneMovie(
				filme.getNome(), 
				filme.getLancamento().getYear()
		);
		filme.setMoviedb(moviedb);
		
		return filme;
	}	
		
	@PostMapping("/filme")
	Filme postFilme(@RequestBody Filme filme) {
		daof.save(filme);
		return filme;
	}
	@PutMapping("/filme/{id}")
	ResponseEntity<Filme> putFilme(@PathVariable Long id, @RequestBody Filme filme) {
		Filme p = daof.save(filme);
		if(p!=null)
			return new ResponseEntity<>(filme, HttpStatus.CREATED);
		
		return new ResponseEntity<>(filme, HttpStatus.OK);
	}
   @DeleteMapping("/filme/{id}")
   void deleteFilme(@PathVariable Long id) {
 //      daof.removerFilme(id);
	   daof.deleteById(id);
	   }
	
}
	
	
	
