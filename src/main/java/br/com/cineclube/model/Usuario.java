package br.com.cineclube.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank(message="Campo nome obrigatorio")
	@Size(min=2,max=50,message="Nome deve conter {min} ate {max} caracteres")
	@Column(nullable = false)
	private String nome;
	

	@NotBlank(message="Campo email obrigatorio")
	@Email(message = "email invalido")
	@Column(nullable = false, unique=true)
	private String email;
	

	@Column(nullable = false)
	private String password;	
	
	@Transient
	private String confirmPassword;
	
	@Transient	
	private String oldPassword;		

	@JsonSerialize(using = RoleSerializer.class)
    @Size(min=1, max=3, message="qtd de roles deve ser entre {min} e {max}")
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_role",
		joinColumns = {@JoinColumn(name="usuario_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}) 
    private Set<Role> roles;
	
	public Usuario() {}
		
	public Usuario(String nome, String email, String password, String confirmPassword, Role role) {
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		if (role!=null) {
			this.roles = new HashSet<>();
			this.roles.add(role);
		}
	}
	
	public Usuario(String nome, String email, String password, String confirmPassword, List<Role> roles) {
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		if (roles!=null && roles.size() > 0) {
			this.roles = new HashSet<>();
			this.roles.addAll(roles);
		}
	}		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}


