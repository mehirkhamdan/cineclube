package br.com.cineclube.controller;

/*import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.dao.CategoriaRepository;
import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.dao.PessoaRepository;
import br.com.cineclube.dao.RoleRepository;
import br.com.cineclube.model.Categoria;
import br.com.cineclube.model.Filme;
import br.com.cineclube.model.Pessoa;
import br.com.cineclube.model.Role;

/*@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PessoaRepository dao;
	
	@Autowired
	FilmeRepository daof;
	
	@Autowired
	CategoriaRepository daoCat;
	
	@Autowired
	RoleRepository daoRol;
	
	@GetMapping("categorias")
    public List<Categoria> getCategorias(@RequestParam(value = "q", required = false) String query) {
        if (!StringUtils.hasLength(query)) {
            return daoCat.findAll();
        }
        return daoCat.findByNomeIgnoreCaseContaining(query);
    }		
	@GetMapping("/elenco")
	public List<Pessoa> pessoasElenco(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return dao.findAll();
		}
		return dao.findByNomeIgnoreCaseContaining(query);
	}	
	
	@GetMapping("/permissoes")
	public List<Role> rolePermissoesl(@RequestParam(value = "q", required = false) String query) {
		if (!StringUtils.hasLength(query)) {
			return daoRol.findAll();
		}
		return daoRol.findByNameIgnoreCaseContaining(query);
	}
	
	@GetMapping(value = "/filmes") 
	Iterable<Filme> getFilmes() { 
		return daof.findAll();
	}		
	@GetMapping("/pessoa/{id}")
	Optional<Pessoa> getPessoa(@PathVariable Long id) { 
		return dao.findById(id);
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
	
}*/
