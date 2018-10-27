package application.ControlPanal.gestionEmployees;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GestionEmployeesController implements Initializable {

	@FXML private BorderPane userPanel;
    @FXML private Pane center_Pane;
    @FXML private JFXButton viewUsers;
    @FXML private JFXButton addUser;
    @FXML private JFXButton Edit_User;
    @FXML private Pane top_Pane;
    @FXML private Label header;
    @FXML private JFXButton goBack;
  //=======================================================================================================================================     
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	JFXDepthManager.setDepth(top_Pane, 4);	
    	JFXDepthManager.setDepth(goBack, 4);	
    	JFXDepthManager.setDepth(header, 4);	
    	JFXDepthManager.setDepth(Edit_User, 4);
    	JFXDepthManager.setDepth(userPanel, 4);
    	
    	goBack.setOnKeyPressed(evt->{
			if (evt.getCode()==KeyCode.BACK_SPACE) {
				try {
					((Node)evt.getSource()).getScene().getWindow().hide();  
					Stage stageRDV = new Stage();
					FXMLLoader loader = new FXMLLoader();
					Pane root_RDV;
					root_RDV = loader.load(getClass().getResource("/application/ControlPanal/ControlPanal.fxml").openStream());
					Scene scene = new Scene(root_RDV,1365,710);
					scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/ControlPanal.css").toExternalForm());
					stageRDV.setScene(scene);
					stageRDV.setFullScreen(true);
					stageRDV.setResizable(false);
					stageRDV.setTitle("Cabinet Medicale");
					stageRDV.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
					stageRDV.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	
	}

    
    
  //======================================================================================================================================= 
    @FXML public void go_BackToControlPanel(ActionEvent event) {
    	
    	try {
			((Node)event.getSource()).getScene().getWindow().hide();  
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDV;
			root_RDV = loader.load(getClass().getResource("/application/ControlPanal/ControlPanal.fxml").openStream());
			Scene scene = new Scene(root_RDV,1365,710);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/ControlPanal.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setFullScreen(true);
			stageRDV.setResizable(false);
			stageRDV.setTitle("Cabinet Medicale");
			stageRDV.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

 //=======================================================================================================================================   
    @FXML public void view_Users(ActionEvent event) {
    	
    	try {
			
			Stage secondStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_View;
			root_View = loader.load(getClass().getResource("/application/ControlPanal/gestionEmployees/viewEmployeeList/ViewEmployee.fxml").openStream());
			
			Scene scene = new Scene(root_View,1300,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionEmployees/viewEmployeeList/ViewEmployee.css").toExternalForm());
			
			secondStage.setScene(scene);
			secondStage.setTitle("List des Employées");
			secondStage.setResizable(false);
			secondStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
  //======================================================================================================================================= 
    @FXML public void add_new_User(ActionEvent event) {
    	
    	try {
			
			Stage addPatientStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_AddPatient;
			root_AddPatient = loader.load(getClass().getResource("/application/ControlPanal/gestionEmployees/addEmployee/AddEmployee.fxml").openStream());
			Scene scene = new Scene(root_AddPatient,850,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionEmployees/addEmployee/AddEmployee.css").toExternalForm());
			addPatientStage.setScene(scene);
			addPatientStage.setResizable(false);
			addPatientStage.setTitle("Ajouter un Employée");
			addPatientStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	
    	
    }

  //======================================================================================================================================= 
    @FXML public  void update_Or_Delete_User(ActionEvent event) {
    	
    	try {
			
			Stage upd_del_stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_UpdDEL;
			root_UpdDEL = loader.load(getClass().getResource("/application/ControlPanal/gestionEmployees/updateOrDeleteEmployee/UpdateOrDeleteEmployee.fxml").openStream());
			Scene scene = new Scene(root_UpdDEL,1200,680);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionEmployees/updateOrDeleteEmployee/UpdateOrDeleteEmployee.css").toExternalForm());
			upd_del_stage.setScene(scene);
			upd_del_stage.setTitle("UPDATE/DELETE Employées");
			//upd_del_stage.setResizable(false);
			upd_del_stage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }


}
