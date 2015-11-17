package song.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import song.core.model.User;

/**
 * Created by Song on 2015/11/10.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmailAndPassword(String email,String password);

    User findByEmail(String email);


    User findByName(String name);
}



