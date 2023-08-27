package repository;

import model.Task;
import org.springframework.stereotype.Repository;
import util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
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
}
