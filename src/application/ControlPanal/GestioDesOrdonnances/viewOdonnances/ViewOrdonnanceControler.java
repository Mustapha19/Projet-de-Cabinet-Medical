package application.ControlPanal.GestioDesOrdonnances.viewOdonnances;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import application.ControlPanal.GestioDesOrdonnances.OrdonnanceTable;
import application.ControlPanal.GestioDesOrdonnances.viewOdonnances.ordonnanceSelected.OrdonnanceSelectedController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewOrdonnanceControler implements Initializable {

    @FXML private AnchorPane view_ord;
    @FXML private TableView<Ordonnance_Patient> ordonnance_info;
    @FXML private TableColumn<Ordonnance_Patient, String> nom;
    @FXML private TableColumn<Ordonnance_Patient, String> prenom;
    @FXML private TableColumn<Ordonnance_Patient, String> date_naissance;
    @FXML private TableColumn<Ordonnance_Patient,Integer> numero_Ord;
    @FXML private TableColumn<Ordonnance_Patient, String> date_Ord;
    @FXML private JFXTextField nom_input;
    @FXML private JFXTextField prenom_input;
    @FXML private JFXButton chercher;
    @FXML private JFXButton ouvrir;
    @FXML private JFXButton fermer;
    @FXML private JFXDatePicker date;
    private ObservableList<Ordonnance_Patient>list = FXCollections.observableArrayList();
    Ordonnance_Patient patientInfo;
    int num_Ord;
	String firstName;
	String laStName;
	String dateOrd;
	LocalDate birthdate;
	LocalDate now ;
	long age;
    
  //======================================================================================= 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			ResultSet by_name_result = OrdonnanceTable.select_All_Ord();
			while (by_name_result.next()) {
				list.add(new Ordonnance_Patient(
						by_name_result.getInt(1),
						by_name_result.getString(2),
						by_name_result.getString(3),
						by_name_result.getString(4),
						by_name_result.getInt(5),
						by_name_result.getString(6)));
			}
			by_name_result.close();
		} catch (SQLException e1) {e1.printStackTrace();}
		
		
		numero_Ord.setCellValueFactory(new PropertyValueFactory<Ordonnance_Patient, Integer>("numero_Ord"));
		nom.setCellValueFactory(new PropertyValueFactory<Ordonnance_Patient, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Ordonnance_Patient, String>("prenom"));
		date_naissance.setCellValueFactory(new PropertyValueFactory<Ordonnance_Patient, String>("date_naissance"));
		date_Ord.setCellValueFactory(new PropertyValueFactory<Ordonnance_Patient, String>("date_Ord"));
		ordonnance_info.setItems(list);
		MultipleSelectionModel<Ordonnance_Patient> selected = ordonnance_info.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Ordonnance_Patient>() {
			@Override
			public void changed(ObservableValue<? extends Ordonnance_Patient> observable,
					Ordonnance_Patient oldValue, Ordonnance_Patient newValue) {
				prenom_input.setText(ordonnance_info.getSelectionModel().getSelectedItem().getPrenom());
				nom_input.setText(ordonnance_info.getSelectionModel().getSelectedItem().getNom());
				date.setValue(LocalDate.parse(ordonnance_info.getSelectionModel().getSelectedItem().getDate_Ord()));
				num_Ord = ordonnance_info.getSelectionModel().getSelectedItem().getNumero_Ord();
				firstName = ordonnance_info.getSelectionModel().getSelectedItem().getPrenom();
				laStName = ordonnance_info.getSelectionModel().getSelectedItem().getNom();
				dateOrd = ordonnance_info.getSelectionModel().getSelectedItem().getDate_Ord();
				birthdate = LocalDate.parse(ordonnance_info.getSelectionModel().getSelectedItem().getDate_naissance());
				now = LocalDate.now();
				age  = ChronoUnit.YEARS.between(birthdate, now);
				
				ouvrir.setDisable(false);
				ordonnance_info.setOnKeyPressed(event->{
					if(event.getCode()==KeyCode.O){try {ouvrir();} catch (SQLException e) {e.printStackTrace();}}});
				}
		});
	}
//========================================================================================================
	public void Seaarch_By_Name() {
		list.clear();
		try {
			ResultSet by_name_result = OrdonnanceTable.select_Join_Ord_with_Patient(
																nom_input.getText(),
																prenom_input.getText());
			while(by_name_result.next()){
				list.add(new Ordonnance_Patient(
						by_name_result.getInt(1),
						by_name_result.getString(2),
						by_name_result.getString(3),
						by_name_result.getString(4),
						by_name_result.getInt(5),
						by_name_result.getString(6)));
			
			}
			by_name_result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ordonnance_info.setItems(list);	
		ordonnance_info.refresh();
		date.setOnKeyPressed(event->{
			if (event.getCode()==KeyCode.ENTER) {
				try {
					ResultSet by_name_result = OrdonnanceTable.select_Join_Ord_with_Patient(
																		nom_input.getText(),
																		prenom_input.getText(),
																		date.getValue().toString());
					while(by_name_result.next()){
						list.add(new Ordonnance_Patient(
								by_name_result.getInt(1),
								by_name_result.getString(2),
								by_name_result.getString(3),
								by_name_result.getString(4),
								by_name_result.getInt(5),
								by_name_result.getString(6)));
					}
					by_name_result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ordonnance_info.setItems(list);
			}
		});
	}
//========================================================================================================	
	public void ouvrir() throws SQLException {
		
		try {
			Stage upd_del_stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			AnchorPane afficher_Ord = loader.load(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/viewOdonnances/ordonnanceSelected/OrdonnanceSelected.fxml").openStream());
			OrdonnanceSelectedController switch_To_Afficher= (OrdonnanceSelectedController)loader.getController();
			switch_To_Afficher.set_Ordonnanace_Info(num_Ord,laStName,firstName,age+"",dateOrd);
			Scene scene = new Scene(afficher_Ord,1200,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/viewOdonnances/ordonnanceSelected/OrdonnanceSelected.css").toExternalForm());
			upd_del_stage.setScene(scene);
			upd_del_stage.setTitle("Afficher Ordonnance");
			upd_del_stage.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/history5.png").toString()));
			upd_del_stage.setResizable(false);
			upd_del_stage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
//========================================================================================================	
	public void fermer(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

}
