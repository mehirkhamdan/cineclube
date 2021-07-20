package br.com.cineclube.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.tmdb.model.PersonTMDB;
import br.com.cineclube.tmdb.service.PersondbService;

@RestController
@RequestMapping("/apipes")
@CrossOrigin()
public class ApiPessoaController {
	
	@Autowired
	PessoaRepository dao;
	
	@Autowired
	PersondbService apiServicep;
	
	@GetMapping("/elenco")
	public List<Pessoa> pessoasElenco(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return dao.findAll();
		}
		return dao.findByNomeIgnoreCaseContaining(query);
	}	

	@GetMapping("/pessoa/{id}")
	Pessoa getPessoa(@PathVariable Long id) {
		
        Pessoa p = dao.findById(id).get();
		
		Optional<PersonTMDB> personOpt = apiServicep.searchByName(p.getNome());
		if (personOpt.isPresent()) {
			PersonTMDB persondb = personOpt.get();
			persondb = apiServicep.getById(persondb.getId());
			 p.setPersondb(persondb);	
		}
		return p;
	}		
	
	@GetMapping(value = "/pessoas") 
	Iterable<Pessoa> gePessoas() { 
		return dao.findAll();
	}	
	@PostMapping("/pessoa")
	Pessoa postPessoa(@RequestBody Pessoa pessoa) {
		dao.save(pessoa);
		return pessoa;
	}
	@PutMapping("/pessoa/{id}")
	ResponseEntity<Pessoa> putPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa p = dao.save(pessoa);
		if(p!=null)
			return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
		
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
   @DeleteMapping("/pessoa/{id}")
   void deletePessoa(@PathVariable Long id) {
       dao.removerPessoa(id);
   }

}
