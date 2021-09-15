package ca.sheridancollege.pate1431.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.pate1431.beans.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
