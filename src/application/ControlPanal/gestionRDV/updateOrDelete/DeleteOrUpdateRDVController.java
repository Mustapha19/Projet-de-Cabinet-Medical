	package application.ControlPanal.gestionRDV.updateOrDelete;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.effects.JFXDepthManager;

import application.ControlPanal.NotificationControler;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class DeleteOrUpdateRDVController implements Initializable {

    @FXML private Pane RDV_Window;
    @FXML private Label label_RDV;
    @FXML private TableView<RDV> patientTable;
    @FXML private TableColumn<RDV, Integer> patientID;
    @FXML private TableColumn<RDV, String> firstName;
    @FXML private TableColumn<RDV, String> lastName;
    @FXML private TableColumn<RDV, String> dateRDV;
    @FXML private TableColumn<RDV, String> timeRDV;
    @FXML private TableColumn<RDV, String> commentaire;
    @FXML private JFXTextField firstNameTextField;
    @FXML private JFXTextField lastNameTextField;
    @FXML private JFXDatePicker newDateRDV;
    @FXML private JFXTimePicker newTimeRDV;
    @FXML private JFXTextArea newCommentaire;
    @FXML private JFXButton delete;
    @FXML private JFXButton update;
    @FXML private JFXButton close;
    ObservableList<RDV> list = FXCollections.observableArrayList();
    private int input_id;
    private String input_dateRDV= "";
	private String input_TimeRDV= "";
	private String input_Commmentaire = "";
	private String oldInput_dateRDV= "";
	private String oldInput_TimeRDV= "";
	private String updateMessage = "Do you really want to update";
	private String deleteMessage = "Do you really want to delete";
	private String finishUpdateMessage = "Update Complete Successfully";
	private String finishDeleteMessage = "Delete Complete Successfully";
	private String erreurDeletion = "Erreur accure while Deleting";
	private String erreurUpdation = "Erreur accure while Updating";
	private boolean one_Of_Them_Is_Empty;
	NotificationControler notification;
	ButtonType button_choice;
 //=======================================================================================
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	JFXDepthManager.setDepth(label_RDV, 4);
		JFXDepthManager.setDepth(firstNameTextField, 4);
		JFXDepthManager.setDepth(lastNameTextField, 4);
		JFXDepthManager.setDepth(newDateRDV, 4);
		JFXDepthManager.setDepth(newTimeRDV, 4);
		JFXDepthManager.setDepth(newCommentaire, 4);
		JFXDepthManager.setDepth(patientTable, 4);
    	try {
			ResultSet join_Result = RDVTable.join_The_Patient_with_RDV();
			while (join_Result.next()) { 
				list.add(new RDV(join_Result.getInt(1),
						         join_Result.getString(2),
						         join_Result.getString(3),
						         join_Result.getString(5),
						         join_Result.getString(6),
						         join_Result.getString(7)
						         ));
			}
			patientID.setCellValueFactory(new PropertyValueFactory <RDV, Integer>("patientID"));
			firstName.setCellValueFactory(new PropertyValueFactory<RDV, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<RDV, String>("lastName"));
			dateRDV.setCellValueFactory(new PropertyValueFactory<RDV, String>("dateRDV"));
			timeRDV.setCellValueFactory(new PropertyValueFactory<RDV, String>("timeRDV"));
			commentaire.setCellValueFactory(new PropertyValueFactory<RDV, String>("commentaire"));
			patientTable.setItems(list);
			MultipleSelectionModel<RDV> selected = patientTable.getSelectionModel();
			selected.selectedItemProperty().addListener(new ChangeListener<RDV>() {
				@Override
				public void changed(ObservableValue<? extends RDV> observable, RDV oldValue, RDV newValue) {
					update.setDisable(false);
					delete.setDisable(false);
					firstNameTextField.setText(patientTable.getSelectionModel().getSelectedItem().getFirstName());
					lastNameTextField.setText(patientTable.getSelectionModel().getSelectedItem().getLastName());
					input_id = patientTable.getSelectionModel().getSelectedItem().getPatientID();
					patientTable.getSelectionModel().getSelectedItem().getPatientID();
					oldInput_dateRDV = patientTable.getSelectionModel().getSelectedItem().getDateRDV();
					oldInput_TimeRDV = patientTable.getSelectionModel().getSelectedItem().getTimeRDV();
					newDateRDV.setValue(LocalDate.parse(patientTable.getSelectionModel().getSelectedItem().getDateRDV()));
					newTimeRDV.setValue(LocalTime.parse(patientTable.getSelectionModel().getSelectedItem().getTimeRDV()));
					newCommentaire.setText(patientTable.getSelectionModel().getSelectedItem().getCommentaire());
					
				}
			});
			
			join_Result.close();
			
			} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
  //=======================================================================================  
    @FXML
    void delete_RDV() throws SQLException {
    	one_Of_Them_Is_Empty = firstNameTextField.getText().equals("")||
				   lastNameTextField.getText().equals("")||
				   newDateRDV.getValue().equals(null)||
				   newTimeRDV.getValue().equals(null)||
				   newCommentaire.getText().equals("");
    	if (one_Of_Them_Is_Empty) {
			Alert warnning = new Alert(AlertType.WARNING,"Please enter all the fields",ButtonType.OK);
			warnning.setTitle("Insert warnning");
			warnning.setHeaderText("One of the fields is Empty");
			warnning.showAndWait();	
		}else{
    	button_choice = confirme(deleteMessage);
		if (button_choice.equals(ButtonType.YES)) {
			int row_deleted = RDVTable.deleteRDV(input_id);
			if (row_deleted>0) {
				notification = new  NotificationControler();
				notification.deletion_Notification(finishDeleteMessage);
			}
		else{
			notification = new  NotificationControler();
			notification.erreur_Deletion_Notification(erreurDeletion);
		}
			System.out.println("Error in deletion");
		
		patientID.setText("");
		firstName.setText("");
		lastName.setText("");
		newDateRDV.setValue(null);
		newTimeRDV.setValue(null);
		}
		}
    }   
  //=========================================================================================== 
    public void updateRDV() throws SQLException {
    	one_Of_Them_Is_Empty = firstNameTextField.getText().equals("")||
    								   lastNameTextField.getText().equals("")||
    								   newDateRDV.getValue().equals(null)||
    								   newTimeRDV.getValue().equals(null)||
    								   newCommentaire.getText().equals("");
		if (one_Of_Them_Is_Empty) {
			Alert warnning = new Alert(AlertType.WARNING,"Please enter all the fields",ButtonType.OK);
			warnning.setTitle("Insert warnning");
			warnning.setHeaderText("One of the fields is Empty");
			warnning.showAndWait();	
		}else{
	    	button_choice = confirme(updateMessage);
	    	if (button_choice.equals(ButtonType.YES)) {
	    		RDV rdvUpdate;
				input_dateRDV = newDateRDV.getValue().toString();
	    		input_TimeRDV = newTimeRDV.getValue().toString();	
	    		input_Commmentaire = newCommentaire.getText();    	
	    		rdvUpdate = new RDV(input_dateRDV, input_TimeRDV, input_Commmentaire);
				int row_updated = RDVTable.updateRDV(oldInput_dateRDV, oldInput_TimeRDV, rdvUpdate);		
				if (row_updated>0) {
					notification = new  NotificationControler();
					notification.upation_Notification(finishUpdateMessage);
					}
				 else{
						notification = new  NotificationControler();
						notification.erreur_Updation_Notification(erreurUpdation);
				 }
			    	
				newDateRDV.setValue(LocalDate.parse(""));
				newTimeRDV.setValue(LocalTime.parse(""));
		    	newCommentaire.setText("");
		    	firstNameTextField.setText("");
		    	lastNameTextField.setText("");
	    	}
		}
    }
  //=======================================================================================
    @SuppressWarnings("static-access")
    public ButtonType confirme(String message) {
    	ButtonType[] yes = {new ButtonType("YES").YES,new ButtonType("NO").CANCEL};
    	Alert confirmation = new Alert(AlertType.CONFIRMATION,"",yes);
    	confirmation.setTitle("Confirmation operation");
    	confirmation.setHeaderText(message);
    	confirmation.setResult(yes[1]);
    	confirmation.showAndWait();
    	return confirmation.getResult();		
    }
    public void fermer(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }

}
