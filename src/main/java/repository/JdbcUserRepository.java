package repository;

import model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository{
    DataSource dataSource;
    public JdbcUserRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<User> all() {
        List<User> results = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"user\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                results.add(user);
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public User oneById(int id) {
        User result = new User();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"user\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            result.setId(resultSet.getInt("id"));
            result.setUsername(resultSet.getString("username"));
            result.setUsername(resultSet.getString("email"));
            result.setUsername(resultSet.getString("password"));

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
