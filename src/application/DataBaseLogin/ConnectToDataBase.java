package application.DataBaseLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* This class get the input login and make a connection to data base 
and authorization to the user */ 
public class ConnectToDataBase {

	public ConnectToDataBase() {
		
	}
	//Establish a connection to cabinet_medical data base 
	//We call this method every time we need to do an operation into the data base
	private static Connection getConnection(){
		try {
			String url = "jdbc:mysql://localhost/cabinet_medical";
			String username = "root";
			String password = "";
			Connection connect= DriverManager.getConnection(url, username, password);
			return connect;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//get the connection object returned by the GetConnection method a check out
	public static boolean is_a_user(UserLogin user)throws Exception{
		Connection connection = getConnection();
		boolean is_a_User = false;
		try {
			String sql_Statement = "SELECT UserName,Password FROM user";
			PreparedStatement select = connection.prepareStatement(sql_Statement);
			ResultSet result = select.executeQuery();
			
			while(result.next() ){
				boolean find_Match = result.getString(1).equals(user.getUserName()) &&
									 result.getString(2).equals(user.getPassword());
				if (find_Match) {
					is_a_User =true;
					break;
				}
			}
			connection.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is_a_User;
	}
	
	

}
