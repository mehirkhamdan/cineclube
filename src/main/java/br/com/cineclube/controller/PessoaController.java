package br.com.cineclube.controller;

import java.util.Optional;

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

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.tmdb.model.PersonTMDB;
import br.com.cineclube.tmdb.service.PersondbService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaRepository dao;
	
	@Autowired
	PersondbService apiService;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("pessoaList",dao.findAll());
		return "pessoa/list.html";
	}@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Pessoa p = new Pessoa();
		model.addAttribute("pessoa", p);
		return "pessoa/manterPessoa.html";
	}
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.removerPessoa(id);
		return "redirect:/pessoas/list";
	}
	@Secured("ROLE_ADMIN, ROLE_USER")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Pessoa p = dao.findById(id).get();
		
		Optional<PersonTMDB> personOpt = apiService.searchByName(p.getNome());
		if (personOpt.isPresent()) {
			PersonTMDB persondb = personOpt.get();
			persondb = apiService.getById(persondb.getId());
			p.setPersondb(persondb);
		}
		model.addAttribute("pessoa", p);
		return "pessoa/manterPessoa.html";
	}
	
	@PostMapping("/save")
	public String save(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if(result.hasErrors())
			return "pessoa/manterPessoa.html";
		dao.save(pessoa);
		return "redirect:/pessoas/list";
	}
}
