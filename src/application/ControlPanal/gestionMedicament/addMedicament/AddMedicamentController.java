package application.ControlPanal.gestionMedicament.addMedicament;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import application.ControlPanal.gestionMedicament.Medicament;
import application.ControlPanal.gestionMedicament.MedicamentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AddMedicamentController implements Initializable{
  @FXML private AnchorPane add_medicament;
  @FXML private JFXTextField nom;
  @FXML private JFXTextField marque;
  @FXML private JFXTextField dosage_medicament;
  @FXML private JFXComboBox<String>forme_medicament;
  @FXML private TableView<Medicament> medicament_list;
  @FXML private TableColumn<Medicament, String> nom_Internat;
  @FXML private TableColumn<Medicament, String> nom_de_marque;
  @FXML private TableColumn<Medicament, String> forme;
  @FXML private TableColumn<Medicament, String> dosage;
  
  @FXML private JFXButton sauvegarder;
  @FXML private JFXButton fermer;
  ObservableList<Medicament> list  = FXCollections.observableArrayList();  
  ObservableList<String>formeItems = FXCollections.observableArrayList(); 
  private String input_scientifique_Name;
  private String input_nom_de_marque;
  private String input_forme;
  private String input_dosage;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	JFXDepthManager.setDepth(dosage_medicament, 4);
    	JFXDepthManager.setDepth(fermer, 4);
    	JFXDepthManager.setDepth(forme_medicament, 4);
    	JFXDepthManager.setDepth(marque, 4);
    	JFXDepthManager.setDepth(medicament_list, 4);
    	JFXDepthManager.setDepth(nom, 4);
    	JFXDepthManager.setDepth(fermer, 4);
    	JFXDepthManager.setDepth(sauvegarder, 4);
    	
		remplire_forme_ComboBox();
		sauvegarder.setDisable(false);
		nom_Internat.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nom_Internat"));
		nom_de_marque.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nom_de_marque"));
		forme.setCellValueFactory(new PropertyValueFactory<Medicament, String>("forme"));
		dosage.setCellValueFactory(new PropertyValueFactory<Medicament, String>("dosage"));
		medicament_list.setItems(list);
	}
   
    public void fermer(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

  
    public void souvgarder(ActionEvent event) throws SQLException {
    	input_scientifique_Name = nom.getText();
    	input_nom_de_marque = marque.getText();
    	input_forme = forme_medicament.getValue();
    	input_dosage = dosage_medicament.getText();
    	
    	Medicament medicament_attribute = new Medicament(
    										input_scientifique_Name,
    										input_nom_de_marque,
    										input_forme,
    										input_dosage);
    	list.add(medicament_attribute);
    	MedicamentTable.insert_Medicament(medicament_attribute);
    }
  //======================================================================================================================================
    public void remplire_forme_ComboBox(){
		try {
			ResultSet result_list = MedicamentTable.select_Forme();
			while (result_list.next()) {formeItems.add(result_list.getString(1));}
			result_list.close();
		} catch (SQLException e) {e.printStackTrace();}
		forme_medicament.setItems(formeItems);
    }
 //======================================================================================================================================
    public void remplir_Medicament_Table(){
		try {
			ResultSet result_list = MedicamentTable.show_Medicament_Table();
			while (result_list.next()) { 
			/*	list.add(new Medicament(
										result_list.getInt(1),
										result_list.getString(2),
										result_list.getString(3),
										result_list.getString(4),
										result_list.getString(5),
										result_list.getString(6)));*/
	    }
			medicament_list.setItems(list);
			result_list.close();
		} catch (SQLException e) {e.printStackTrace();}
    }
	    

}
