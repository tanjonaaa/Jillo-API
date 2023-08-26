package repository;

import model.Project;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
@Repository
public class JdbcProjectRepository extends JDBCRepository<Project>{

    private static final  String tableName = "project";
    private static final String uniqueField = "title";
    private static final List<String> columns = Arrays.asList("title", "description", "id_user");
    public JdbcProjectRepository(DataSource dataSource) {
        super(dataSource, tableName, uniqueField, columns);
    }

    @Override
    protected Project mapResultSet(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setTitle(resultSet.getString("title"));
        project.setDescription(resultSet.getString("description"));
        project.setIdUser(resultSet.getInt("id_user"));
        return project;
    }
}
