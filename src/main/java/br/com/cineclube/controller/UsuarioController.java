package br.com.cineclube.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cineclube.dao.UsuarioRepository;
import br.com.cineclube.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UsuarioRepository daoUser;

	@Secured("ROLE_ADMIN")
	@GetMapping("/new")
	public String newForm(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "usuario/manterUser.html";
	}
	

	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Usuario usuario = daoUser.findById(id).get();
		model.addAttribute("usuario", usuario);
		return "usuario/manterUser.html";
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/save")
	public String save(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("password") String Password) {
		

		if (result.hasErrors()) {
			return "usuario/manterUser.html";

		} else if (!usuario.getPassword().equals(usuario.getConfirmPassword())) {
			return "usuario/manterUser.html";
		}

		usuario.setPassword(passwordEncoder.encode(Password));
		daoUser.save(usuario);
		return "redirect:/usuarios/list";
	}
	

	@GetMapping("/changePassword")
	public String trocaSenha(Model model, Usuario usuario) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email= authentication.getName();
        Usuario user = daoUser.findByEmail(email);
		model.addAttribute("usuario", user);
		return "/usuario/changePassword.html";
	}

	@PostMapping("/savePass")
	public String savePass(@Valid Usuario usuarioForm, BindingResult result, Model model) {

		
		Usuario user = daoUser.findUsuarioById(usuarioForm.getId());	
		if (passwordEncoder.matches(usuarioForm.getOldPassword(), user.getPassword())) {
		}else if (!usuarioForm.getPassword().equals(usuarioForm.getConfirmPassword())) {
				return "usuario/manterUser.html";
			}
			user.setPassword(passwordEncoder.encode(usuarioForm.getPassword()));
			daoUser.save(user);
			
		
			return "redirect:/";			
	}
	

	
	@GetMapping("/list")
	public String list(Model model) {
		List<Usuario> usuarios = daoUser.findAll();
		model.addAttribute("userList", usuarios);
		return "usuario/list.html";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		daoUser.removerUsuario(id);
		return "redirect:/usuarios/list";
	}

}
