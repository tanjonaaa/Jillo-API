package repository;

import model.Task;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcTaskRepository implements TaskRepository{
    private DataSource dataSource;

    public JdbcTaskRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Task> all() {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"task\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                tasks.add(this.mapResultSet(resultSet));
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task oneById(int id) {
        return null;
    }

    @Override
    public Task oneByTitle(String title) {
        return null;
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

    private Task mapResultSet (ResultSet resultSet) throws SQLException{
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadline(resultSet.getTimestamp("deadline"));
        task.setIdUser(resultSet.getInt("id_user"));
        task.setIdProject(resultSet.getInt("id_project"));
        task.setIdStatus(resultSet.getInt("id_status"));
        return task;
    }
}
