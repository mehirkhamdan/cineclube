package br.com.cineclube.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cineclube.tmdb.model.PersonTMDB;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=3, max=50, message="Nome deve conter ao menos {min} caracteres")
	@Column(nullable = false)
	private String nome;
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull
	private LocalDate dataNasc;
	
	@Transient
	private PersonTMDB persondb;
	
	@JsonIgnore
	@ManyToMany(mappedBy="pessoas")
	private Set<Filme> filmes;
	
	@Transient
	private Integer age;
	
	public Pessoa() {}
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;
		this.dataNasc = dataNasc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	public Integer getAge() {
		age = (int)ChronoUnit.YEARS.between(dataNasc, LocalDate.now());
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Set<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(Set<Filme> filmes) {
		this.filmes = filmes;
	}
	public PersonTMDB getPersondb() {
		return persondb;
	}
	public void setPersondb(PersonTMDB persondb) {
		this.persondb = persondb;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
