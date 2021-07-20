package br.com.cineclube.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cineclube.dao.UsuarioRepository;
import br.com.cineclube.model.Usuario;

@Service
public class UsuarioService implements UserDetailsService {
	// Nossa service precisa implementar a interface UserDetailsService


	
	@Autowired
	UsuarioRepository dao;
	// carrega do database os dados do usuario de acordo com o username fornecido
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// ir na UsuarioRepository e consultar se o usuario existe....
		Usuario user = dao.findByEmail(email);
		if (user==null) {
			throw new UsernameNotFoundException("Login invalido");
		}
		UsuarioDetails usd = new UsuarioDetails(user);
		return usd;

	}			
		    	
	
}


