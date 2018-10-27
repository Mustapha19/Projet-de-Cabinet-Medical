package application.ControlPanal.gestionPatients.updateOrDelete;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import application.ControlPanal.NotificationControler;
import application.ControlPanal.gestionPatients.PatientTable;
import application.ControlPanal.gestionPatients.Patients;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class UpdateOrDelController implements Initializable{

	private  int input_patientID;
	private  String sexe_Selected;
	private  String input_blood;
	private  String input_firstName;
	private  String input_LastName;
	private  String input_dateofBirth;
	private  String input_phoneNumber;
	private String updateMessage = "Do you really want to update";
	private String deleteMessage = "Do you really want to delete";
	private String finishUpdateMessage = "Update Complete Successfully";
	private String finishDeleteMessage = "Delete Complete Successfully";
	private String erreurDeletion = "Erreur accure while Deleting";
	private String erreurUpdation = "Erreur accure while Updating";
	private boolean one_Of_Them_Is_Empty;	
	private NotificationControler notification;
	private ObservableList<Patients> list  = FXCollections.observableArrayList();
	private ObservableList<String>genderItems = FXCollections.observableArrayList("MALE","FEMELE");
	private ObservableList<String>bloodItems = FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
	
	private Patients patient;
	private ButtonType button_choice;
	@FXML TableView<Patients> patientTable;
	@FXML TableColumn<Patients, Integer> id;
	@FXML TableColumn<Patients, String> firstName;
	@FXML TableColumn<Patients, String> lastName;
	@FXML TableColumn<Patients, String> dateOfBirth;
	@FXML TableColumn<Patients, String> sexe;
	@FXML TableColumn<Patients, String> blood;
	@FXML TableColumn<Patients, String> phoneNumber;
	@FXML AnchorPane updateRoot;
	@FXML Label add_word_label;
	@FXML JFXTextField prenom;
	@FXML JFXTextField nom;
	@FXML JFXTextField telephone;
	@FXML JFXComboBox<String> gender;
	@FXML JFXDatePicker date; 
	@FXML JFXComboBox<String> groupage;
	@FXML JFXButton supprimer; 
	@FXML JFXButton update;
	@FXML JFXButton close;

 //=======================================================================================================================================
	public void initialize(URL location, ResourceBundle resources) {
		groupage.setItems(bloodItems);
		gender.setItems(genderItems);
		JFXDepthManager.setDepth(add_word_label, 4);
		JFXDepthManager.setDepth(prenom, 4);
		JFXDepthManager.setDepth(nom, 4);
		JFXDepthManager.setDepth(date, 4);
		JFXDepthManager.setDepth(gender, 4);
		JFXDepthManager.setDepth(groupage, 4);
		JFXDepthManager.setDepth(telephone, 4);
		waiting_For_KeyEvents();
		show_Patient_table();
		id.setCellValueFactory(new PropertyValueFactory<Patients, Integer>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
		dateOfBirth.setCellValueFactory(new PropertyValueFactory<Patients, String>("dateOfBirth"));
		sexe.setCellValueFactory(new PropertyValueFactory<Patients, String>("sexe"));
		blood.setCellValueFactory(new PropertyValueFactory<Patients, String>("blood"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<Patients, String>("phoneNumber"));
		
		MultipleSelectionModel<Patients> selected = patientTable.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Patients>() {
			@Override
			public void changed(ObservableValue<? extends Patients> observable, Patients oldValue,Patients newValue) {
				
				input_patientID = patientTable.getSelectionModel().getSelectedItem().getId();
				nom.setText(patientTable.getSelectionModel().getSelectedItem().getLastName());
				prenom.setText(patientTable.getSelectionModel().getSelectedItem().getFirstName());
				date.setValue(LocalDate.parse(patientTable.getSelectionModel().getSelectedItem().getDateOfBirth()));
				gender.setValue(patientTable.getSelectionModel().getSelectedItem().getSexe());
				groupage.setValue(patientTable.getSelectionModel().getSelectedItem().getBlood());
				telephone.setText(patientTable.getSelectionModel().getSelectedItem().getPhoneNumber());
				update.setDisable(false);
				supprimer.setDisable(false);
			}
		});
	
	date.setOnKeyPressed(event->{
		try {
			if (event.getCode()==KeyCode.ENTER)
			patient = PatientTable.getPatient_by_name_dateOfBirth(firstName.getText(),
																  lastName.getText(),
																  date.getValue().toString());
		} catch (SQLException e1) {e1.printStackTrace();}	
	});
		

	}
 //=======================================================================================================================================
	public void patient_Selected_FromPatientList(Patients person) {
		prenom.setText(person.getFirstName());
		nom.setText(person.getLastName());
		date.setValue(LocalDate.parse(person.getDateOfBirth()));
		gender.setValue(person.getSexe());
		groupage.setValue(person.getBlood());
		telephone.setText(person.getPhoneNumber());
		update.setDisable(false);
		supprimer.setDisable(false);
	}
 //=======================================================================================================================================
	public void update_Patient() throws SQLException{
		one_Of_Them_Is_Empty = prenom.getText().equals("")||
							   nom.getText().equals("")||
							   date.getValue().equals("")||
							   gender.getValue().equals("")||
							   groupage.getValue().equals("")||
							   telephone.getText().equals("");
		if (one_Of_Them_Is_Empty) {
		Alert warnning = new Alert(AlertType.WARNING,"Please enter all the fields",ButtonType.OK);
		warnning.setTitle("Insert warnning");
		warnning.setHeaderText("One of the fields is Empty");
		warnning.showAndWait();
		
		} else {
		button_choice = confirme(updateMessage);
		if (button_choice.equals(ButtonType.YES)) {
	
		input_firstName = prenom.getText();
		input_LastName = nom.getText();
		input_dateofBirth = date.getValue().toString();
		sexe_Selected = gender.getValue();
		input_blood = groupage.getValue();
		input_phoneNumber = telephone.getText();
		Patients person = new Patients(input_patientID,
									   input_firstName,
									   input_LastName,
									   input_dateofBirth,
									   sexe_Selected,
									   input_blood,
									   input_phoneNumber, null);
		int row_updated = PatientTable.updatePatient(person);
		if (row_updated>0) {
			notification = new  NotificationControler();
			notification.upation_Notification(finishUpdateMessage);
			for (Patients patients : list) 
				if (patients.getId()==person.getId()) {
					list.remove(patients);
					list.add(person);
					break;
				}
		}else{
			notification = new  NotificationControler();
			notification.erreur_Updation_Notification(erreurUpdation);
		}
		update.setDisable(true);
		supprimer.setDisable(true);
		patientTable.refresh();
		prenom.setText("");
		nom.setText("");
		date.setValue(null);
		gender.setValue("");
		groupage.setValue("");
		telephone.setText("");
		}
		}
	}
 //=======================================================================================================================================
	public void delete_patient() throws SQLException {
		one_Of_Them_Is_Empty = prenom.getText().equals("")||
				   nom.getText().equals("")||
				   date.getValue().equals("")||
				   gender.getValue().equals("")||
				   groupage.getValue().equals("")||
				   telephone.getText().equals("");
		if (one_Of_Them_Is_Empty) {
		Alert warnning = new Alert(AlertType.WARNING,"Please enter all the fields",ButtonType.OK);
		warnning.setTitle("Insert warnning");
		warnning.setHeaderText("One of the fields is Empty");
		
		warnning.showAndWait();
		
		} else {
		button_choice = confirme(deleteMessage);
		if (button_choice.equals(ButtonType.YES)) {
		
		int row_deleted  = PatientTable.deletePatient(input_patientID);
		if (row_deleted>0) {
			notification = new  NotificationControler();
			notification.deletion_Notification(finishDeleteMessage);
		}
		else{
			notification = new  NotificationControler();
			notification.erreur_Deletion_Notification(erreurDeletion);
		}
			
		prenom.setText("");
		nom.setText("");
		gender.setValue("");
		groupage.setValue("");
		telephone.setText("");
		date.setValue(null);
		show_Patient_table();
		}
		
		}
	}
 //=======================================================================================================================================	
	public void waiting_For_KeyEvents(){
		 nom.setOnKeyPressed(e->{
			if (e.getCode()==KeyCode.ENTER) {		
				try {
					patient = PatientTable.getPatient_by_name(firstName.getText(), lastName.getText());
					setText_forTextfields();
				}catch (SQLException e1) {e1.printStackTrace();}
			}
		});
	}
 //======================================================================================================================================= 
	public void setText_forTextfields() {
		prenom.setText(patient.getFirstName());
		nom.setText(patient.getLastName());
		date.setValue(LocalDate.parse(patient.getDateOfBirth()));
		gender.setValue(patient.getSexe());
		groupage.setValue(patient.getBlood());
		telephone.setText(patient.getPhoneNumber());
	}
 //=======================================================================================================================================	
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
	//=======================================================================================================================================
	public void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
 //=======================================================================================================================================
	 public void show_Patient_table() {
		 list.clear();
		 try {
				ResultSet result_list = PatientTable.show_Patient_Table();
				while (result_list.next()) { 
					list.add(new Patients(result_list.getInt(1),
										  result_list.getString(2),
										  result_list.getString(3),
										  result_list.getString(4),
										  result_list.getString(5),
										  result_list.getString(6),
										  result_list.getString(7),
										  result_list.getString(8)
							));
				}
				result_list.close();
				} catch (SQLException e) {e.printStackTrace();}
		 patientTable.setItems(list);
	 }
	
	
}
