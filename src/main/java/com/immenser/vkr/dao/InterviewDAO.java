package com.immenser.vkr.dao;
import com.immenser.vkr.config.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class InterviewDAO extends Database{

    public ResultSet getInterwies(Calendar date) throws SQLException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM candidates JOIN interviews ON candidates.candidate_id = interviews.candidate_id JOIN positions ON interviews.position_id = positions.position_id WHERE interview_date=? ORDER BY interview_time";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setDate(1, new java.sql.Date(date.getTimeInMillis()));
            System.out.println(new java.sql.Date(date.getTimeInMillis()));
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
