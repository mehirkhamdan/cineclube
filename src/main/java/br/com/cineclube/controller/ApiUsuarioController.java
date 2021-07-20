package br.com.cineclube.controller;

import java.util.List;

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

import br.com.cineclube.dao.UsuarioRepository;
import br.com.cineclube.model.Usuario;

@RestController
@RequestMapping("/apiuser")
@CrossOrigin()
public class ApiUsuarioController {
	
	@Autowired
	UsuarioRepository daoUser;
	
	@GetMapping("usuario")
	   public List<Usuario> getUsuarios(@RequestParam(value = "q", required = false) String query) {
	       if (!StringUtils.hasLength(query)) {
	           return daoUser.findAll();
	       }
	       return daoUser.findByNomeIgnoreCaseContaining(query);
	   }	
	
	@GetMapping(value = "/usuarios") 
	Iterable<Usuario> getUsuarios() { 
		return daoUser.findAll();
			
	}
	
	@PostMapping("/usuario")
	Usuario postUsuario(@RequestBody Usuario usuario) {
		daoUser.save(usuario);
		return usuario;
	}
	@PutMapping("/usuario/{id}")
	ResponseEntity<Usuario> putUsuario1(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario u = daoUser.save(usuario);
		if(u!=null)
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
  @DeleteMapping("/usuario/{id}")
  void deleteUsuario(@PathVariable Long id) {
      daoUser.removerUsuario(id);
  }

}

