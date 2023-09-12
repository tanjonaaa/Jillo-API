package co.tanjona.man.jillo.repository;

import co.tanjona.man.jillo.model.ToBeIn;
import org.springframework.stereotype.Repository;
import co.tanjona.man.jillo.util.ResultSetMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcToBeInRepository extends JDBCRelationnalRepository<ToBeIn>{

    private static final String tableName = "to_be_in";
    private static final String uniqueField = "id_project";
    private static final List<String> columns = Arrays.asList("created_at", "id_project", "id_user");
    private static final List<String> foreignKeys = Arrays.asList("id_project", "id_user");
    public JdbcToBeInRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns, foreignKeys);
    }


    @Override
    protected ToBeIn mapResultSet(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToToBeIn(resultSet);
    }
}
