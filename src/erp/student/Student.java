package erp.student;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import erp.database.DataBaseConnection;

public class Student extends JFrame {

    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_2;
    private String name;
    private String rollno;
    private String courseName;
    private String feedback;
    private CardLayout cardLayout;
    private JTextField feedbackTextField;
    private StudentData studentData;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                if (DataBaseConnection.checkConnection()) {
                    Student frame = new Student(null, null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Start the Database Server first", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Student(String name, String rollno) {
        this.name = name;
        this.rollno = rollno;
        this.studentData = new StudentData();
        studentData.getStudentDetails(rollno);

        setTitle("Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1047, 650);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initializeUIComponents();
    }

    private void initializeUIComponents() {
        createHeaderPanel();
        createSidebarPanel();
        createContentPanel();
    }

    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(new Color(0, 139, 139, 220));
        headerPanel.setBounds(0, 11, 1031, 101);
        contentPane.add(headerPanel);

        JLabel headerLabel = new JLabel("STUDENT");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Poppins ExtraBold", Font.BOLD, 40));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(372, 11, 338, 79);
        headerPanel.add(headerLabel);
    }

    private void createSidebarPanel() {
        panel = new JPanel();
        panel.setBounds(0, 111, 302, 500);
        panel.setBackground(new Color(23, 35, 51));
        contentPane.add(panel);
        panel.setLayout(null);

        panel_2 = new JPanel();
        panel_2.setBackground(new Color(23, 35, 51));
        panel_2.setBounds(0, 0, 302, 110);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel iconLabel = new JLabel("");
        iconLabel.setIcon(new ImageIcon(Student.class.getResource("/assets/icons8-administrator-male-skin-type-7-48.png")));
        iconLabel.setBounds(36, 34, 53, 43);
        panel_2.add(iconLabel);

        JLabel greetingLabel = new JLabel("Hi, " + name);
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 18));
        greetingLabel.setBounds(99, 34, 164, 43);
        panel_2.add(greetingLabel);

        JButton personalDetailsButton = createSidebarButton("Personal Details", 121);
        JButton enrolledCoursesButton = createSidebarButton("Enrolled Courses", 204);
        JButton feedbackButton = createSidebarButton("Feedback", 280);

        panel.add(personalDetailsButton);
        panel.add(enrolledCoursesButton);
        panel.add(feedbackButton);

        personalDetailsButton.addActionListener(e -> cardLayout.show(contentPane, "PersonalDetails"));
        enrolledCoursesButton.addActionListener(e -> {
            cardLayout.show(contentPane, "EnrolledCourse");
            updateCourseDetails();
        });
        feedbackButton.addActionListener(e -> cardLayout.show(contentPane, "Feedback"));
    }

    private JButton createSidebarButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(23, 35, 51));
        button.setBorderPainted(false);
        button.setBounds(0, yPosition, 302, 65);
        return button;
    }

    private void createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(302, 111, 729, 500);
        contentPane.add(contentPanel);
        contentPanel.setLayout(new CardLayout(0, 0));
        cardLayout = (CardLayout) contentPanel.getLayout();

        JPanel personalDetailsPanel = new JPanel();
        personalDetailsPanel.setBackground(Color.WHITE);
        contentPanel.add(personalDetailsPanel, "PersonalDetails");
        personalDetailsPanel.setLayout(null);
        personalDetailsPanel.add(createPersonalDetailsPanel());

        JPanel enrolledCoursesPanel = new JPanel();
        enrolledCoursesPanel.setBackground(Color.WHITE);
        contentPanel.add(enrolledCoursesPanel, "EnrolledCourse");
        enrolledCoursesPanel.setLayout(null);

        JLabel courseLabel = new JLabel("Courses");
        courseLabel.setBounds(0, 0, 739, 510);
        courseLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 20));
        courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseLabel.setVerticalAlignment(SwingConstants.TOP);
        enrolledCoursesPanel.add(courseLabel);

        JPanel courseDetailsPanel = new JPanel();
        courseDetailsPanel.setBounds(20, 63, 699, 391);
        enrolledCoursesPanel.add(courseDetailsPanel);
        courseDetailsPanel.setLayout(null);

        JLabel courseDetailsLabel = new JLabel();
        courseDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseDetailsLabel.setFont(new Font("Poppins Medium", Font.BOLD, 15));
        courseDetailsLabel.setBounds(253, 5, 202, 47);
        courseDetailsPanel.add(courseDetailsLabel);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBackground(Color.WHITE);
        contentPanel.add(feedbackPanel, "Feedback");
        feedbackPanel.setLayout(null);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        submitButton.setBounds(302, 268, 157, 43);
        feedbackPanel.add(submitButton);

        feedbackTextField = new JTextField();
        feedbackTextField.setBounds(112, 143, 544, 102);
        feedbackPanel.add(feedbackTextField);
        feedbackTextField.setColumns(10);

        JLabel feedbackLabel = new JLabel("Enter Feedback");
        feedbackLabel.setFont(new Font("Dialog", Font.PLAIN, 26));
        feedbackLabel.setBounds(282, 88, 199, 34);
        feedbackPanel.add(feedbackLabel);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 139, 139, 220));
        footerPanel.setBounds(0, 400, 729, 100);
        feedbackPanel.add(footerPanel);

        submitButton.addActionListener(e -> {
            if (submitFeedback()) {
                JOptionPane.showMessageDialog(null, "Feedback Submitted!!");
                feedbackTextField.setText(null);
            } else {
                JOptionPane.showMessageDialog(null, "Internal Error");
            }
        });
    }

    private JPanel createPersonalDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel detailsLabel = new JLabel("DETAILS");
        detailsLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        detailsLabel.setBounds(266, 26, 148, 48);
        panel.add(detailsLabel);

        JLabel nameLabel = new JLabel("Name: " + studentData.getName());
        nameLabel.setBounds(63, 98, 600, 28);
        panel.add(nameLabel);

        JLabel rollnoLabel = new JLabel("Roll Number: " + studentData.getRollNumber());
        rollnoLabel.setBounds(63, 136, 600, 28);
        panel.add(rollnoLabel);

        JLabel courseLabel = new JLabel("Course: " + studentData.getCourse());
        courseLabel.setBounds(63, 174, 600, 28);
        panel.add(courseLabel);

        return panel;
    }

    private void updateCourseDetails() {
		// Fetch the course details from studentData
		String courseDetails = studentData.getCourse(); // Replace with actual method to get course details
	
		// Update the UI component with course details
		JLabel courseDetailsLabel = (JLabel) ((JPanel) ((JPanel) getContentPane().getComponent(1))
				.getComponent(1)).getComponent(0); // Adjust this path based on your actual component hierarchy
		courseDetailsLabel.setText("<html>" + courseDetails + "</html>"); // Use HTML formatting if needed
	}
	

    private boolean submitFeedback() {
        feedback = feedbackTextField.getText();
        return studentData.submitFeedback(rollno, feedback);
    }
}
