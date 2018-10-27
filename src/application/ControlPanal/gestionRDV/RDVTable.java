package application.ControlPanal.gestionRDV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.ControlPanal.gestionPatients.Patients;

public class RDVTable {

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
		
		public static void insert_RDV(RDV rendez_vous) throws SQLException {
			Connection connection_test= getConnection();
			
			String statemnet = "INSERT INTO rendez_vous(U_ID,P_ID,NumRDV,Date_RDV,Heure_RDV,Commentaire) VALUES("
								+ "'"+rendez_vous.getEmployeeID()+"',"
								+ "'"+rendez_vous.getPatientID()+"',"
								+ "'"+rendez_vous.getNumRDV()+"',"
								+ "'"+rendez_vous.getDateRDV()+"',"
								+ "'"+rendez_vous.getTimeRDV()+"',"
								+"'"+rendez_vous.getCommentaire()+"')";
			PreparedStatement insert = connection_test.prepareStatement(statemnet);
			insert.executeUpdate();	
			connection_test.close();
		
		}
		//delete patient informations
		public static int deleteRDV(int id) throws SQLException {

			String delete_Statetment = "DELETE FROM rendez_vous WHERE P_ID=?";
			Connection connection = getConnection();
			PreparedStatement prepared_delete_statement = connection.prepareStatement(delete_Statetment);
			prepared_delete_statement.setInt(1, id);
			int row = prepared_delete_statement.executeUpdate();
			connection.close();
			return row;
		}
		
		public static ResultSet show_RDV_Table() throws SQLException{
			Connection connection_test= getConnection();
			String statement = "SELECT * FROM rendez_vous WHERE 1 ORDER BY Date_RDV,Heure_RDV";
			PreparedStatement select = connection_test.prepareStatement(statement);
			ResultSet result = select.executeQuery();
			return result;
		}
		public static boolean chek_IF_Exists(String dateRDV,String heureRDV) throws SQLException{
		
			Connection connection_test= getConnection();
			String statement = "SELECT * FROM rendez_vous WHERE"
								+ "`Date_RDV`='"+dateRDV+"' AND `Heure_RDV` = '"+heureRDV+"'";
			PreparedStatement select = connection_test.prepareStatement(statement);
			ResultSet result = select.executeQuery();
			 if (result.next())
				 return true;
			return false;
		}
		public static ResultSet search_by_date(String date) throws SQLException{
			Connection connection_test= getConnection();
			String statement = "SELECT rendez_vous.P_ID,patient.FirstName,patient.LastName,rendez_vous.NumRDV, "
					+ "rendez_vous.Date_RDV,rendez_vous.Heure_RDV,rendez_vous.Commentaire"
					+ " FROM rendez_vous, patient WHERE patient.P_ID=rendez_vous.P_ID AND"
					+ " rendez_vous.Date_RDV = '"+date+"' ORDER BY Heure_RDV";
			PreparedStatement select = connection_test.prepareStatement(statement);
			ResultSet result = select.executeQuery();
			return result;
		}
		//this query is used to get the today list RDV to display it in the RDV Controler 
		public static ResultSet get_Today_RDV() throws SQLException{
			Connection connection_test= getConnection();
			String statement = "SELECT rendez_vous.P_ID,patient.FirstName,patient.LastName,rendez_vous.NumRDV, "
								+ "rendez_vous.Date_RDV,rendez_vous.Heure_RDV,"
								+ " rendez_vous.Commentaire,rendez_vous.DateOfCreation"
								+ " FROM rendez_vous, patient WHERE patient.P_ID=rendez_vous.P_ID AND"
								+ " rendez_vous.Date_RDV = CURRENT_DATE() ORDER BY Heure_RDV";
			PreparedStatement select = connection_test.prepareStatement(statement);
			ResultSet result = select.executeQuery();
			return result;
		}
		
		// Search for patient by id
		public static Patients getPatientName_ByID(int id) throws SQLException {
			Patients patients =  new Patients();
			Connection connection_test= getConnection();
			String statement = "SELECT firstName,LastName FROM patient WHERE patient.P_ID =?";
			PreparedStatement select = connection_test.prepareStatement(statement);
			select.setInt(1, id);
			ResultSet result = select.executeQuery();
			//this equivalent to if the result is not empty
			if (result.next()) {
				patients.setFirstName(result.getString(1));
				patients.setLastName(result.getString(2));
			}
			connection_test.close();
			return patients;
		}
		public static ResultSet join_The_Patient_with_RDV() throws SQLException {
			Connection connection_test= getConnection();
			String sqlStatment = "SELECT rendez_vous.P_ID,patient.FirstName,patient.LastName,rendez_vous.NumRDV, "
					+ "rendez_vous.Date_RDV,rendez_vous.Heure_RDV, rendez_vous.Commentaire,rendez_vous.DateOfCreation"
					+ " FROM rendez_vous, patient WHERE patient.P_ID=rendez_vous.P_ID AND rendez_vous.Date_RDV >= CURRENT_DATE() ORDER BY Date_RDV ASC";
			PreparedStatement select = connection_test.prepareStatement(sqlStatment);
			ResultSet result = select.executeQuery();
			return result;
		}
		
		
		//update patient informations 
		public static int updateRDV(String oldDate,String oldTime,RDV newRDV) throws SQLException {

			String update_Statetment = "UPDATE rendez_vous SET Date_RDV=?,Heure_RDV=?,Commentaire=? WHERE Date_RDV =?"
					+ "AND Heure_RDV=?";
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(update_Statetment);
			statement.setString(1, newRDV.getDateRDV());
			statement.setString(2, newRDV.getTimeRDV());
			statement.setString(3,newRDV.getCommentaire());
			statement.setString(4, oldDate);
			statement.setString(5, oldTime);
			int row = statement.executeUpdate();
			connection.close();
			return row;
		}
	
}
