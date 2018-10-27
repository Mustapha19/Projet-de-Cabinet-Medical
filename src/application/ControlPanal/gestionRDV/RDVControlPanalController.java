package application.ControlPanal.gestionRDV;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RDVControlPanalController implements Initializable {
	@FXML
	private BorderPane RdvPanel;
	@FXML
	private Pane top_Pane;
	
	@FXML
	private JFXButton goBack;
	@FXML
	private JFXButton view_patients;
	@FXML
	private JFXButton add_patient;
	@FXML
	private JFXButton update_delete;
	@FXML
	private JFXButton rendez_vous;
	@FXML
	private JFXButton ordonnance;
	

	// Event Listener on JFXButton[#view_patients].onAction
	@FXML
	public void view_RDV(ActionEvent event) {
		
		
		try {
			
			Stage viewRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_viewRDV;
			root_viewRDV = loader.load(getClass().getResource("/application/ControlPanal/gestionRDV/viewRDV/ViewRDV.fxml").openStream());
			Scene scene = new Scene(root_viewRDV,1000,600);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionRDV/viewRDV/ViewRDV.css").toExternalForm());
			viewRDV.setScene(scene);
			viewRDV.setResizable(false);
			viewRDV.setTitle("List des RDV");
			viewRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Event Listener on JFXButton[#add_patient].onAction
	@FXML
	public void add_new_RDV(ActionEvent event) {
		try {
					
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDV;
			root_RDV = loader.load(getClass().getResource("/application/ControlPanal/gestionRDV/addRDV/AddRDV.fxml").openStream());
			Scene scene = new Scene(root_RDV,1000,600);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionRDV/addRDV/AddRDV.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setResizable(false);
			stageRDV.setTitle("Ajouter un RDV");
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Event Listener on JFXButton[#update_delete].onAction
	@FXML
	public void update_Or_Delete_RDV(ActionEvent event) {
		
		try {
			
			Stage updateRDVStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDVupdate;
			root_RDVupdate = loader.load(getClass().getResource("/application/ControlPanal/gestionRDV/updateOrDelete/DeleteOrUpdateRDV.fxml").openStream());
			Scene scene = new Scene(root_RDVupdate,1000,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionRDV/updateOrDelete/DeleteOrUpdateRDV.css").toExternalForm());
			updateRDVStage.setScene(scene);
			updateRDVStage.setResizable(false);
			updateRDVStage.setTitle("UPDATE/DELETE RDV");
			updateRDVStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	// Event Listener on JFXButton[#rendez_vous].onAction
	@FXML
	public void go_BackToControlPanel(ActionEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(top_Pane, 4);
		JFXDepthManager.setDepth(RdvPanel, 4);
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
	
}
