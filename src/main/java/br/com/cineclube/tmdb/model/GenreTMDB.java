package br.com.cineclube.tmdb.model;

public class GenreTMDB {
	
	private Integer Id;
	private String name;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GenreTMDB() {
		
	}
	public GenreTMDB(String name) {
		this.name = name;
	}	
	
	

}
