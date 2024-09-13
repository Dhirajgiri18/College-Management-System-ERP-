package erp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	
	static Connection con=null;
	static final String url="jdbc:mysql://localhost:3306/erp";
	static final String uname="root";
	static final String password="Dhiraj@123";
	
	
	public static Connection getConnection()
	{
		if(con!=null)
		{
			return con;
		}
		else
		{
			try
			{
				
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,uname,password);
				System.out.print("DataBase Connected");
				return con;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
				return con;
			}
		}
	}
	public static boolean checkconnection() 
	{
		
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,uname,password);
			System.out.print("DataBase Connected");
			return true;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return false;
		}
	}
	public static void  closeConnection() 
	{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

