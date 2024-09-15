package erp.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import erp.database.DataBaseConnection;

public class Feedback {
    private String courseName;
    private String feedback;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean insertFeedback() {
        String query = "INSERT INTO Feedback (CourseName, Comment) VALUES (?, ?)";
        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pStatement = con.prepareStatement(query)) {

            pStatement.setString(1, getCourseName());
            pStatement.setString(2, getFeedback());

            pStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
