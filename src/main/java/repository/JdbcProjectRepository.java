package repository;

import model.Project;
import model.Task;
import model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class JdbcProjectRepository extends JDBCRepository<Project>{

    private static final  String tableName = "project";
    private static final String uniqueField = "title";
    private static final List<String> columns = Arrays.asList("title", "description", "id_user");
    public JdbcProjectRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected Project mapResultSet(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setTitle(resultSet.getString("title"));
        project.setDescription(resultSet.getString("description"));
        project.setIdUser(resultSet.getInt("id_user"));
        return project;
    }

    public List<User> getUsers(int id){
        List<User> users = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT u.* FROM \"to_be_in\" t_b_i " +
                    "INNER JOIN \"user\" u ON u.id = t_b_i.id_user " +
                    "INNER JOIN \"project\" p ON p.id = t_b_i.id_project " +
                    "WHERE p.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public List<Task> getTasks(int id){
        List<Task> tasks = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"task\" WHERE id_project = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getTimestamp("deadline"));
                task.setIdProject(resultSet.getInt("id_project"));
                task.setIdUser(resultSet.getInt("id_user"));
                task.setIdStatus(resultSet.getInt("id_status"));
                tasks.add(task);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public List<Task> getTasksByStatus(int id, int statusId){
        List<Task> tasks = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"task\" WHERE " +
                    "id_project = ? AND id_status = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, statusId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getTimestamp("deadline"));
                task.setIdProject(resultSet.getInt("id_project"));
                task.setIdUser(resultSet.getInt("id_user"));
                task.setIdStatus(resultSet.getInt("id_status"));
                tasks.add(task);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return tasks;
    }
}
