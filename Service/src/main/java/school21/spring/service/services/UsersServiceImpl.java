package school21.spring.service.services;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.models.User;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class UsersServiceImpl implements UsersService{
    UsersRepository repository;
    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl")UsersRepository repository){
        this.repository = repository;
    }
    public String signUp(String email){
        User user = new User();
        user.setEmail(email);
        user.setIdentifier((long)(repository.findAll().size() + 1));
        repository.save(user);
        return UUID.randomUUID().toString();
    }
}