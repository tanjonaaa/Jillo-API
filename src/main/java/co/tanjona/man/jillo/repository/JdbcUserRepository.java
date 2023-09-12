package co.tanjona.man.jillo.repository;

import co.tanjona.man.jillo.model.Project;
import co.tanjona.man.jillo.model.User;
import org.springframework.stereotype.Repository;
import co.tanjona.man.jillo.util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return ResultSetMapper.mapResultSetToUser(resultSet);
    }

    public List<Project> getProjects(int id){
        List<Project> projects = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT p.* FROM \"to_be_in\" t_b_i " +
                    "INNER JOIN \"project\" p ON p.id = t_b_i.id_project " +
                    "INNER JOIN \"user\" u ON u.id = t_b_i.id_user " +
                    "WHERE t_b_i.id_user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                projects.add(ResultSetMapper.mapResultSetToProject(resultSet));
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return projects;
    }
}
