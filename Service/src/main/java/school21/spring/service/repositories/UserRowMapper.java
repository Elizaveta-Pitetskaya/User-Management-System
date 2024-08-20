package school21.spring.service.repositories;
import school21.spring.service.models.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserRowMapper implements RowMapper<User>{
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User();
        user.setIdentifier(rs.getLong("identifier"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}