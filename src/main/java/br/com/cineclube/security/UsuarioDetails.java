package br.com.cineclube.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import br.com.cineclube.model.Usuario;
import br.com.cineclube.model.Role;

public class UsuarioDetails implements UserDetails { /**
	 * 
	 */

// espelhamento da classe Usuario mas para o Spring
	private static final long serialVersionUID = 1L;
	private String email; // ou username
	private String password;
	private Set<Role> roles;
		
    private List<GrantedAuthority> authorities  = new ArrayList<GrantedAuthority>();; // roles (SUPERADMIN, ADMIN, USER, VISITOR, ...)

    public UsuarioDetails(Usuario user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		
		for(Role roleUser : user.getRoles()){
			String role = roleUser.getName();
			this.authorities.add(new SimpleGrantedAuthority(role));	
		}
	}
        
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO - get from database
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO - get from database
		return true;
	}
}
