package application.ControlPanal.GestioDesOrdonnances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class OrdonnanceTable {

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
	//================================insert into Contientmedicament_Table=============================================
	public static void insert_Into_Contientmedicament_Table(int med_ID,Ordonnace ordonnace) throws SQLException {
		Connection connection_test= getConnection();
		String statemnet = "INSERT INTO `contientmedicament` (`Numero_Ordon`,"
															+ "`Med_ID`,"
															+ " `Quantite`,"
															+ " `DureeTaraitement`,"
															+ " `Commentaire`,"
															+ " `DateDeCreation`)"
															+"VALUES ('"+ordonnace.getN_ord()+"',"
																		+ "'"+med_ID+"',"
																		+"'"+ordonnace.getQuantite()+"',"
																		+"'"+ordonnace.getDuree_trait()+"',"
																		+"'"+ordonnace.getCommentaire()+"',"
																		+"CURRENT_DATE())";
		
		PreparedStatement insert = connection_test.prepareStatement(statemnet);
		insert.execute();	
		connection_test.close();
	}
	//================================insert into Ordonnance_Table=============================================

	public static void insert_Into_Ordonnance_Table(int P_ID) throws SQLException {
		Connection connection_test= getConnection();
		String statemnet = "INSERT INTO `ordonnance` (`U_ID`, `P_ID`, `Numero_Ordon`, `Date`) "
						+ "VALUES ('1', '"+P_ID+"',NULL,CURRENT_DATE())";
							
		PreparedStatement insert = connection_test.prepareStatement(statemnet);
		
		insert.execute();	
		connection_test.close();
	
	}
	
	//================================select all data from  Ordonnance_Table=============================================

	public static ResultSet show_Ordonnance() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM medicament WHERE 1";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	//========= Select the forme medecine from medicament_Table to use it into ComboBox forme in add Medicament======
	
	public static ResultSet select_scientific_name() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT DISTINCT `Nom_Scientifique` FROM `medicament` WHERE 1 ORDER by Nom_Scientifique ASC";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	//========= Select the forme medecine from medicament_Table to use it into ComboBox Dosage in add Medicament======
	
	public static ResultSet select_Dosage(String nom_scientific, String forme) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT DISTINCT `DOSAGE` FROM `medicament` WHERE FORME ='"+forme+"' AND"
				+ "`Nom_Scientifique`='"+ nom_scientific+"' ORDER BY `DOSAGE` ASC";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	//========= Select the forme medecine from medicament_Table to use it into ComboBox forme in add Medicament======
	
	public static ResultSet select_Forme(String nom_scientific) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT DISTINCT `FORME` FROM `medicament` WHERE FORME <> 'inconnu' AND"
							+ "`Nom_Scientifique`='"+ nom_scientific+"' ORDER BY `FORME` ASC";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	//========= Select the forme medecine from medicament_Table to use it into ComboBox forme in add Medicament======
	 
	public static ResultSet select_Forme() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT DISTINCT`FORME` FROM `medicament` WHERE FORME <> 'inconnu' ORDER BY `FORME` ASC";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	
	//retourne le direnier ordonnance inserer
	public static ResultSet get_Ordonnance_Num() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT `Numero_Ordon` FROM ordonnance ORDER BY `Numero_Ordon` DESC LIMIT 1;";
		PreparedStatement select = connection_test.prepareStatement(statement);
		ResultSet resultSet = select.executeQuery(); 
		return resultSet;
	}
	
	public static ResultSet get_Medicament_ID(Ordonnace medicament_Propriete) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT `Med_ID` FROM `medicament` WHERE `Nom_Scientifique`=? AND `FORME` = ? AND `DOSAGE` = ? LIMIT 1";
		PreparedStatement select = connection_test.prepareStatement(statement);
		select.setString(1,medicament_Propriete.getMedecament());
		select.setString(2,medicament_Propriete.getForme());
		select.setString(3,medicament_Propriete.getDosage());
		ResultSet resultSet = select.executeQuery(); 
		return resultSet;
	}
  //================================Select data by joining the contientMedicament_Table And Ordonnance_Table========================
	public static ResultSet select_Join_Ord_with_ConMed(int num_Ord) throws SQLException {
		Connection connection_test= getConnection();
		String join_Statment = "SELECT contientmedicament.Numero_Ordon,contientmedicament.Med_ID,"
								+ "medicament.Nom_Scientifique, medicament.FORME,medicament.DOSAGE,"
								+ "contientmedicament.Quantite,contientmedicament.DureeTaraitement,"
								+ "contientmedicament.Commentaire,ordonnance.Date "
								+ "FROM contientmedicament,ordonnance,medicament "
								+ "WHERE contientmedicament.Numero_Ordon = ordonnance.Numero_Ordon"
								+ " AND contientmedicament.DateDeCreation=ordonnance.Date"
								+ " AND contientmedicament.Med_ID=medicament.Med_ID"
								+ " AND ordonnance.Numero_Ordon = '"+num_Ord+"'";
		PreparedStatement select = connection_test.prepareStatement(join_Statment);
		ResultSet result = select.executeQuery();
		return result;	
	}
	
//================================Select all ordonnance_Patient by joining the contientMedicament_Table And Ordonnance_Table========================
	public static ResultSet select_Join_Ord_with_Patient() throws SQLException {
		Connection connection_test= getConnection();
		String join_Statment = "SELECT patient.P_ID,patient.LastName,patient.FirstName,patient.DateOfBirth,"
							+ "ordonnance.Numero_Ordon,ordonnance.Date FROM patient,ordonnance"
							+ " WHERE patient.P_ID=ordonnance.P_ID";
		PreparedStatement select = connection_test.prepareStatement(join_Statment);
		ResultSet result = select.executeQuery();
		return result;	
	}
//================================ Search for an ordonnance_patient by name =============================
	public static ResultSet select_All_Ord() throws SQLException {
		Connection connection_test= getConnection();
		String join_Statment = "SELECT patient.P_ID,patient.LastName,patient.FirstName,patient.DateOfBirth,"
				+ "ordonnance.Numero_Ordon,ordonnance.Date FROM patient,ordonnance"
				+ "  WHERE patient.P_ID=ordonnance.P_ID ";
		PreparedStatement select = connection_test.prepareStatement(join_Statment);
		ResultSet result = select.executeQuery();
		return result;	
	}
//================================ Search for an ordonnance_patient by name =============================
		public static ResultSet select_Join_Ord_with_Patient(String lastName,String firstName) throws SQLException {
			Connection connection_test= getConnection();
			String join_Statment = "SELECT patient.P_ID,patient.LastName,patient.FirstName,patient.DateOfBirth,"
								+ "ordonnance.Numero_Ordon,ordonnance.Date FROM patient,ordonnance"
								+ "  WHERE patient.P_ID=ordonnance.P_ID AND patient.LastName ='"+lastName.toUpperCase()+"' "
								+ "AND patient.FirstName = '"+firstName.toUpperCase()+"'";
			PreparedStatement select = connection_test.prepareStatement(join_Statment);
			ResultSet result = select.executeQuery();
			return result;	
		}
//================================ Search for an ordonnance_patient by name and date ========================
		public static ResultSet select_Join_Ord_with_Patient(String lastName,String firstName,String date) throws SQLException {
			Connection connection_test= getConnection();
			String join_Statment = "SELECT patient.P_ID,patient.LastName,patient.FirstName,patient.DateOfBirth,"
								+ "ordonnance.Numero_Ordon,ordonnance.Date FROM patient,ordonnance"
								+ " WHERE patient.P_ID=ordonnance.P_ID AND patient.LastName ='"+lastName+"' "
								+ "AND patient.FirstName = '"+firstName+"' AND ordonnance.Date ='"+date+"'" ;
			PreparedStatement select = connection_test.prepareStatement(join_Statment);
			ResultSet result = select.executeQuery();
			return result;	
		}		
//================================Select the ordonannance ID by date and Patient ID Ordonnance_Table========================
	public static ResultSet get_Ordonnance_Num(int numero_Ord,int p_ID) throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT `Numero_Ordon` FROM ordonnance WHERE ;";
		PreparedStatement select = connection_test.prepareStatement(statement);
		ResultSet resultSet = select.executeQuery(); 
		return resultSet;
	}
//============================================================================================================================
	public static ResultSet show_suggestion(String pattern) throws SQLException{
		Connection connection_test= getConnection();
		String join_Statment = "SELECT patient.P_ID,patient.LastName,patient.FirstName,patient.DateOfBirth,"
								+ "ordonnance.Numero_Ordon,ordonnance.Date FROM patient,ordonnance"
								+ "WHERE patient.P_ID=ordonnance.P_ID AND"
								+ "(patient.LastName LIKE '"+pattern+"%' OR patient.FirstName LIKE '"+pattern+"%')";
		
		
		PreparedStatement select = connection_test.prepareStatement(join_Statment);
		return select.executeQuery();
	}
}
