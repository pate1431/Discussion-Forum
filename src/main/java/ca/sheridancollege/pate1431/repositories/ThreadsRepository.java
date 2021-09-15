package ca.sheridancollege.pate1431.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.pate1431.beans.Threads;

public interface ThreadsRepository extends JpaRepository<Threads, Long> {

}
