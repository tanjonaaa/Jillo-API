package repository;

import model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcUserRepository extends JDBCRepository<User>{
    private final static String tableName = "user";
    private final static List<String> columns = Arrays.asList("username", "email", "password");
    private final static String uniqueField = "email";
    public JdbcUserRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected User mapResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
