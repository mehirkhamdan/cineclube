package br.com.cineclube.tmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.tmdb.model.WrapperGenreSearch;


@Service
public class GenredbService {
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
    public WrapperGenreSearch searchGenresAll() {
    	String genreUrl = 
    			"https://api.themoviedb.org/3/genre/movie/list?api_key="
    	+  apiKey + "&language=pt-BR";
    	
    	 WrapperGenreSearch searchResult = apiRequest.getForObject(genreUrl,WrapperGenreSearch.class);
    	      	  
    	  return searchResult;
    }

}
