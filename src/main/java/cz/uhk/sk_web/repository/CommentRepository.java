package cz.uhk.sk_web.repository;

import cz.uhk.sk_web.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
