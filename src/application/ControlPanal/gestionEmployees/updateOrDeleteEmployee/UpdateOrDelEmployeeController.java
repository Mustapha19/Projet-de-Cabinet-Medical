package application.ControlPanal.gestionEmployees.updateOrDeleteEmployee;

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
import application.ControlPanal.gestionEmployees.Employee;
import application.ControlPanal.gestionEmployees.EmployeeTable;
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

public class UpdateOrDelEmployeeController implements Initializable{

	private int input_EmployeeID;
	private  String input_Debut_Travaille;
	private  String input_Salary;
	private  String input_Sexe;
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
	private NotificationControler notification;
	
	@FXML private AnchorPane updateRoot;
    @FXML private Label add_word_label;
    @FXML private JFXTextField lastName;
    @FXML private JFXTextField firstName;
    @FXML private JFXDatePicker dateOfBirth;
    @FXML private JFXComboBox<String> sexe;
    @FXML private JFXComboBox<String> blood;
    @FXML private JFXDatePicker work_start;
    @FXML private JFXTextField salary;
    @FXML private JFXTextField phone_Number;
    @FXML private JFXButton close;
    @FXML private JFXButton update;
    @FXML private JFXButton supprimer;
    @FXML private TableView<Employee> userTable;
    @FXML private TableColumn<Employee, Integer> user_ID;
    @FXML private TableColumn<Employee,String> nom;
    @FXML private TableColumn<Employee,String> prenom;
    @FXML private TableColumn<Employee,String> date_naissance;
    @FXML private TableColumn<Employee,String> gender;
    @FXML private TableColumn<Employee,String> debut_travaille;
    @FXML private TableColumn<Employee,String> salaire;
    @FXML private TableColumn<Employee,String> groupage;
    @FXML private TableColumn<Employee,String> telephone;
    private ObservableList<Employee>user_List = FXCollections.observableArrayList();
    private ObservableList<String>genderItems = FXCollections.observableArrayList("MALE","FEMELE");
    private ObservableList<String>bloodItems = FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-");
    private Employee employee;
    private ButtonType button_choice;

  //=======================================================================================================================================
	public void initialize(URL location, ResourceBundle resources) {
		blood.setItems(bloodItems);
		sexe.setItems(genderItems);
		JFXDepthManager.setDepth(add_word_label, 4);
		JFXDepthManager.setDepth(firstName, 4);
		JFXDepthManager.setDepth(lastName, 4);
		JFXDepthManager.setDepth(dateOfBirth, 4);
		JFXDepthManager.setDepth(sexe, 4);
		JFXDepthManager.setDepth(blood, 4);
		JFXDepthManager.setDepth(salary, 4);
		JFXDepthManager.setDepth(work_start, 4);
		JFXDepthManager.setDepth(phone_Number, 4);
		try {
			ResultSet listset= EmployeeTable.show_Employee_Table();
			while (listset.next()) {
				user_List.add(new Employee(
								listset.getInt(1),
								listset.getString(2),
								listset.getString(3),
								listset.getString(4),
								listset.getString(5),
								listset.getString(6),
								listset.getString(7),
								listset.getString(8),
								listset.getString(9)
								));
				
			}
			listset.close();
		} catch (SQLException e) {e.printStackTrace();}
		user_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("user_ID"));
		nom.setCellValueFactory(new PropertyValueFactory<Employee, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Employee, String>("prenom"));
		date_naissance.setCellValueFactory(new PropertyValueFactory<Employee, String>("date_naissance"));
		gender.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		groupage.setCellValueFactory(new PropertyValueFactory<Employee, String>("groupage"));
		salaire.setCellValueFactory(new PropertyValueFactory<Employee, String>("salaire"));
		debut_travaille.setCellValueFactory(new PropertyValueFactory<Employee, String>("debut_travaille"));
		telephone.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephone"));
		userTable.setItems(user_List);
		MultipleSelectionModel<Employee> selected = userTable.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Employee>() {
			@Override
			public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
				input_EmployeeID = userTable.getSelectionModel().getSelectedItem().getUser_ID();
				lastName.setText(userTable.getSelectionModel().getSelectedItem().getNom());
				firstName.setText(userTable.getSelectionModel().getSelectedItem().getPrenom());
				dateOfBirth.setValue(LocalDate.parse(userTable.getSelectionModel().getSelectedItem().getDate_naissance()));
				sexe.setValue(userTable.getSelectionModel().getSelectedItem().getGender());
				blood.setValue(userTable.getSelectionModel().getSelectedItem().getGroupage());
				salary.setText(userTable.getSelectionModel().getSelectedItem().getSalaire());
				work_start.setValue(LocalDate.parse(userTable.getSelectionModel().getSelectedItem().getDebut_travaille()));
				phone_Number.setText(userTable.getSelectionModel().getSelectedItem().getTelephone());
				update.setDisable(false);
				supprimer.setDisable(false);
			}
		});
	}
 //=======================================================================================================================================
	public void update_Employee() throws SQLException{
		button_choice = confirme(updateMessage);
		if (button_choice.equals(ButtonType.YES)) {
		input_firstName = firstName.getText();
		input_LastName = lastName.getText();
		input_dateofBirth = dateOfBirth.getValue().toString();
		input_Sexe = sexe.getValue();
		input_blood = blood.getValue();
		input_Salary = salary.getText();
		input_Debut_Travaille = work_start.getValue().toString();
		input_phoneNumber = phone_Number.getText();
		Employee employee = new Employee(input_EmployeeID,
									   input_LastName,
									   input_firstName,
									   input_dateofBirth,
									   input_Sexe,
									   input_blood,
									   input_Salary,
									   input_Debut_Travaille,
									   input_phoneNumber);
		int row_updated = EmployeeTable.updateEmployee(employee);
		if (row_updated>0) {
			notification = new  NotificationControler();
			notification.upation_Notification(finishUpdateMessage);
			for (Employee employee2 : user_List) 
				if (employee2.getUser_ID()==employee.getUser_ID()) {
					user_List.remove(employee2);
					user_List.add(employee);
					break;
				}
		}
		else{
			notification = new  NotificationControler();
			notification.erreur_Updation_Notification(erreurUpdation);
		}
		update.setDisable(true);
		supprimer.setDisable(true);
		lastName.setText("");
		firstName.setText("");
		sexe.setValue("");
		blood.setValue("");
		salary.setText("");
		phone_Number.setText("");
		userTable.refresh();
		dateOfBirth.setValue(null);
		work_start.setValue(null);
		}
	}
 //=======================================================================================================================================
	public void delete_Employee() throws SQLException {
		button_choice = confirme(deleteMessage);
		if (button_choice.equals(ButtonType.YES)) {
		int row_deleted  = EmployeeTable.deleteEmployee(input_EmployeeID);
		if (row_deleted>0) {
			notification = new  NotificationControler();
			notification.deletion_Notification(finishDeleteMessage);
		}
		else{
			notification = new  NotificationControler();
			notification.erreur_Deletion_Notification(erreurDeletion);
		}
		update.setDisable(true);
		supprimer.setDisable(true);
		lastName.setText("");
		firstName.setText("");
		dateOfBirth.setValue(null);
		work_start.setValue(null);
		sexe.setValue("");
		blood.setValue("");
		salary.setText("");
		phone_Number.setText("");
		}
	}
//=======================================================================================================================================
	public void waiting_For_KeyEvents(JFXTextField textField){
		textField.setOnKeyPressed(e->{
			if (e.getCode()==KeyCode.ENTER) {		
				try {
					if(textField==firstName)
						employee = EmployeeTable.getEmployee_by_name(firstName.getText(), lastName.getText());			
					setText_forTextfields();
				} catch (NumberFormatException e1) {e1.printStackTrace();} 
				  catch (SQLException e1) {e1.printStackTrace();
				}
			}
		});
	}
 //=======================================================================================================================================
	public void setText_forTextfields() {
		lastName.setText(employee.getPrenom());
		firstName.setText(employee.getNom());
		dateOfBirth.setValue(LocalDate.parse(employee.getDate_naissance()));
		sexe.setValue(employee.getGender());
		blood.setValue(employee.getGroupage());
		salary.setText(employee.getSalaire());
		work_start.setValue(LocalDate.parse(employee.getDebut_travaille()));
		phone_Number.setText(employee.getTelephone());
	}
 //======================================================================================================================================
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
 //======================================================================================================================================
    public void close(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }
	
	
}
