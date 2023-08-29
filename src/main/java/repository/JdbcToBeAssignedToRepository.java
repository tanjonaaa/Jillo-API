package repository;

import model.ToBeAssignedTo;
import org.springframework.stereotype.Repository;
import util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcToBeAssignedToRepository extends JDBCRelationnalRepository<ToBeAssignedTo>{

    private static final String tableName = "to_be_assigned_to";
    private static final String uniqueField = "id_task";
    private static final List<String> columns = Arrays.asList("created_at", "id_task", "id_user");
    private static final List<String> foreignKeys = Arrays.asList("id_user", "id_task");
    public JdbcToBeAssignedToRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns, foreignKeys);
    }

    @Override
    protected ToBeAssignedTo mapResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
