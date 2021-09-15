package ca.sheridancollege.pate1431.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.pate1431.beans.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

	public Members findByUsername(String username);

}
