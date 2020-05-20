package royalstacks.app.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import royalstacks.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findUserByUsername (String username);
}
