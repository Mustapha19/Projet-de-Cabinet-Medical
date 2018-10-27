package application.ControlPanal.gestionMedicament.updateOrDeleteMedicament;

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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class UpdateOrDeleteMedicamentController implements Initializable{

    @FXML private AnchorPane update_medicament;
    @FXML private JFXTextField nom;
    @FXML private JFXTextField dosage_medicament;
    @FXML private JFXTextField marque;
    @FXML private JFXComboBox<String>forme_medicament;
    @FXML private TableView<Medicament> medicament_list;
    @FXML private TableColumn<Medicament, String> nom_Internat;
    @FXML private TableColumn<Medicament, String> nom_de_marque;
    @FXML private TableColumn<Medicament, String> forme;
    @FXML private TableColumn<Medicament, String> dosage;
    ObservableList<Medicament> list  = FXCollections.observableArrayList();  
    ObservableList<String>formeItems = FXCollections.observableArrayList(); 
    @FXML private JFXButton supprimer;
    @FXML private JFXButton update;
    @FXML private JFXButton fermer;
//======================================================================================================================================
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	JFXDepthManager.setDepth(dosage_medicament, 4);
    	JFXDepthManager.setDepth(fermer, 4);
    	JFXDepthManager.setDepth(forme_medicament, 4);
    	JFXDepthManager.setDepth(marque, 4);
    	JFXDepthManager.setDepth(medicament_list, 4);
    	JFXDepthManager.setDepth(nom, 4);
    	JFXDepthManager.setDepth(update, 4);
    	JFXDepthManager.setDepth(supprimer, 4);
    	JFXDepthManager.setDepth(update_medicament, 4);
    	remplire_forme_ComboBox();
    	remplir_Medicament_Table();
    	
		nom_Internat.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nom_Internat"));
		nom_de_marque.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nom_de_marque"));
		forme.setCellValueFactory(new PropertyValueFactory<Medicament, String>("forme"));
		dosage.setCellValueFactory(new PropertyValueFactory<Medicament, String>("dosage"));
		MultipleSelectionModel<Medicament> selected = medicament_list.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Medicament>() {
			@Override
			public void changed(ObservableValue<? extends Medicament> observable, Medicament oldValue,
					Medicament newValue) {
				nom.setText(medicament_list.getSelectionModel().getSelectedItem().getNom_Internat());
				marque.setText(medicament_list.getSelectionModel().getSelectedItem().getNom_de_marque());
				forme_medicament.setValue(medicament_list.getSelectionModel().getSelectedItem().getForme());
				dosage_medicament.setText(medicament_list.getSelectionModel().getSelectedItem().getDosage());
				update.setDisable(false);
				supprimer.setDisable(false);
			}
		});
		
		
	}
//======================================================================================================================================
    @FXML public void fermer(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }
//======================================================================================================================================
    @FXML public void souvgarder(ActionEvent event) {

    }
//======================================================================================================================================
    public void remplire_forme_ComboBox(){
    	ResultSet result_list;
		try {
			result_list = MedicamentTable.select_Forme();
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
				/*list.add(new Medicament(
										result_list.getInt(1),
										result_list.getString(2),
										result_list.getString(3),
										result_list.getString(4),
										result_list.getString(5),
										result_list.getString(6)));*/
	    }
			result_list.close();
		} catch (SQLException e) {e.printStackTrace();}
		medicament_list.setItems(list);
    }
    	
	
	

}
