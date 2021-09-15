package ca.sheridancollege.pate1431.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.pate1431.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRolename(String rolename);
}
