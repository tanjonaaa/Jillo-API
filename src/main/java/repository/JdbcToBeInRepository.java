package repository;

import model.ToBeIn;
import org.springframework.stereotype.Repository;
import util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcToBeInRepository extends JDBCRepository<ToBeIn>{

    private static final String tableName = "to_be_in";
    private static final String uniqueField = "id_project";
    private static final List<String> columns = Arrays.asList("created_at", "id_project", "id_user");
    public JdbcToBeInRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected ToBeIn mapResultSet(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToToBeIn(resultSet);
    }


}
