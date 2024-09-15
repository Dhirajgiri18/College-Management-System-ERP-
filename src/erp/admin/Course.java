package erp.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import erp.database.DataBaseConnection;

public class Course {
    private String courseCode;
    private String courseName;
    private String semesterYear;

    // Constructor for initialization
    public Course(String courseCode, String courseName, String semesterYear) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.semesterYear = semesterYear;
    }

    // Default constructor
    public Course() {
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    private Connection getConnection() {
        return DataBaseConnection.getConnection();
    }

    public boolean insertCourseDetails() {
        String query = "INSERT INTO Courses (CourseCode, CourseName, SemesterYear) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
                PreparedStatement pStatement = con.prepareStatement(query)) {

            pStatement.setString(1, getCourseCode());
            pStatement.setString(2, getCourseName());
            pStatement.setString(3, getSemesterYear());

            pStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourseDetails(String courseCode) {
        String query = "DELETE FROM Courses WHERE CourseCode = ?";
        try (Connection con = getConnection();
                PreparedStatement pStatement = con.prepareStatement(query)) {

            pStatement.setString(1, courseCode);
            int rowDeleted = pStatement.executeUpdate();
            return rowDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
