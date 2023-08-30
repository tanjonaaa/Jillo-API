package repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JDBCRelationnalRepository<Relation> extends JDBCRepository<Relation>{

    private List<String> foreignKeys;
    public JDBCRelationnalRepository(
            DataSource dataSource,
            String tableName,
            String uniqueField,
            List<String> columns,
            List<String> foreignKeys
    ) {
        super(dataSource, tableName, uniqueField, columns);
        this.foreignKeys = foreignKeys;
    }

    @Override
    protected abstract Relation mapResultSet(ResultSet resultSet) throws SQLException;

    public List<Relation> getByForeignKeys(List<Object> keys){
        List<Relation> relations = new ArrayList<>();
        String sql = "SELECT * FROM "+this.tableName+" WHERE "+this.getFieldsForAndConditions();
        System.out.println(sql);
        try(Connection connection = this.dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < keys.size(); i++) {
                System.out.println(keys.get(i));
                statement.setObject(i+1, keys.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                relations.add(this.mapResultSet(resultSet));
            }

            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return relations;
    }

    protected String getFieldsForAndConditions(){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.foreignKeys.size(); i++) {
            if(i != this.foreignKeys.size() - 1){
                list.append(this.foreignKeys.get(i)).append(" = ? AND ");
            }else {
                list.append(this.foreignKeys.get(i)).append(" = ?");
            }
        }
        return list.toString();
    }
}
