package repository;

import model.Project;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcProjectRepository implements ProjectRepository{

    private DataSource dataSource;

    public JdbcProjectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Project> all() {
        List<Project> projects = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"project\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                projects.add(mapResultSet(resultSet));
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Project oneById(int id) {
        Project project = new Project();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"project\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            project = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project oneByTitle(String title) {
        Project project = new Project();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"project\" WHERE title = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            project = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void save(Project project) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO \"project\" (title, description, id_user) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, project.getTitle());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getIdUser());

            statement.executeUpdate();
            System.out.println("Votre projet a bien été inséré");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(Project project) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \"project\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, project.getId());
            statement.executeUpdate();
            System.out.println("Le projet a bien été supprimé");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Project mapResultSet (ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setTitle(resultSet.getString("title"));
        project.setDescription(resultSet.getString("description"));
        project.setIdUser(resultSet.getInt("id_user"));
        return project;
    }
}
