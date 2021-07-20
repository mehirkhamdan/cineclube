package br.com.cineclube.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cineclube.dao.CategoriaRepository;
import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Categoria;
import br.com.cineclube.model.Filme;
import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.service.MoviedbService;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	FilmeRepository dao;
	
	@Autowired
	CategoriaRepository daoCat;
	
	@Autowired
	MoviedbService apiService;

	@Secured("ROLE_ADMIN")	
	@GetMapping("/new")
	public String newForm(Model model) {
		Filme filme = new Filme();
		model.addAttribute("filme", filme);
		return "filme/manterFilme.html";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
	
		Filme filme = dao.getOne(id);
		model.addAttribute("filme", filme);
		
		MovieTMDB moviedb = apiService.searchOneMovie(
				filme.getNome(), 
				filme.getLancamento().getYear()
		);
		filme.setMoviedb(moviedb);
	
		return "filme/manterFilme.html";
	}
	@GetMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("filmeList", filmeList);
		model.addAttribute("categorias", daoCat.findAll());
		model.addAttribute("cat", new Categoria());
		return "filme/list.html";
	}	
	@PostMapping("/save")
	public String save(@Valid Filme filme, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "filme/manterFilme.html";
		}
		dao.save(filme);
		return "redirect:/filmes/list";
	}
	@PostMapping(value = "/filtra")
	public String filtra(Long cat, Model model) {
		List<Filme> filmes = dao.findByCategoria(cat);
		model.addAttribute("filmeList",filmes);
		model.addAttribute("categorias", daoCat.findAll());
		return "filme/list.html";
	}
}
