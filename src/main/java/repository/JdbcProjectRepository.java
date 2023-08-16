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
        return null;
    }

    @Override
    public void save(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(Project project) {

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
