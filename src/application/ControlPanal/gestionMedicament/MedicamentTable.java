package application.ControlPanal.gestionMedicament;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MedicamentTable {

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
	public static void insert_Medicament(Medicament medicament) throws SQLException {
		Connection connection_test= getConnection();
		
		String statemnet = "INSERT INTO medicament( `Nom_Scientifique`, `NOM_DE_MARQUE`, `FORME`, `DOSAGE`)VALUES("
							+ "'"+medicament.getNom_Internat()+"',"
							+ "'"+medicament.getNom_de_marque()+"',"
							+ "'"+medicament.getForme()+"',"
							+ "'"+medicament.getDosage()+"')";
		PreparedStatement insert = connection_test.prepareStatement(statemnet);
		insert.executeUpdate();	
		connection_test.close();
	
	}
	
	public static ResultSet show_Medicament_Table() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT * FROM medicament WHERE 1";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}
	public static ResultSet select_Forme() throws SQLException{
		Connection connection_test= getConnection();
		String statement = "SELECT DISTINCT `FORME` FROM `medicament` WHERE FORME <> 'inconnu' ORDER BY `FORME` ASC";
		PreparedStatement select = connection_test.prepareStatement(statement);
		return select.executeQuery();
	}

	
	}
