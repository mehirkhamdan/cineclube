package br.com.cineclube.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.cineclube.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	List<Role> findByName(String name);
	
	@Transactional
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("delete from Role r where r.id = :id")
	void removeRole(Long id);

	List<Role> findByNameIgnoreCaseContaining(String name);


}

