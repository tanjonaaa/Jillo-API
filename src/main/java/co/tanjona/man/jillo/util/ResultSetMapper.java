package co.tanjona.man.jillo.util;

import co.tanjona.man.jillo.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    public static User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    public static Project mapResultSetToProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setTitle(resultSet.getString("title"));
        project.setDescription(resultSet.getString("description"));
        project.setIdUser(resultSet.getInt("id_user"));
        return project;
    }

    public static Task mapResultSetToTask (ResultSet resultSet) throws SQLException{
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setTitle(resultSet.getString("title"));
        task.setDescription(resultSet.getString("description"));
        task.setDeadline(resultSet.getTimestamp("deadline"));
        task.setIdUser(resultSet.getInt("id_user"));
        task.setIdProject(resultSet.getInt("id_project"));
        task.setIdStatus(resultSet.getInt("id_status"));
        return task;
    }

    public static Status mapResultSetToStatus (ResultSet resultSet) throws SQLException {
        Status status= new Status();
        status.setId(resultSet.getInt("id"));
        status.setName(resultSet.getString("name"));
        return status;
    }

    public static ToBeIn mapResultSetToToBeIn(ResultSet resultSet) throws SQLException {
        ToBeIn toBeIn = new ToBeIn();

        toBeIn.setId(resultSet.getInt("id"));
        toBeIn.setCreatedAt(resultSet.getTimestamp("created_at"));
        toBeIn.setIdUser(resultSet.getInt("id_user"));
        toBeIn.setIdProject(resultSet.getInt("id_project"));

        return toBeIn;
    }

    public static ToBeAssignedTo mapResultSetToToBeAssignedTo(ResultSet resultSet) throws SQLException{
        ToBeAssignedTo toBeAssignedTo = new ToBeAssignedTo();

        toBeAssignedTo.setId(resultSet.getInt("id"));
        toBeAssignedTo.setCreatedAt(resultSet.getTimestamp("created_at"));
        toBeAssignedTo.setIdUser(resultSet.getInt("id_user"));
        toBeAssignedTo.setIdTask(resultSet.getInt("id_task"));

        return toBeAssignedTo;
    }
}
