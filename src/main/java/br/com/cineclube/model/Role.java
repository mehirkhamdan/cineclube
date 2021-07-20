package br.com.cineclube.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Role {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotBlank(message = "Campo nome obrigatorio")
		@Size(min = 2, max = 50, message = "Nome deve conter {min} ate {max} caracteres")
		@Column(nullable = false)
		private String name;		
		
		@JsonSerialize(using = UsuarioSerializer.class)
		@ManyToMany(fetch=FetchType.EAGER, mappedBy = "roles")
		private Set<Usuario> users;
		
		public Role() {	}
		
		public Role(String name) {
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Usuario> getUsers() {
			return users;
		}

		public void setUsers(Set<Usuario> users) {
			this.users = users;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Role other = (Role) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}		
				
		}
		
		


