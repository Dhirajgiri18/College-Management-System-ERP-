package erp.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import erp.database.DataBaseConnection;

public class StudentData {

	private String rollNumber;
	private String name;
	private String course;
	private String dob;
	private String contact;
	private String userId;
	private String emailId;
	private String password;

	// Setters
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setDOB(String dob) {
		this.dob = dob;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setEmail(String email) {
		this.emailId = email;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getCourse() {
		return course;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public String getDOB() {
		return dob;
	}

	public String getContact() {
		return contact;
	}

	public String getEmail() {
		return emailId;
	}

	public String getUserId() {
		return userId;
	}

	// Generate User ID and Password
	public String generateUserId() {
		userId = name + rollNumber;
		return userId;
	}

	public String generatePassword() {
		password = name + rollNumber;
		return password;
	}

	// Database connection
	private Connection getConnection() {
		return DataBaseConnection.getConnection();
	}

	// Fetch student details
	public boolean getStudentDetails(String rollNumber) {
		String query = "SELECT * FROM Student WHERE Roll_No = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, rollNumber);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					setName(rs.getString("Name"));
					setCourse(rs.getString("Course"));
					setRollNumber(rs.getString("Roll_No"));
					setDOB(rs.getString("DateOfBirth"));
					setContact(rs.getString("Contact"));
					setEmail(rs.getString("EmailID"));
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Insert student details
	public boolean insertStudentDetails() {
		String query = "INSERT INTO Student (Name, Course, Roll_No, DateOfBirth, Contact, EmailID, Student_Id, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, getName());
			ps.setString(2, getCourse());
			ps.setString(3, getRollNumber());
			ps.setString(4, getDOB());
			ps.setString(5, getContact());
			ps.setString(6, getEmail());
			ps.setString(7, generateUserId());
			ps.setString(8, generatePassword());

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update student details
	public boolean updateStudentDetails(String rollNumber) {
		String query = "UPDATE Student SET Name = ?, Course = ?, DateOfBirth = ?, Contact = ?, EmailID = ?, Student_Id = ?, Password = ? WHERE Roll_No = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, getName());
			ps.setString(2, getCourse());
			ps.setString(3, getDOB());
			ps.setString(4, getContact());
			ps.setString(5, getEmail());
			ps.setString(6, generateUserId());
			ps.setString(7, generatePassword());
			ps.setString(8, rollNumber);

			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Delete student details
	public boolean deleteStudentDetails(String rollNumber) {
		String query = "DELETE FROM Student WHERE Roll_No = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, rollNumber);

			int rowsDeleted = ps.executeUpdate();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Submit feedback
	public boolean submitFeedback(String rollno, String feedback) {
		String query = "INSERT INTO Feedback (Roll_No, Feedback) VALUES (?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, rollno);
			ps.setString(2, feedback);

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
