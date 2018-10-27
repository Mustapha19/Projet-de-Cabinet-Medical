package application.ControlPanal.gestionPatients;

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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GestionPationsController implements Initializable {

    @FXML private BorderPane patientsPanel;
    @FXML private Pane center_Pane;
    @FXML private JFXButton viewPatients;
    @FXML private JFXButton addPatients;
    @FXML private JFXButton modefierPatients;
    @FXML private Pane top_Pane;
    @FXML private JFXButton goBack;
  //=======================================================================================================================================     
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    
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
		
		JFXDepthManager.setDepth(top_Pane, 4);
	}

    
    
  //======================================================================================================================================= 
    @FXML
    void go_BackToControlPanel(ActionEvent event) {
    	
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
    @FXML
    void view_Pateints(ActionEvent event) {
    	
    	try {
			
			Stage secondStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_View;
			root_View = loader.load(getClass().getResource("/application/ControlPanal/gestionPatients/viewPatients/ViewPatients.fxml").openStream());
			
			Scene scene = new Scene(root_View,1084,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionPatients/viewPatients/ViewPatients.css").toExternalForm());
			
			secondStage.setScene(scene);
			secondStage.setTitle("List des Patients");
			secondStage.setResizable(false);
			secondStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
  //======================================================================================================================================= 
    @FXML
    void add_new_Patient(ActionEvent event) {
    	
    	try {
			
			Stage addPatientStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_AddPatient;
			root_AddPatient = loader.load(getClass().getResource("/application/ControlPanal/gestionPatients/addPatients/Patients.fxml").openStream());
			Scene scene = new Scene(root_AddPatient,850,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionPatients/addPatients/Patients.css").toExternalForm());
			addPatientStage.setScene(scene);
			addPatientStage.setResizable(false);
			addPatientStage.setTitle("Ajouter un Patient");
			addPatientStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	
    	
    }

  //======================================================================================================================================= 
    @FXML
    void update_Or_Delete_Patient(ActionEvent event) {
    	
    	try {
			
			Stage upd_del_stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_UpdDEL;
			root_UpdDEL = loader.load(getClass().getResource("/application/ControlPanal/gestionPatients/updateOrDelete/UpdateOrDelete.fxml").openStream());
			Scene scene = new Scene(root_UpdDEL,1200,680);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionPatients/updateOrDelete/UpdateOrDelete.css").toExternalForm());
			upd_del_stage.setScene(scene);
			upd_del_stage.setTitle("UPDATE/DELETE Patient");
			upd_del_stage.setResizable(false);
			upd_del_stage.show();
		}catch (IOException e) {e.printStackTrace();}
    	
    	
    }

 
   
	

}
