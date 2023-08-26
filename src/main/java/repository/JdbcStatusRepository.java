package repository;

import model.Status;
import model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class JdbcStatusRepository extends JDBCRepository<Status>{

    private static final String tableName = "status";
    private static final String uniqueField = "name";
    private static final List<String> columns = List.of("name");
    public JdbcStatusRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected Status mapResultSet (ResultSet resultSet) throws SQLException {
        Status status= new Status();
        status.setId(resultSet.getInt("id"));
        status.setName(resultSet.getString("name"));
        return status;
    }
}
