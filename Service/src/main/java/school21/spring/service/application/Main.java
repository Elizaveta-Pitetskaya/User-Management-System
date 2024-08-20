package school21.spring.service.application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.models.User;
import school21.spring.service.services.UsersService;
import school21.spring.service.config.ApplicationConfig;
public class Main{
    public static void main(String args[]){
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("Test service");
        UsersService service = context.getBean("usersServiceImpl", UsersService.class);
        service.signUp("itsex02@bestmail.ru");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
        System.out.println(usersRepository.findAll());
    }
}