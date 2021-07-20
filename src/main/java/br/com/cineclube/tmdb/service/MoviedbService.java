package br.com.cineclube.tmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.model.WrapperMovieSearch;

@Service // acessada via autowired (uso interno das nossas classes)
public class MoviedbService {
	
	/* realiza a busca por filme, pois essa busca eh usada em diversas partes do sistema */
	// MoviedbService apiService = new MoviedbService();
	
	@Value("${api.moviedb.key}")
    private String apiKey;

    @Autowired
    private RestTemplate apiRequest;
    
	public WrapperMovieSearch searchMovie(String title, Integer year){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" 
        			+  apiKey + "&query=" + title + "&year=" + year;
    	
    	// aonde ocorre a des-serializacao (converter o retorno em json para objeto java)
    	WrapperMovieSearch searchResult = 
    			apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	
    	return searchResult;
    }		
    public MovieTMDB getMovieById(Long id) {
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/movie/" + id + "?api_key=" +  apiKey;
    	
        MovieTMDB filme = apiRequest.getForObject(filmeUrl, MovieTMDB.class);
        
        return filme; // serializado em JSON
        
    }
    public MovieTMDB searchOneMovie(String title, Integer year){
    	
    	String filmeUrl = 
        		"https://api.themoviedb.org/3/search/movie?api_key=" +  apiKey + "&query=" + title + "&year=" + year;
    	
    	
    	WrapperMovieSearch searchResult = apiRequest.getForObject(filmeUrl, WrapperMovieSearch.class);
    	
    	MovieTMDB filme = new MovieTMDB();
    	
    	// testando se existe ao menos um filme na lista de resultados (vem da moviedb api)
    	if (searchResult.getResults()!=null && searchResult.getResults().size() > 0) {
    		filme = searchResult.getResults().get(0);
    	}
    	
    	return filme;
    }
	

}
