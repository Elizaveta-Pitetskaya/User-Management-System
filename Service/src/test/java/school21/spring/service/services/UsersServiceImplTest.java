package school21.spring.service.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.config.TestApplicationConfig;
public class UsersServiceImplTest{
    private UsersRepository repository;
    @BeforeEach
    public void init(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        repository = context.getBean("repository", UsersRepository.class);
    }
    @Test
    public void signUpTest(){
        UsersService service = new UsersServiceImpl(repository);
        Assertions.assertNotNull(service.signUp("testionemail@new.yandex.ru"));
    }
}