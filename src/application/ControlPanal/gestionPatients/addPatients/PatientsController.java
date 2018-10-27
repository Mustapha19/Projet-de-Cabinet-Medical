package application.ControlPanal.gestionPatients.addPatients;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import application.ControlPanal.NotificationControler;
import application.ControlPanal.gestionPatients.PatientTable;
import application.ControlPanal.gestionPatients.Patients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PatientsController implements Initializable{
	private String input_sexe = "";
	private String input_firstName = "";
	private String input_LastName= "";
	private String input_dateofBirth= "";
	private String input_blood= "";
	private String input_phoneNumber= "";
	private String insertMessage= "Inserted Complete Successfully";
	private Patients patient;
	private NotificationControler notification;
	private boolean onOfThem_isEmpty;
	@FXML StackPane addPatient;
	@FXML AnchorPane border_layout;
	@FXML Label add_word_label;
	@FXML JFXTextField firstName;
	@FXML JFXTextField lastName;
	@FXML JFXTextField phone_Number;
	@FXML JFXComboBox<String> sexe;
	@FXML JFXComboBox<String> groupage;
	@FXML JFXDatePicker date;
	ObservableList<String>sexItems = FXCollections.observableArrayList("Male","Femele");
	ObservableList<String>bloodItems = FXCollections.observableArrayList(
										"A+","A-","B+","B-","AB+","AB-","O+","O-");
	

//=======================================================================================================================================
	
	public void add_patient(ActionEvent event) throws SQLException {
		 onOfThem_isEmpty = firstName.getText().isEmpty()||
								   lastName.getText().isEmpty()||
								   date.getValue()==null||
								   sexe.getValue()==null||
								   phone_Number.getText().isEmpty();
		if (onOfThem_isEmpty) {
			loadDailog("Please enter all the fields \n One of the fields is Empty");
		} else {
		input_firstName = firstName.getText();
		input_LastName = lastName.getText();
		input_dateofBirth = date.getValue().toString();
		input_phoneNumber = phone_Number.getText();		
		input_sexe = sexe.getValue();
		input_blood = groupage.getValue();
		patient = new Patients(0,input_firstName, input_LastName, input_dateofBirth,input_sexe,input_blood,input_phoneNumber,"");
		PatientTable.insert_Patients(patient);
		notification = new NotificationControler();
		notification.insertion_Notification(insertMessage);
		firstName.setText("");
		lastName.setText("");
		date.setValue(null);
		phone_Number.setText("");
		sexe.setValue("");
		close(event);
		
		}
	}
//=======================================================================================================================================
	public void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
//=======================================================================================================================================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(add_word_label, 4);
		JFXDepthManager.setDepth(firstName, 4);
		JFXDepthManager.setDepth(lastName, 4);
		JFXDepthManager.setDepth(date, 4);
		JFXDepthManager.setDepth(sexe, 4);
		JFXDepthManager.setDepth(groupage, 4);
		JFXDepthManager.setDepth(phone_Number, 4);
		
		sexe.setItems(sexItems);
		groupage.setItems(bloodItems);
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
		 JFXDialog dialog = new JFXDialog(addPatient, dialogLayout, JFXDialog.DialogTransition.CENTER);
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
