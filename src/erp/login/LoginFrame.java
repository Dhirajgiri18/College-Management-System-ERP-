package erp.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import erp.admin.MainApp; // Updated import
import erp.database.DataBaseConnection;
import erp.student.Student;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField adminUserField;
	private JPasswordField adminPassField;
	private JTextField studentUserField;
	private JPasswordField studentPassField;
	static LoginFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				if (DataBaseConnection.checkConnection()) {
					frame = new LoginFrame();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Start the Database Server first", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() throws IOException {
		setTitle("ERP System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1047, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139, 220));
		panel_1.setBounds(0, 23, 1031, 101);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("ERP SYSTEM");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Poppins ExtraBold", Font.PLAIN, 40));
		lblNewLabel_7.setBounds(329, 11, 374, 79);
		panel_1.add(lblNewLabel_7);

		JPanel panel = createLoginPanel("ADMIN", "adminlogin.png", 73, 185, true);
		contentPane.add(panel);

		JPanel panel_2 = createLoginPanel("STUDENT", "studentlogin.png", 640, 185, false);
		contentPane.add(panel_2);

		JLabel lblNewLabel_6 = new JLabel(new ImageIcon("assets/backgroundimage2.jpg"));
		lblNewLabel_6.setBounds(0, -2, 1031, 613);
		contentPane.add(lblNewLabel_6);
	}

	private JPanel createLoginPanel(String role, String iconPath, int x, int y, boolean isAdmin) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 80));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setLayout(null);
		panel.setBounds(x, y, 330, 381);

		JLabel lblRole = new JLabel(role);
		lblRole.setForeground(Color.WHITE);
		lblRole.setFont(new Font("Poppins SemiBold", Font.PLAIN, 25));
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setBounds(10, 11, 310, 52);
		panel.add(lblRole);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(new Color(34, 167, 240));
		btnLogin.setBorderPainted(false);
		btnLogin.setFont(new Font("Poppins SemiBold", Font.PLAIN, 20));
		btnLogin.setBounds(10, 305, 310, 52);
		panel.add(btnLogin);

		JLabel lblUserId = new JLabel("UserID");
		lblUserId.setFont(new Font("Poppins Medium", Font.BOLD, 16));
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(22, 158, 71, 33);
		panel.add(lblUserId);

		JTextField userField = new JTextField();
		userField.setFont(new Font("Poppins", Font.PLAIN, 12));
		userField.setBounds(93, 159, 206, 33);
		panel.add(userField);
		userField.setColumns(10);

		JPasswordField passField = new JPasswordField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passField.setBounds(93, 230, 206, 33);
		panel.add(passField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Poppins Medium", Font.BOLD, 16));
		lblPassword.setBounds(8, 229, 95, 33);
		panel.add(lblPassword);

		JLabel lblIcon = new JLabel(new ImageIcon(iconPath));
		lblIcon.setBounds(123, 65, 83, 83);
		panel.add(lblIcon);

		if (isAdmin) {
			adminUserField = userField;
			adminPassField = passField;
		} else {
			studentUserField = userField;
			studentPassField = passField;
		}

		btnLogin.addActionListener(e -> handleLogin(role, userField, passField, isAdmin));

		return panel;
	}

	private void handleLogin(String role, JTextField userField, JPasswordField passField, boolean isAdmin) {
		try {
			ResultSet rs = checkCredentials(role, userField.getText(), new String(passField.getPassword()));
			if (rs != null && rs.next()) {
				if (isAdmin) {
					// For Admin
					MainApp admin = new MainApp(rs.getString(1)); // Create MainApp instance
					admin.setVisible(true); // Show MainApp
				} else {
					// For Student
					Student student = new Student(rs.getString(1), rs.getString(2)); // Create Student instance
					student.setVisible(true); // Show Student
				}
				frame.dispose(); // Close LoginFrame
	
				// Create a new JFrame to display the welcome message
				JFrame welcomeFrame = new JFrame("Welcome");
				welcomeFrame.setSize(300, 200);
				welcomeFrame.setLocationRelativeTo(null);
				welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
				JLabel wel_label = new JLabel("Welcome: " + rs.getString(1), SwingConstants.CENTER);
				wel_label.setFont(new Font("Poppins", Font.BOLD, 16));
				welcomeFrame.add(wel_label);
				welcomeFrame.setVisible(true); // Show welcome message
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect user-id or password. Try again with correct details.");
				clearFields(isAdmin);
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}
	

	private ResultSet checkCredentials(String userType, String userId, String password) {
		Connection con = DataBaseConnection.getConnection();
		String query = userType.equals("Admin")
				? "SELECT Name FROM Admin WHERE User_Id=? AND Password=?"
				: "SELECT Name, Roll_No FROM Student WHERE Student_Id=? AND Password=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, userId);
			ps.setString(2, password);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void clearFields(boolean isAdmin) {
		if (isAdmin) {
			adminUserField.setText(null);
			adminPassField.setText(null);
		} else {
			studentUserField.setText(null);
			studentPassField.setText(null);
		}
	}
}
