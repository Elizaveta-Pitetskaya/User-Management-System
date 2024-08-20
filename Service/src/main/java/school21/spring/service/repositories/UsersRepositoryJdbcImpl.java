package school21.spring.service.repositories;
import javax.sql.DataSource;
import java.util.Optional;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
import java.sql.SQLException;
import school21.spring.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource source;
    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource") DataSource source){
        this.source = source;
    }
    public Optional<User> findById(Long id){
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from new_users where identifier = " + id + ";");
            if(resultSet.next()){
                User user = new User(resultSet.getLong("identifier"), resultSet.getString("email"));
                return Optional.of(user);
            }
            connection.close();
        } catch(Exception e){}
        return Optional.empty();
    }

    public List<User> findAll(){
        List<User> list = new LinkedList();
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from new_users;");
            while(resultSet.next()){
                User user = new User(resultSet.getLong("identifier"), resultSet.getString("email"));
                list.add(user);
            }
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return list;
    }

    public void save(User entity){
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into new_users(identifier, email) values(" + entity.getIdentifier() + ", '" + entity.getEmail() + "');";
            if(statement.executeUpdate(query) == 0)
                throw new SQLException("Fail to save user.");
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void update(User entity){
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            String query = "update new_users set email = '" + entity.getEmail() + "' where identifier = " + entity.getIdentifier() + ";";
            if(statement.executeUpdate(query) == 0)
                throw new SQLException("Fail to update users table.");
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void delete(Long id){
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            String query = "delete from new_users where identifier = " + id + ";";
            if(statement.executeUpdate(query) == 0)
                throw new SQLException("Fail to delete from users table.");
            connection.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public Optional<User> findByEmail(String email){
        try{
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from new_users where email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                User user = new User(resultSet.getLong("identifier"), email);
                connection.close();
                return Optional.of(user);
            }
        }catch (Exception e){}
        return Optional.empty();
    }
}