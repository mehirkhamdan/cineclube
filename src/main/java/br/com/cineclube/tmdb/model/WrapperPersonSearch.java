package br.com.cineclube.tmdb.model;

import java.util.List;

public class WrapperPersonSearch {
	
	private List<PersonTMDB> results;

    public List<PersonTMDB> getResults() {
    	
    	results.sort( (f1,f2) -> Double.compare(f2.getPopularity(), f1.getPopularity()) );
        return results;
    }
    public void setResults(List<PersonTMDB> results) {
        this.results = results;
    }

}
