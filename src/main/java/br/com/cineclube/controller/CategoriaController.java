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
import br.com.cineclube.model.Categoria;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository dao;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Categoria> catList = dao.findAll();
		model.addAttribute("catList", catList);
		return "categoria/list-categoria";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Categoria cat = new Categoria();
		model.addAttribute("categoria", cat);
		return "categoria/manterCat.html";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerCategoria(id);
		return "redirect:/categorias/list";
	}
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Categoria cat = dao.findById(id).get();
		model.addAttribute("categoria", cat);
		return "categoria/manterCat.html";
	}
	@PostMapping("/save")
	public String save(@Valid Categoria cat, BindingResult result, Model model) {
		if(result.hasErrors())
			return "categoria/manterCat.html";
		dao.save(cat);
		return "redirect:/categorias/list";
	}

}
