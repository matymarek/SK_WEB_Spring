package cz.uhk.sk_web.repository;

import cz.uhk.sk_web.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
    Image findById(int id);
}
