package org.example.mytodo.manager;

import org.example.mytodo.db.DBConnectionProvider;
import org.example.mytodo.model.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatusManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Status getStatusById(int id) {
        String sql = "SELECT * FROM status WHERE id = " + id;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                return Status.builder()
                        .id(id)
                        .status(resultSet.getNString("status"))
                        .build();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
