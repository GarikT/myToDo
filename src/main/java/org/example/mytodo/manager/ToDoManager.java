package org.example.mytodo.manager;

import org.example.mytodo.db.DBConnectionProvider;
import org.example.mytodo.model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private StatusManager statusManager = new StatusManager();

    public void add(ToDo todo) {
        String sql = "INSERT INTO todo(title, created_date, finish_date, user_id, status_id)VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todo.getTitle());
            java.util.Date utilDate = todo.getCreateDate();
            java.sql.Date sqlCreateDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(2, sqlCreateDate);
            utilDate = todo.getFinishDate();
            java.sql.Date sqlFinishDate = null;
            if(utilDate != null) {
                sqlFinishDate = new java.sql.Date(utilDate.getTime());
                preparedStatement.setDate(3, sqlFinishDate);
            }else{
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }

            preparedStatement.setInt(4, todo.getUser().getId());
            preparedStatement.setInt(5, todo.getStatus().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                todo.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM todo WHERE id = " + id;
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(ToDo todo) {
        System.out.println(todo);
        String sql = "UPDATE todo SET title = ?, finish_date = ?, status_id = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, todo.getTitle());
            java.util.Date utilDate = todo.getFinishDate();
            if(utilDate != null) {
                java.sql.Date sqlFinishDate = new java.sql.Date(utilDate.getTime());
                preparedStatement.setDate(2, sqlFinishDate);
            }else{
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }
            preparedStatement.setInt(3, todo.getStatus().getId());
            preparedStatement.setInt(4, todo.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ToDo getToDoById(int id) {
        String sql = "SELECT * FROM todo WHERE id = " + id;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                return ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(statusManager.getStatusById(resultSet.getInt("status_id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<ToDo> getToDoByUserId(int id){
        String sql = "select * from todo where user_id = " + id;
        List<ToDo> todos = new ArrayList<>();
        try( Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                todos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(id))
                        .status(statusManager.getStatusById(resultSet.getInt("status_id")))
                        .build());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return todos;
    }

}
