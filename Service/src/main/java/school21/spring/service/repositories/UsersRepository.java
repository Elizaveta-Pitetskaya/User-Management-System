package school21.spring.service.repositories;
import java.util.Optional;
import java.util.List;
import school21.spring.service.models.User;
public interface UsersRepository extends CrudRepository<User>{
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User entity);
    void update(User entity);
    void delete(Long id);
    Optional<User> findByEmail(String email);
}