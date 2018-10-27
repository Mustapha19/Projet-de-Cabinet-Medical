package application.ControlPanal.gestionEmployees.addEmployee;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;

import application.ControlPanal.NotificationControler;
import application.ControlPanal.gestionEmployees.Employee;
import application.ControlPanal.gestionEmployees.EmployeeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AddEmployeeController implements Initializable{
	
	private  String input_Debut_Travaille;
	private  String input_Salary;
	private  String input_Sexe;
	private  String input_blood;
	private  String input_firstName;
	private  String input_LastName;
	private  String input_dateofBirth;
	private  String input_phoneNumber;
	
	private String insertMessage= "Inserted Complete Successfully";
	@FXML AnchorPane border_layout;
	@FXML Label add_word_label;
	@FXML JFXTextField lastName;
	@FXML JFXTextField firstName;
	@FXML JFXDatePicker debut_travaille;
    @FXML JFXTextField salary;
	@FXML JFXTextField phone_Number;
	@FXML JFXComboBox<String> sexe;
	@FXML JFXComboBox<String> groupage;
	@FXML JFXDatePicker date;
	ObservableList<String>sexItems = FXCollections.observableArrayList("Male","Femele");
	ObservableList<String>bloodItems = FXCollections.observableArrayList(
										"A+","A-","B+","B-","AB+","AB-","O+","O-");
	Employee employee;
	NotificationControler notification;
	private boolean onOfThem_isEmpty;

	
	
	public void add_User(ActionEvent event) throws SQLException {
		 onOfThem_isEmpty = firstName.getText().equals("")||
								   lastName.getText().equals("")||
								   date.getValue().equals(null)||
								   sexe.getValue().equals(null)||
								   phone_Number.getText().equals("");
		if (onOfThem_isEmpty) {
			Alert warnning = new Alert(AlertType.WARNING,"Please enter all the fields",ButtonType.OK);
			warnning.setTitle("Insert warnning");
			warnning.setHeaderText("One of the fields is Empty");
			
			warnning.showAndWait();
			
		} else {
		input_firstName = firstName.getText();
		input_LastName = lastName.getText();
		input_dateofBirth = date.getValue().toString();
		input_Sexe = sexe.getValue();
		input_blood = groupage.getValue();
		input_Salary = salary.getText();
		input_Debut_Travaille = debut_travaille.getValue().toString();
		input_phoneNumber = phone_Number.getText();		
		
		employee = new Employee(input_LastName,
								input_firstName,
								input_dateofBirth,
								input_Sexe,
								input_blood,
								input_Salary,
								input_Debut_Travaille,
								input_phoneNumber);
		EmployeeTable.insert_Employee(employee);
	
		notification = new NotificationControler();
		notification.insertion_Notification(insertMessage);
		firstName.setText("");
		lastName.setText("");
		date.setValue(null);
		debut_travaille.setValue(null);
		phone_Number.setText("");
		sexe.setValue("");
		groupage.setValue("");
		((Node)event.getSource()).getScene().getWindow().hide();  
		}
	}

	
	
	public void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}


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

}
