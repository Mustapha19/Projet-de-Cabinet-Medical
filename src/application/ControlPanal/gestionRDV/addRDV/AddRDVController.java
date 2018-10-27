package application.ControlPanal.gestionRDV.addRDV;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.effects.JFXDepthManager;
import application.ControlPanal.NotificationControler;
import application.ControlPanal.gestionPatients.PatientTable;
import application.ControlPanal.gestionPatients.Patients;
import application.ControlPanal.gestionRDV.RDV;
import application.ControlPanal.gestionRDV.RDVTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AddRDVController implements Initializable {
	private int input_id;
	private static int numRDV = 0;
	String input_firstName;
	String input_LastName;
	private String input_dateRDV= "";
	private String input_TimeRDV= "";
	private String input_Commmentaire = "";
	private String insertMessage= "Inserted Complete Successfully";
	@FXML Pane RDV;
	@FXML StackPane RDV_Window;
	@FXML Label label_RDV;
	@FXML TableView<Patients> patientTable;
	//To display the content in the table,
	// the name of columns must be the same with the fields name in the Patient class to display the 
	@FXML TableColumn<Patients, String> id;
	@FXML TableColumn<Patients, String> firstName;
	@FXML TableColumn<Patients, String> lastName;
	@FXML TableColumn<Patients, String> dateOfBirth;
	@FXML TableColumn<Patients, String> sexe;
	@FXML TableColumn<Patients, String> phoneNumber;
	@FXML JFXTextField firstNameTextField;
	@FXML JFXTextField lastNameTextField;
	@FXML JFXDatePicker datePicker; 
	@FXML JFXTimePicker timePicker;
	@FXML JFXTextArea commentaireTextField;
	private ObservableList<Patients> list  = FXCollections.observableArrayList();
	private NotificationControler notification;
 //=======================================================================================================================================
	//this method initialize the ComboBox when this window is shown
	public void initialize(URL location, ResourceBundle resources) {
		
		JFXDepthManager.setDepth(label_RDV, 4);
		JFXDepthManager.setDepth(firstNameTextField, 4);
		JFXDepthManager.setDepth(lastNameTextField, 4);
		JFXDepthManager.setDepth(datePicker, 4);
		JFXDepthManager.setDepth(timePicker, 4);
		JFXDepthManager.setDepth(commentaireTextField, 4);
		JFXDepthManager.setDepth(patientTable, 4);
		
		try {
			ResultSet result_list = PatientTable.show_Patient_Table();
			while (result_list.next()) { 
				list.add(new Patients(result_list.getInt(1),
									  result_list.getString(2),
									  result_list.getString(3),
									  result_list.getString(4),
									  result_list.getString(5),
									  result_list.getString(7)
									 ));
			}
			id.setCellValueFactory(new PropertyValueFactory<Patients, String>("id"));
			firstName.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
			dateOfBirth.setCellValueFactory(new PropertyValueFactory<Patients, String>("dateOfBirth"));
			sexe.setCellValueFactory(new PropertyValueFactory<Patients, String>("sexe"));
			phoneNumber.setCellValueFactory(new PropertyValueFactory<Patients, String>("phoneNumber"));
			patientTable.setItems(list);
			MultipleSelectionModel<Patients> selected = patientTable.getSelectionModel();
			selected.selectedItemProperty().addListener(new ChangeListener<Patients>() {
			
				@Override
				public void changed(ObservableValue<? extends Patients> observable, Patients oldValue,Patients newValue) {
					firstNameTextField.setText(patientTable.getSelectionModel().getSelectedItem().getFirstName());
					lastNameTextField.setText(patientTable.getSelectionModel().getSelectedItem().getLastName());
					input_id = patientTable.getSelectionModel().getSelectedItem().getId();
				}
			});
			} catch (SQLException e) {e.printStackTrace();}	
	}
	
 //=======================================================================================================================================
	//close and return to the Control panel
	@FXML
	private void close (ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();  
		
	}
 //=======================================================================================================================================
	// Insert the RDV data into the RDV table
	//when the submit button clicked this method is called
	@FXML
	public void insert_RDV(ActionEvent event) throws SQLException {
		boolean onOfThem_isEmpty = firstNameTextField.getText().equals("")||lastNameTextField.getText().equals("")||
			commentaireTextField.getText().equals("")||datePicker.getValue().equals(null)||timePicker.getValue().equals(null);
		if (onOfThem_isEmpty) {
			loadDailog("One of the fields is Empty \n Please enter all the fields");
			
		} else {
		input_firstName = firstName.getText();
		input_LastName = lastName.getText();
		input_dateRDV = datePicker.getValue().toString();
		input_TimeRDV = timePicker.getValue().toString();
		input_Commmentaire = commentaireTextField.getText();
		if (is_Already_Exist(input_dateRDV, input_TimeRDV)) {
			loadDailog("Le Rendez-Vous est Déja Ocuppée par une autre personne \n Ressayez une autre date ou changer l'Heure");
			datePicker.setValue(null);
			timePicker.setValue(null);
		}else{
		RDVTable.insert_RDV(new RDV(1,input_id,generateNumRDV(),input_dateRDV, input_TimeRDV, input_Commmentaire));
		notification = new NotificationControler();
		notification.insertion_Notification(insertMessage);
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		datePicker.setValue(null);
		timePicker.setValue(null);
		commentaireTextField.setText("");
		((Node)event.getSource()).getScene().getWindow().hide();  
		}
		}
	}
	//=======================================================================================================================================
	public int generateNumRDV(){
		return ++numRDV;
	}
	//=======================================================================================================================================
	public boolean is_Already_Exist(String date_RDV,String heure_RDV){
		boolean exist = false;
		try {
			exist = RDVTable.chek_IF_Exists(date_RDV, heure_RDV);
		} catch (SQLException e) {e.printStackTrace();}
		return exist;
	}
//=======================================================================================================================================
	public void loadDailog(String message) {
		 javafx.scene.text.Font headerfont = new javafx.scene.text.Font(20);
		 javafx.scene.text.Font font = new javafx.scene.text.Font(16);
		 JFXDialogLayout dialogLayout = new JFXDialogLayout();
		 Text text = new Text("Désolé !!!");
		 text.setFont(headerfont);
		 dialogLayout.setHeading(text);
		 Text textMessagee = new Text(message);
		 textMessagee.setFont(font);
		 dialogLayout.setBody(textMessagee);
		 JFXDialog dialog = new JFXDialog(RDV_Window, dialogLayout, JFXDialog.DialogTransition.CENTER);
		 dialog.toFront();
		 JFXButton fermer = new JFXButton("Fermer");
		 fermer.setCancelButton(true);
		 fermer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {dialog.close();}
		});
		 dialogLayout.setActions(fermer);
		 dialog.show();
	}
	
}

