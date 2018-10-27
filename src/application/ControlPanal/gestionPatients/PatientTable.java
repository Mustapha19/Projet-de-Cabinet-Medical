package application.ControlPanal.gestionPatients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientTable {
	

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
	public static void insert_Patients(Patients patient) throws SQLException {
		Connection connection_test= getConnection();
		
		String statemnet = "INSERT INTO `patient`("
										+ " `FirstName`,"
										+ " `LastName`,"
										+ " `DateOfBirth`,"
										+ " `Sexe`,"
										+ " `Groupage`,"
										+ " `PhoneNumber`,"
										+ " `DateOfCreation`)"
										+ " VALUES("
										+ "'"+patient.getFirstName()+"',"
										+ "'"+patient.getLastName()+"',"
										+ "'"+patient.getDateOfBirth()+"',"
										+ "'"+patient.getSexe()+"',"
										+ "'"+patient.getBlood()+"',"
										+"'"+patient.getPhoneNumber()+"',"
										+"CURRENT_DATE())";
		System.err.println(statemnet);
		PreparedStatement insert = connection_test.prepareStatement(statemnet);
		insert.executeUpdate();	
		connection_test.close();
	
	}
	//update patient informations 
	public static int updatePatient(Patients patients) throws SQLException {

		String update_Statetment = "UPDATE patient SET FirstName=?,LastName=?,DateOfBirth=?,Sexe=?,Groupage=?, PhoneNumber=? "
									+ "WHERE P_ID=?";
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(update_Statetment);
		statement.setString(1, patients.getFirstName());
		statement.setString(2, patients.getLastName());
		statement.setString(3, patients.getDateOfBirth());
		statement.setString(4, patients.getSexe()+"");
		statement.setString(5, patients.getBlood());
		statement.setString(6, patients.getPhoneNumber());
		statement.setInt(7, patients.getId());
		int row = statement.executeUpdate();
		connection.close();
		return row;
	}
	
	//delete patient informations
	public static int deletePatient(int id) throws SQLException {

		String delete_Statetment = "DELETE FROM patient WHERE P_ID=?";
		Connection connection = getConnection();
		PreparedStatement prepared_delete_statement = connection.prepareStatement(delete_Statetment);
		prepared_delete_statement.setInt(1, id);
		int row = prepared_delete_statement.executeUpdate();
		connection.close();
		return row;
	}
	
	public static ResultSet show_Patient_Table() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM `patient` WHERE 1 ORDER BY  LastName,FirstName";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	
	public static ResultSet show_suggestion(Patients person) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM `patient`"
							+ " WHERE LastName LIKE '"+person.getLastName()+"%' OR FirstName LIKE '"+person.getFirstName()+"%' OR"
									+ "(LastName LIKE '"+person.getFirstName()+"%' OR FirstName LIKE '"+person.getLastName()+"%')";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	public static ResultSet show_suggestion_by_Pattern(String pattern) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM `patient`"
							+ " WHERE LastName LIKE '"+pattern+"%' OR FirstName LIKE '"+pattern+"%'";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	
	// Search for patient by id
	public static Patients getPatient_DB_ID(int id) throws SQLException {
		Patients patients =  new Patients();
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM patient WHERE P_ID=?";
		PreparedStatement select = connection_test.prepareStatement(statement);
		select.setInt(1, id);
		ResultSet result = select.executeQuery();
		//this equivalent to if the result is not empty
		if (result.next()) {
			patients.setId(result.getInt(1));
			patients.setFirstName(result.getString(2));
			patients.setLastName(result.getString(3));
			patients.setDateOfBirth(result.getString(4));
			patients.setSexe(result.getString(5));
			patients.setBlood(result.getString(6));
			patients.setPhoneNumber(result.getString(7));
		}
		connection_test.close();
		return patients;
	}
	public static ResultSet show_patients_Name() throws SQLException {
		Connection connection_test= getConnection();
		String statement = "SELECT P_ID,FirstName, LastName,PhoneNumber FROM patient";
		PreparedStatement select = connection_test.prepareStatement(statement);
		ResultSet result = select.executeQuery();
		return result;
	}
	
	
	// Search for patient by name
	public static Patients getPatient_by_name(String firstName,String lastName) throws SQLException {
		Patients patients =  new Patients();
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM patient WHERE FirstName=? AND LastName=?";
		PreparedStatement select = connection_test.prepareStatement(statement);
		select.setString(1,firstName.toUpperCase());
		select.setString(2,lastName.toUpperCase());
		ResultSet result = select.executeQuery();
		if (result.next()) {
			patients.setId(result.getInt(1));
			patients.setFirstName(result.getString(2));
			patients.setLastName(result.getString(3));
			patients.setDateOfBirth(result.getString(4));
			patients.setSexe(result.getString(5));
			patients.setBlood(result.getString(6));
			patients.setPhoneNumber(result.getString(7));
		}
		connection_test.close();
		return patients;
	}
	// Search for patient by name
		public static Patients getPatient_by_name_dateOfBirth(String firstName,String lastName,String dateOfBirth) throws SQLException {
			Patients patients =  new Patients();
			Connection connection_test= getConnection();
			String statement = "SELECT * FROM patient WHERE FirstName=? AND LastName=? AND DateOfBirth=?";
			PreparedStatement select = connection_test.prepareStatement(statement);
			select.setString(1,firstName.toUpperCase());
			select.setString(2,lastName.toUpperCase());
			select.setString(3,dateOfBirth);
			ResultSet result = select.executeQuery();
			if (result.next()) {
				patients.setId(result.getInt(1));
				patients.setFirstName(result.getString(2));
				patients.setLastName(result.getString(3));
				patients.setDateOfBirth(result.getString(4));
				patients.setSexe(result.getString(5));
				patients.setBlood(result.getString(6));
				patients.setPhoneNumber(result.getString(7));
			}
			connection_test.close();
			return patients;
		}
		public static Patients getPatient_ID_by_name_dateOfBirth(String firstName,String lastName,String dateOfBirth) throws SQLException {
			Patients patients =  new Patients();
			Connection connection_test= getConnection();
			String statement = "SELECT P_ID FROM patient WHERE FirstName=? AND LastName=? AND DateOfBirth=?";
			PreparedStatement select = connection_test.prepareStatement(statement);
			select.setString(1,firstName);
			select.setString(2,lastName);
			select.setString(3,dateOfBirth);
			ResultSet result =select.executeQuery();		
			if (result.next()) {
				patients.setId(result.getInt(1));
			}
			connection_test.close();
			return patients; 	
		}
		public static int getPatientID_for_Ordonnance(Patients patientsselected,String dateOfBirth) throws SQLException {
			int p_id = 0;
			Connection connection_test= getConnection();
			String statement = "SELECT P_ID FROM patient WHERE FirstName=? AND LastName=? AND DateOfBirth=?";
			PreparedStatement select = connection_test.prepareStatement(statement);
			select.setString(1,patientsselected.getFirstName());
			select.setString(2,patientsselected.getLastName());
			select.setString(3,dateOfBirth);
			ResultSet result =select.executeQuery();		
			if (result.next()) {
				p_id = result.getInt(1);
			}
			connection_test.close();
			return p_id; 	
		}
	
	
	
	
	
	
	
	
	
}
