package application.ControlPanal.gestionEmployees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeTable {
	

    //Establish a connection to cabinet_medical data base 
	//We call this method every time we need to do an operation into the data base
	public static Connection getConnection(){
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
	public static void insert_Employee(Employee employee) throws SQLException {
		Connection connection_test= getConnection();
		
		String statemnet = "INSERT INTO employees("
												+ " `Prenom`,"
												+ " `Nom`,"
												+ " `Date_naissance`,"
												+ " `Sexe`, "
												+ " `Groupage`,"
												+ " `Salaire`,"
												+ " `Debut_Travaille`,"
												+ " `Telephone`) "
										+ "VALUES("
										+ "'"+employee.getPrenom()+"',"
										+ "'"+employee.getNom()+"',"
										+ "'"+employee.getDate_naissance()+"',"
										+ "'"+employee.getGender()+"',"
										+ "'"+employee.getGroupage()+"',"
										+ "'"+employee.getSalaire()+"',"
										+ "'"+employee.getDebut_travaille()+"',"
										+"'"+employee.getTelephone()+"')";
		PreparedStatement insert = connection_test.prepareStatement(statemnet);
		insert.executeUpdate();	
		connection_test.close();
	
	}
	//update Employee informations 
	public static int updateEmployee(Employee employee) throws SQLException {
		Connection connection = getConnection();
		String update_Statetment = "UPDATE employees SET "
													+ "Prenom=?,"
													+ "Nom=?,"
													+ "Date_naissance=?,"
													+ "Sexe=?,"
													+ "Groupage=?,"
													+ "Salaire=?,"
													+ "Debut_Travaille=?,"
													+ "Telephone=? "
													+ "WHERE U_ID=?";
		
		PreparedStatement statement = connection.prepareStatement(update_Statetment);
		statement.setString(1, employee.getPrenom());
		statement.setString(2,employee.getNom());
		statement.setString(3, employee.getDate_naissance());
		statement.setString(4, employee.getGender());
		statement.setString(5, employee.getGroupage());
		statement.setString(6, employee.getSalaire());
		statement.setString(7,employee.getDebut_travaille());
		statement.setString(8,employee.getTelephone());
		statement.setInt(9,employee.getUser_ID());
		int row = statement.executeUpdate();
		
		connection.close();
		return row;
	}
	
	//delete Employee informations
	public static int deleteEmployee(int id) throws SQLException {

		String delete_Statetment = "DELETE FROM employees WHERE U_ID=?";
		Connection connection = getConnection();
		PreparedStatement prepared_delete_statement = connection.prepareStatement(delete_Statetment);
		prepared_delete_statement.setInt(1, id);
		int row = prepared_delete_statement.executeUpdate();
		connection.close();
		return row;
	}
	
	public static ResultSet show_Employee_Table() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM `employees` WHERE 1 ORDER BY  Nom,Prenom";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	
	public static ResultSet show_suggestion(Employee person) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM `employees`"
							+ " WHERE Nom LIKE '"+person.getNom()+"%' OR Prenom LIKE '"+person.getPrenom()+"%' OR"
									+ "(Nom LIKE '"+person.getPrenom()+"%' OR Prenom LIKE '"+person.getNom()+"%')";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	
	// Search for Employee by id
	public static Employee getEmployee_DB_ID(int id) throws SQLException {
		Employee employee =  new Employee();
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM employees WHERE U_ID=?";
		PreparedStatement select = connection_test.prepareStatement(statement);
		select.setInt(1, id);
		ResultSet result = select.executeQuery();
		
		if (result.next()) {
			employee.setUser_ID(result.getInt(1));
			employee.setPrenom(result.getString(2));
			employee.setNom(result.getString(3));
			employee.setDate_naissance(result.getString(4));
			employee.setGender(result.getString(5));
			employee.setGroupage(result.getString(6));
			employee.setSalaire(result.getString(7));
			employee.setDebut_travaille(result.getString(8));
			employee.setTelephone(result.getString(9));
		}
		connection_test.close();
		return employee;
	}
	public static ResultSet show_Employee_Name() throws SQLException {
		Connection connection_test= getConnection();
		String statement = "SELECT U_ID,Prenom, Nom,Telephone FROM employees";
		PreparedStatement select = connection_test.prepareStatement(statement);
		ResultSet result = select.executeQuery();
		return result;
	}
	
	
	// Search for Employee by name
	public static Employee getEmployee_by_name(String firstName,String lastName) throws SQLException {
		Employee employee =  new Employee();
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM employees WHERE Prenom=? AND Nom=?";
		PreparedStatement select = connection_test.prepareStatement(statement);
		select.setString(1,firstName.toUpperCase());
		select.setString(2,lastName.toUpperCase());
		ResultSet result = select.executeQuery();
		if (result.next()) {
			employee.setUser_ID(result.getInt(1));
			employee.setPrenom(result.getString(2));
			employee.setNom(result.getString(3));
			employee.setDate_naissance(result.getString(4));
			employee.setGender(result.getString(5));
			employee.setGroupage(result.getString(6));
			employee.setSalaire(result.getString(7));
			employee.setDebut_travaille(result.getString(8));
			employee.setTelephone(result.getString(9));
		}
		connection_test.close();
		return employee;
	}
	// Search for Employee by name
		public static Employee getEmployee_by_name_dateOfBirth(String firstName,String lastName,String dateOfBirth) throws SQLException {
			Employee employee =  new Employee();
			Connection connection_test= getConnection();
			String statement = "SELECT * FROM employees WHERE  Prenom=? AND Nom=? AND Date_naissance=?";
			PreparedStatement select = connection_test.prepareStatement(statement);
			select.setString(1,firstName.toUpperCase());
			select.setString(2,lastName.toUpperCase());
			select.setString(3,dateOfBirth);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				employee.setUser_ID(result.getInt(1));
				employee.setPrenom(result.getString(2));
				employee.setNom(result.getString(3));
				employee.setDate_naissance(result.getString(4));
				employee.setGender(result.getString(5));
				employee.setGroupage(result.getString(6));
				employee.setSalaire(result.getString(7));
				employee.setDebut_travaille(result.getString(8));
				employee.setTelephone(result.getString(9));
			}
			connection_test.close();
			return employee;
		}
	
	
	
	
	
	
	
	
	
}
