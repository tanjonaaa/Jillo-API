package repository;

import model.ToBeIn;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcToBeInRepository implements ToBeInRepository{
    private DataSource dataSource;

    public JdbcToBeInRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ToBeIn> all() {
        List<ToBeIn> toBeInS = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM \"to_be_in\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                toBeInS.add(this.mapResultSet(resultSet));
            }

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return toBeInS;
    }

    @Override
    public ToBeIn oneById(int id) {
        ToBeIn toBeIn = new ToBeIn();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"to_be_in\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            toBeIn = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return toBeIn;
    }

    @Override
    public ToBeIn oneByProjectId(int projectId) {
        ToBeIn toBeIn = new ToBeIn();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \"to_be_in\" WHERE id_project = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            toBeIn = mapResultSet(resultSet);

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return toBeIn;
    }

    @Override
    public void save(ToBeIn toBeIn) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO \"to_be_in\" (id_user, id_project) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, toBeIn.getIdUser());
            statement.setInt(2, toBeIn.getIdProject());

            statement.executeUpdate();
            System.out.println("Votre nouveau contributeur a bien été inséré");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(ToBeIn toBeIn) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE \"to_be_in\" " +
                    "SET created_at = ?, id_user = ?, id_project = ? WHERE" +
                    " id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setTimestamp(1, toBeIn.getCreatedAt());
            statement.setInt(2, toBeIn.getIdUser());
            statement.setInt(3, toBeIn.getIdProject());
            statement.setInt(4, toBeIn.getId());

            statement.executeUpdate();
            System.out.println("Les données de la contribution ont bien été mises à jour");

            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ToBeIn toBeIn) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \"to_be_in\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, toBeIn.getId());

            int rowCount = statement.executeUpdate();
            System.out.println(rowCount+" suppression réussie");

            statement.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private ToBeIn mapResultSet(ResultSet resultSet) throws SQLException {
        ToBeIn toBeIn = new ToBeIn();

        toBeIn.setId(resultSet.getInt("id"));
        toBeIn.setCreatedAt(resultSet.getTimestamp("created_at"));
        toBeIn.setIdUser(resultSet.getInt("id_user"));
        toBeIn.setIdProject(resultSet.getInt("id_project"));

        return toBeIn;
    }
}
