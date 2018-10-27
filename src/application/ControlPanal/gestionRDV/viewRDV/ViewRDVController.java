package application.ControlPanal.gestionRDV.viewRDV;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.effects.JFXDepthManager;

import application.ControlPanal.gestionRDV.RDV;
import application.ControlPanal.gestionRDV.RDVTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ViewRDVController implements Initializable{
	
	@FXML Pane root_viewRDV;
	
	@FXML TableView<RDV>tableRDV;
	@FXML ObservableList<RDV> listItems = FXCollections.observableArrayList();
	@FXML private TableColumn<RDV, Integer> patientID;
	@FXML private TableColumn<RDV, String> firstName;
	@FXML private TableColumn<RDV, String> lastName;
	@FXML TableColumn<RDV, Integer> numRDV ;
	@FXML TableColumn<RDV, String> dateRDV;
	@FXML TableColumn<RDV, String> timeRDV;
	@FXML TableColumn<RDV, String> commentaire;
	@FXML JFXDatePicker search;
	@FXML JFXButton chercher;
	@FXML JFXButton fermer;
	@FXML Label labelResult;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		JFXDepthManager.setDepth(search, 4);
		JFXDepthManager.setDepth(tableRDV, 4);
	
		try {
			ResultSet result_list = RDVTable.get_Today_RDV();
			int num_row = 0;
			while (result_list.next()) {
				num_row++;
				listItems.add(new RDV(result_list.getInt(1),
									  result_list.getInt(4),
									  result_list.getString(2),
									  result_list.getString(3),
									  result_list.getString(5),
									  result_list.getString(6),
									  result_list.getString(7)));
			}
			if (num_row<=0) {
				labelResult.setText("Pas de Rendez-vous Aujourd'hui");
			}
			else labelResult.setText("");
			patientID.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("patientID"));
			numRDV.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("numRDV"));
			firstName.setCellValueFactory(new PropertyValueFactory<RDV, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<RDV, String>("lastName"));
			dateRDV.setCellValueFactory(new PropertyValueFactory<RDV, String>("dateRDV"));
			timeRDV.setCellValueFactory(new PropertyValueFactory<RDV, String>("timeRDV"));
			commentaire.setCellValueFactory(new PropertyValueFactory<RDV, String>("commentaire"));
			tableRDV.setItems(listItems);


			MultipleSelectionModel<RDV> selected = tableRDV.getSelectionModel();
			selected.selectedItemProperty().addListener(new ChangeListener<RDV>() {

				@Override
				public void changed(ObservableValue<? extends RDV> observable, RDV oldValue, RDV newValue) {
					/*search.setText(tableRDV.getSelectionModel().getSelectedItem().getLastName()+" "+
							tableRDV.getSelectionModel().getSelectedItem().getFirstName());*/
					chercher.setDisable(false);

				}
			});
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}	
	
	public void search() throws SQLException{
		listItems.clear();
		ResultSet list_rdv = RDVTable.search_by_date(search.getValue().toString());
		int num_row = 0;
		
		
		while(list_rdv.next()){
			num_row++;
			listItems.add(new RDV(
							list_rdv.getInt(1),
							list_rdv.getInt(4),
							list_rdv.getString(3),
							list_rdv.getString(2),
							list_rdv.getString(5),
							list_rdv.getString(6),
							list_rdv.getString(7)
							));
		}
		if (num_row<=0) {
			labelResult.setText("Pas de Rendez-vous");
		}else labelResult.setText("");
		list_rdv.close();
		
		tableRDV.setItems(listItems);
	}
	public void fermer(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
}