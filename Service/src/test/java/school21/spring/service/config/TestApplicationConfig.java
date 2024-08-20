package school21.spring.service.config;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
@Configuration
@ComponentScan(basePackages = "school21.spring.service")
@PropertySource("classpath:db.properties")
public class TestApplicationConfig {
    @Bean
    public DataSource embeddedDB(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("/schema.sql").addScript("/data.sql").build();
    }

    @Bean
    public UsersRepository repository(){
        return new UsersRepositoryJdbcTemplateImpl(embeddedDB());
    }

}