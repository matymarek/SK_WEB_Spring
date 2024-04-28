package cz.uhk.sk_web.repository;

import cz.uhk.sk_web.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);

}
