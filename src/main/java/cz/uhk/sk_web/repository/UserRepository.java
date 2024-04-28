package cz.uhk.sk_web.repository;

import cz.uhk.sk_web.model.User;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);

    @Procedure("DeleteUserAndArchiveDataInTransaction")
    void deleteAndArchiveInTransaction(@Param("original_user_id") int id);

}
