package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.model.WrapperMovieSearch;
import br.com.cineclube.tmdb.service.MoviedbService;

@RestController 
@RequestMapping("/api/v1")
public class MoviedbConsumer {
	
    @Autowired 
	MoviedbService apiService;    
    
    @RequestMapping("/MovieTMDB/{id}")
    public MovieTMDB getMovieById(@PathVariable Long id) {
    	
        return apiService.getMovieById(id);
    }
    @GetMapping("/search")
    public WrapperMovieSearch searchMovie(@RequestParam String title, @RequestParam Integer year){    	
    		
    	return apiService.searchMovie(title, year);
    	
    }   
    @GetMapping("/search1")
    public MovieTMDB searchOneMovie(@RequestParam String title, @RequestParam Integer year){
    	
       	return apiService.searchOneMovie(title, year);

    }    
    
}
