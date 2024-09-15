package erp.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
	 * Launch the application.
	 */

/**
	 * Create the frame.
	 */

//		JPanel panel_9 = new JPanel();
//		panel_9.setBounds(10, 29, 709, 258);
//		panel_5.add(panel_9);
//		panel_9.setLayout(null);
//
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import erp.student.StudentData;
		
		public class MainApp extends JFrame {
			private JFrame frame;
			private JPanel panel_3;
			private JPanel panel_4;
			private JPanel panel_5;
			private JPanel panel_6;
			private JPanel panel_8;
			private CardLayout c1;
			private JTable table;
			private DefaultTableModel model;
			private JTable table2;
			private DefaultTableModel model2;
			private JTable table3;
			private DefaultTableModel model3;
			private Connection con;
		
			private JTextField textField;
			private JTextField textField_1;
			private JTextField textField_2;
			private JTextField textField_3;
			private JTextField textField_4;
			private JTextField textField_5;
			private JTextField textField_6;
			private JTextField textField_7;
			private JTextField textField_8;
			private JTextField textField_9;
			private JTextField textField_10;
			private JTextField textField_13;
		
			private String Name;
			private String Rollnumber;
			private String Contact;
			private String EmailId;
			private String DOB;
			private String Course;
			private String CourseCode;
			private String CourseName;
			private String SemesterYear;
		
			public static void main(String[] args) {
				EventQueue.invokeLater(() -> {
					try {
						MainApp window = new MainApp("Dhiraj");
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		
			public MainApp(String adminName) {
				this.Name = adminName;
				initialize();
			}
		
			private void initialize() {
				frame = new JFrame();
				frame.setBounds(100, 100, 800, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				c1 = new CardLayout();
				panel_3 = new JPanel(c1);
				frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		
				panel_4 = new JPanel();
				panel_5 = new JPanel();
				panel_6 = new JPanel();
				panel_8 = new JPanel();
				panel_3.add(panel_4, "addcourse");
				panel_3.add(panel_5, "studdetails");
				panel_3.add(panel_6, "feedback");
				panel_3.add(panel_8, "studentdetails");
		
				// Initialize models and tables
				model = new DefaultTableModel(new Object[]{"Name", "Course", "Roll_No", "DateOfBirth", "Contact", "EmailID"}, 0);
				table = new JTable(model);
				panel_5.add(new JScrollPane(table));
		
				model2 = new DefaultTableModel(new Object[]{"CourseCode", "CourseName", "SemesterorYear"}, 0);
				table2 = new JTable(model2);
				panel_4.add(new JScrollPane(table2));
		
				model3 = new DefaultTableModel(new Object[]{"CourseName", "Comment"}, 0);
				table3 = new JTable(model3);
				panel_6.add(new JScrollPane(table3));
		
				// Create buttons and add action listeners
				JButton btnNewButton_1 = new JButton("Show Students");
				JButton btnNewButton_2 = new JButton("Show Feedback");
				JButton btnNewButton_3 = new JButton("Add Admin");
				JButton btnNewButton_4 = new JButton("Add Course");
				JButton btnNewButton_4_1 = new JButton("Delete Course");
				JButton btnNewButton_5 = new JButton("Add Student");
				JButton btnNewButton_5_1 = new JButton("Update Student");
				JButton btnNewButton_5_1_1 = new JButton("Delete Student");
				JButton btnNewButton_7 = new JButton("Find Student");
		
				panel_5.add(btnNewButton_5);
				panel_5.add(btnNewButton_5_1);
				panel_5.add(btnNewButton_5_1_1);
				panel_4.add(btnNewButton_4);
				panel_4.add(btnNewButton_4_1);
				panel_6.add(btnNewButton_2);
				panel_3.add(btnNewButton_1, BorderLayout.NORTH);
				panel_3.add(btnNewButton_3, BorderLayout.SOUTH);
		
				// Action Listeners
				btnNewButton_1.addActionListener(e -> {
					c1.show(panel_3, "studdetails");
					model.setRowCount(0);
					showAllStudent();
					panel_5.invalidate();
					panel_5.validate();
					panel_5.repaint();
				});
		
				btnNewButton_2.addActionListener(e -> {
					c1.show(panel_3, "feedback");
					model3.setRowCount(0);
					showFeedback();
					panel_6.invalidate();
					panel_6.validate();
					panel_6.repaint();
				});
		
				btnNewButton_3.addActionListener(e -> c1.show(panel_3, "addadmin"));
		
				btnNewButton_5.addActionListener(e -> {
					if (addStudent()) {
						JOptionPane.showMessageDialog(null, "Student Added!!");
						model.setRowCount(0);
						showAllStudent();
						cleartextField();
						panel_5.invalidate();
						panel_5.validate();
						panel_5.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Internal Error");
					}
				});
		
				btnNewButton_5_1_1.addActionListener(e -> {
					if (updateStudent()) {
						JOptionPane.showMessageDialog(null, "Student Updated!!");
						model.setRowCount(0);
						showAllStudent();
						cleartextField();
						panel_5.invalidate();
						panel_5.validate();
						panel_5.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Internal Error");
					}
				});
		
				btnNewButton_5_1.addActionListener(e -> {
					if (deleteStudent()) {
						JOptionPane.showMessageDialog(null, "Student Deleted!!");
						model.setRowCount(0);
						showAllStudent();
						cleartextField();
						panel_5.invalidate();
						panel_5.validate();
						panel_5.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Student Not Found");
						cleartextField();
						panel_8.invalidate();
						panel_8.validate();
						panel_8.repaint();
					}
				});
		
				btnNewButton_4.addActionListener(e -> {
					if (addCourse()) {
						JOptionPane.showMessageDialog(null, "Course Added!!");
						model2.setRowCount(0);
						showAllCourses();
						cleartextField();
						panel_4.invalidate();
						panel_4.validate();
						panel_4.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Internal Error");
					}
				});
		
				btnNewButton_4_1.addActionListener(e -> {
					if (deleteCourse()) {
						JOptionPane.showMessageDialog(null, "Course Deleted!!");
						model2.setRowCount(0);
						showAllCourses();
						cleartextField();
						panel_4.invalidate();
						panel_4.validate();
						panel_4.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Course Not Found");
						cleartextField();
						panel_8.invalidate();
						panel_8.validate();
						panel_8.repaint();
					}
				});
		
				btnNewButton_7.addActionListener(e -> {
					panel_8.removeAll();
					StudentData s = new StudentData();
					if (s.getStudentDetails(textField_13.getText())) {
						JLabel lblNewLabel_8 = new JLabel("Name  : ");
						lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8.setBounds(238, 54, 101, 31);
						panel_8.add(lblNewLabel_8);
		
						JLabel lblNewLabel_8_1 = new JLabel("Course. :  ");
						lblNewLabel_8_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_1.setBounds(225, 91, 93, 30);
						panel_8.add(lblNewLabel_8_1);
		
						JLabel lblNewLabel_8_2 = new JLabel("Roll No.  :  ");
						lblNewLabel_8_2.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_2.setBounds(219, 131, 106, 31);
						panel_8.add(lblNewLabel_8_2);
		
						JLabel lblNewLabel_8_3 = new JLabel("Date of Birth  :  ");
						lblNewLabel_8_3.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_3.setBounds(177, 172, 148, 31);
						panel_8.add(lblNewLabel_8_3);
		
						JLabel lblNewLabel_8_4 = new JLabel("Phone No. :  ");
						lblNewLabel_8_4.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_4.setBounds(204, 213, 124, 31);
						panel_8.add(lblNewLabel_8_4);
		
						JLabel lblNewLabel_8_5 = new JLabel("Email Id :  ");
						lblNewLabel_8_5.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_5.setBounds(225, 254, 101, 31);
						panel_8.add(lblNewLabel_8_5);
		
						JLabel lblNewLabel_8_6 = new JLabel(s.getName());
						lblNewLabel_8_6.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_6.setBounds(360, 54, 129, 31);
						panel_8.add(lblNewLabel_8_6);
		
						JLabel lblNewLabel_8_1_1 = new JLabel(s.getCourse());
						lblNewLabel_8_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_1_1.setBounds(360, 91, 142, 30);
						panel_8.add(lblNewLabel_8_1_1);
		
						JLabel lblNewLabel_8_2_1 = new JLabel(s.getRollNumber());
						lblNewLabel_8_2_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_2_1.setBounds(360, 131, 142, 31);
						panel_8.add(lblNewLabel_8_2_1);
		
						JLabel lblNewLabel_8_3_1 = new JLabel(s.getDOB());
						lblNewLabel_8_3_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_3_1.setBounds(360, 172, 129, 31);
						panel_8.add(lblNewLabel_8_3_1);
		
						JLabel lblNewLabel_8_4_1 = new JLabel(s.getContact());
						lblNewLabel_8_4_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_4_1.setBounds(360, 213, 129, 31);
						panel_8.add(lblNewLabel_8_4_1);
		
						JLabel lblNewLabel_8_5_1 = new JLabel(s.getEmail());
						lblNewLabel_8_5_1.setFont(new Font("Dialog", Font.PLAIN, 20));
						lblNewLabel_8_5_1.setBounds(360, 254, 168, 31);
						panel_8.add(lblNewLabel_8_5_1);
		
						cleartextField();
						panel_8.invalidate();
						panel_8.validate();
						panel_8.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Student Not Found");
						cleartextField();
						panel_8.invalidate();
						panel_8.validate();
						panel_8.repaint();
					}
				});
			}
		
			protected boolean addStudent() {
				StudentData s = new StudentData();
		
				Name = textField_4.getText();
				Rollnumber = textField_5.getText();
				Contact = textField_6.getText();
				EmailId = textField_7.getText();
				DOB = textField_8.getText();
				Course = textField_9.getText();
		
				s.setName(Name);
				s.setContact(Contact);
				s.setCourse(Course);
				s.setDOB(DOB);
				s.setEmail(EmailId);
				s.setRollNumber(Rollnumber);
		
				return s.insertStudentDetails();
			}
		
			protected boolean updateStudent() {
				StudentData s = new StudentData();
		
				Name = textField_4.getText();
				Rollnumber = textField_5.getText();
				Contact = textField_6.getText();
				EmailId = textField_7.getText();
				DOB = textField_8.getText();
				Course = textField_9.getText();
		
				s.setName(Name);
				s.setContact(Contact);
				s.setCourse(Course);
				s.setDOB(DOB);
				s.setEmail(EmailId);
				s.setRollNumber(Rollnumber);
		
				return s.updateStudentDetails(Rollnumber);
			}
		
			protected boolean deleteStudent() {
				StudentData s = new StudentData();
		
				String Rollnumber = textField_10.getText();
		
				return s.deleteStudentDetails(Rollnumber);
			}
		
			public void showAllStudent() {
				String query = "SELECT Name, Course, Roll_No, DateOfBirth, Contact, EmailID FROM student";
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
		
					while (rs.next()) {
						String Name = rs.getString("Name");
						String Course = rs.getString("Course");
						String Roll_No = rs.getString("Roll_No");
						String DateOfBirth = rs.getString("DateOfBirth");
						String Contact = rs.getString("Contact");
						String EmailID = rs.getString("EmailID");
		
						String[] data = {Name, Course, Roll_No, DateOfBirth, Contact, EmailID};
		
						model.addRow(data);
					}
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			public void showAllCourses() {
				String query = "SELECT CourseCode, CourseName, SemesterorYear FROM Courses";
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
		
					while (rs.next()) {
						String CourseCode = rs.getString("CourseCode");
						String CourseName = rs.getString("CourseName");
						String SemesterYear = rs.getString("SemesterorYear");
		
						String[] data1 = {CourseCode, CourseName, SemesterYear};
		
						model2.addRow(data1);
					}
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			protected boolean addCourse() {
				Course c = new Course();
		
				CourseCode = textField.getText();
				CourseName = textField_1.getText();
				SemesterYear = textField_2.getText();
		
				c.setCourseCode(CourseCode);
				c.setCourseName(CourseName);
				c.setSemesterYear(SemesterYear);
		
				return c.insertCourseDetails();
			}
		
			protected boolean deleteCourse() {
				Course c = new Course();
		
				String CourseCode = textField_3.getText();
		
				return c.deleteCourseDetails(CourseCode);
			}
		
			public void showFeedback() {
				String query = "SELECT CourseName, Comment FROM Feedback";
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
		
					while (rs.next()) {
						String CourseName = rs.getString("CourseName");
						String Feedback = rs.getString("Comment");
		
						String[] data1 = {CourseName, Feedback};
		
						model3.addRow(data1);
					}
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			public void cleartextField() {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
				textField_8.setText(null);
				textField_9.setText(null);
				textField_10.setText(null);
				textField_13.setText(null);
			}
		
		}
		