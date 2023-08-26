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

    /*
    @Override
    public User oneById(int id) {
        User user = new User();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"user\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()){
                user = null;
            }else {
                user = mapResultSet(resultSet);
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO \"user\" (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
            System.out.println("Votre utilisateur a bien été inséré");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE \"user\" SET username = ?, email = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
            System.out.println("Les données de l'utilisateur ont bien été mises à jour");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \"user\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, user.getId());

            int rowCount = statement.executeUpdate();
            System.out.println(rowCount+" suppression réussie");

            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User oneByEmail(String email){
        User user = new User();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"user\" WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()){
                user = null;
            }else {
                user = mapResultSet(resultSet);
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    private User mapResultSet (ResultSet resultSet) throws SQLException{
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
    }
     */
}
