package school21.spring.service.repositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import school21.spring.service.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private NamedParameterJdbcTemplate template;
    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("driverManagerDataSource") DataSource source){
        this.template = new NamedParameterJdbcTemplate(source);
    }
    public Optional<User> findById(Long id){
        try{
            String query = "select * from new_users where identifier = :id;";
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            User user = template.queryForObject(query, params, new UserRowMapper());
            return Optional.of(user);
        } catch (org.springframework.dao.EmptyResultDataAccessException e){}
        return Optional.empty();
    }

    public List<User> findAll(){
        String query = "select * from new_users;";
        List<User> results = template.query(query, new HashMap<String, Object>(), new UserRowMapper());
        return results;
    }

    public void save(User entity){
        String query = "insert into new_users(identifier, email) values(:id, :email);";
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getIdentifier());
        params.put("email", entity.getEmail());
        template.update(query, params);
    }

    public void update(User entity){
        String query = "update new_users set email = :email where identifier = :id;";
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getIdentifier());
        params.put("email", entity.getEmail());
        template.update(query, params);
    }

    public void delete(Long id){
        String query = "delete from new_users where identifier = :id;";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        template.update(query, params);
    }

    public Optional<User> findByEmail(String email){
        try{
            String query = "select * from new_users where email = :email;";
            Map<String, Object> params = new HashMap<>();
            params.put("email", email);
            User user = template.queryForObject(query, params, new UserRowMapper());
            return Optional.of(user);
        }catch(Exception e){
            System.out.println(e);
        }
        return Optional.empty();
    }
}