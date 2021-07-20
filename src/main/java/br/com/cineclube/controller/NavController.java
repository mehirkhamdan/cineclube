package br.com.cineclube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login.html";
	}
	
	/*@RequestMapping("/newuser")
	public String newuser() {
		return "/usuario/new-user.html";
	}*/
	
	@RequestMapping("/changePassword")
	public String changePassword() {
		return "/usuario/changePassword.html";
	}	
	

	
}
