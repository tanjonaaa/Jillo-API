package repository;

import model.Task;
import model.User;
import org.springframework.stereotype.Repository;
import util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class JdbcTaskRepository extends JDBCRepository<Task>{

    private static final String tableName = "task";
    private static final String uniqueField = "title";
    private static final List<String> columns = Arrays.asList(
            "title", "description", "deadline", "id_project", "id_status", "id_user"
    );
    public JdbcTaskRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected Task mapResultSet (ResultSet resultSet) throws SQLException{
        return ResultSetMapper.mapResultSetToTask(resultSet);
    }

    public List<User> getUsers(int id){
        List<User> users = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT u.* FROM \"to_be_assigned_to\" t_b_a_t " +
                    "INNER JOIN \"user\" u ON u.id = t_b_a_t.id_user " +
                    "INNER JOIN \"task\" t ON t.id = t_b_a_t.id_task " +
                    "WHERE t.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(ResultSetMapper.mapResultSetToUser(resultSet));
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
