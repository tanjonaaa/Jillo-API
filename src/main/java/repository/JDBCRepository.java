package repository;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JDBCRepository<Model>{
    protected DataSource dataSource;
    protected String tableName;
    protected  String uniqueField;
    protected List<String> columns;
    public JDBCRepository(DataSource dataSource, String tableName, String uniqueField,List<String> columns) {
        this.dataSource = dataSource;
        this.tableName = tableName;
        this.columns = columns;
        this.uniqueField = uniqueField;
    }

    protected abstract Model mapResultSet(ResultSet resultSet) throws SQLException;

    public List<Model> all(){
        List<Model> results = new ArrayList<>();
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \""+this.tableName+"\"";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                results.add(this.mapResultSet(resultSet));
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return results;
    }

    public Model oneById(int id){
        Model result = null;
        try {
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \""+this.tableName+"\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                result = mapResultSet(resultSet);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void save(Model model){
        try{
            Connection connection = this.dataSource.getConnection();
            String sql = "INSERT INTO \""+this.tableName+"\" ("+this.getFieldsList()+") " +
                    "VALUES ("+this.getFieldsAsQuestionMarks()+")";

            PreparedStatement statement = connection.prepareStatement(sql);

            this.populatePreparedStatement(statement, model);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Model oneByUniqueColumn(Object value){
        Model result = null;
        try {
            Connection connection = this.dataSource.getConnection();
            String sql = "SELECT * FROM \""+this.tableName+"\" WHERE "+this.uniqueField+" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setObject(1, value);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                result = mapResultSet(resultSet);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void update(Model model, int id) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "UPDATE \""+this.tableName+"\" " +
                    "SET "+this.getFieldsWithQuestionMarks()+" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            this.populatePreparedStatement(statement, model);
            statement.setInt(this.columns.size() + 1, id);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try{
            Connection connection = dataSource.getConnection();
            String sql = "DELETE FROM \""+this.tableName+"\" WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    protected String getFieldsList(){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if(i != this.columns.size() - 1){
                list.append(this.columns.get(i)).append(", ");
            }else {
                list.append(this.columns.get(i));
            }
        }
        return list.toString();
    }

    protected String getFieldsAsQuestionMarks(){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if(i != this.columns.size() - 1){
                list.append("?, ");
            }else {
                list.append("?");
            }
        }
        return list.toString();
    }

    protected String getFieldsWithQuestionMarks(){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.columns.size(); i++) {
            if(i != this.columns.size() - 1){
                list.append(this.columns.get(i)).append(" = ?, ");
            }else {
                list.append(this.columns.get(i)).append(" = ?");
            }
        }
        return list.toString();
    }

    protected String ucFirst(String entry){
        return entry.substring(0, 1).toUpperCase() + entry.substring(1);
    }

    protected String getGetterName(String field){
        return "get"+this.ucFirst(this.snakeToCamelCase(field));
    }

    protected Method getGetter(Model model, String field) throws NoSuchMethodException{
        return model.getClass().getMethod(getGetterName(field));
    }
    protected void populatePreparedStatement(PreparedStatement statement, Model model){
        for (int i = 0; i < this.columns.size(); i++) {
            try{
                Method getter = this.getGetter(model, this.columns.get(i));
                System.out.println(getter);
                statement.setObject(i+1, getter.invoke(model));

            }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected String snakeToCamelCase(String toConvert){
        if(toConvert.contains("_")){
            List<String> splitted = Arrays.asList(toConvert.split("_"));
            List<String> mutated = splitted.stream()
                    .map((e) -> {
                        if(splitted.indexOf(e) > 0){
                            return this.ucFirst(e);
                        }else {
                            return e;
                        }
                    }).toList();
            return String.join("", mutated);
        }
        return this.ucFirst(toConvert);
    }
}
