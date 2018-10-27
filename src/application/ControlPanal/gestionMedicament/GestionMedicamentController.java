package application.ControlPanal.gestionMedicament;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GestionMedicamentController implements Initializable{
	
	@FXML private AnchorPane medicamentPanel;

    @FXML private Pane top_Pane;
    
    @FXML private JFXButton back;

    @FXML private JFXButton addMedicament;

    @FXML private JFXButton updateMedicament;
    //===============================Initialize Method=================================
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	JFXDepthManager.setDepth(top_Pane, 4);
    	JFXDepthManager.setDepth(updateMedicament, 4);
    	JFXDepthManager.setDepth(addMedicament, 4);
    	JFXDepthManager.setDepth(back, 4);
    	
    	back.setOnKeyPressed(event->{
			if (event.getCode()==KeyCode.BACK_SPACE) {
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
		});
    	
	}
  //===============================add_Medicament Method=================================

    @FXML
    void add_Medicament(ActionEvent event) {
    	try { 
			Stage stageMed  = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_Med;
			root_Med = loader.load(getClass().getResource("/application/ControlPanal/gestionMedicament/addMedicament/AddMedicament.fxml").openStream());
			Scene scene = new Scene(root_Med,1300,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionMedicament/addMedicament/AddMedicament.css").toExternalForm());
			stageMed.setScene(scene);
			stageMed.setResizable(false);
			stageMed.setTitle("Ajouter un Médicament");
			stageMed.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
   //==================================update_Medicament Method=====================================

    @FXML public void update_Medicament(ActionEvent event) {
    	try { 
			Stage stageMed  = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_Med;
			root_Med = loader.load(getClass().getResource("/application/ControlPanal/gestionMedicament/updateOrDeleteMedicament/UpdateOrDeleteMedicament.fxml").openStream());
			Scene scene = new Scene(root_Med,1300,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionMedicament/updateOrDeleteMedicament/UpdateOrDeleteMedicament.css").toExternalForm());
			stageMed.setScene(scene);
			stageMed.setResizable(false);
			stageMed.setTitle("Update/Delete Médicament");
			stageMed.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
  //===============================go_BackToControlPanel Method=================================
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
   


}
