package repository;

import model.Status;
import model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JdbcStatusRepository implements StatusRepository{

    private DataSource dataSource;

    public JdbcStatusRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Status> all() {
        List<Status> status = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"status\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                status.add(mapResultSet(resultSet));
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Status oneById(int id) {
        Status status = new Status();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"status\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            status = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Status oneByName(String name) {
        Status status = new Status();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"status\" WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            status = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public void save(Status status) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO \"status\" (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status.getName());

            statement.executeUpdate();
            System.out.println("Votre statut de tâche a bien été inséré");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Status status) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE \"status\" SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status.getName());
            statement.setInt(2, status.getId());

            statement.executeUpdate();
            System.out.println("Les données du statut de tâche ont bien été mises à jour");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Status status) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \"status\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, status.getId());

            int rowCount = statement.executeUpdate();
            System.out.println(rowCount+" suppression réussie");

            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private Status mapResultSet (ResultSet resultSet) throws SQLException {
        Status status= new Status();
        status.setId(resultSet.getInt("id"));
        status.setName(resultSet.getString("name"));
        return status;
    }
}
