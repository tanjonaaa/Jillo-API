package repository;

import model.Task;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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
        Task task = new Task();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"task\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            task = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task oneByTitle(String title) {
        Task task = new Task();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"task\" WHERE title = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            task = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void save(Task task) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO \"task\" (" +
                    "title, description, deadline, id_user, id_project, id_status" +
                    ") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, task.getDeadline());
            statement.setInt(4, task.getIdUser());
            statement.setInt(5, task.getIdProject());
            statement.setInt(6, task.getIdStatus());

            statement.executeUpdate();
            System.out.println("Votre tâche a bien été inséré");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task task) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE \"task\" SET " +
                    "title = ?, description = ?, deadline = ?, id_user = ?, id_project = ?, id_status = ?" +
                    " WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, task.getDeadline());
            statement.setInt(4, task.getIdUser());
            statement.setInt(5, task.getIdProject());
            statement.setInt(6, task.getIdStatus());
            statement.setInt(7, task.getId());

            statement.executeUpdate();
            System.out.println("Votre tâche a bien été modifiée");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Task task) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \"task\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getId());

            int rowCount = statement.executeUpdate();
            System.out.println(rowCount+" suppression réussie");

            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
