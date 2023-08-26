package repository;

import model.ToBeIn;
import org.springframework.stereotype.Repository;

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
        ToBeIn toBeIn = new ToBeIn();

        toBeIn.setId(resultSet.getInt("id"));
        toBeIn.setCreatedAt(resultSet.getTimestamp("created_at"));
        toBeIn.setIdUser(resultSet.getInt("id_user"));
        toBeIn.setIdProject(resultSet.getInt("id_project"));

        return toBeIn;
    }
}
