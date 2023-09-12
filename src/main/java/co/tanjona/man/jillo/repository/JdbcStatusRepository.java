package co.tanjona.man.jillo.repository;

import co.tanjona.man.jillo.model.Status;
import org.springframework.stereotype.Repository;
import co.tanjona.man.jillo.util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
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
        return ResultSetMapper.mapResultSetToStatus(resultSet);
    }
}
