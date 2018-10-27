package application.ControlPanal;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlPanalController implements Initializable{
	
	@FXML Pane top_Pane;
	@FXML private BorderPane root;
	@FXML private Label display_username;	
    @FXML  private JFXButton gestionPatients;
    @FXML private JFXButton gestionEmployees;
    @FXML private JFXButton gestionMedicament;
    @FXML private JFXButton rendez_vous;
    @FXML private JFXButton ordonnance;
    @FXML private JFXButton singOut;
    private String exitMessage = "Do you really want to exit";
	
//===============================================================================	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(display_username, 4);
		JFXDepthManager.setDepth(top_Pane, 4);	
	}
	
//=======================================================================================
	//confirmation method that display an AlertDialog when the user wants to sing out
	@SuppressWarnings("static-access")
	public ButtonType confirme(String message) {
		ButtonType[] yes = {new ButtonType("YES").YES,new ButtonType("NO").CANCEL};
		Alert confirmation = new Alert(AlertType.CONFIRMATION,"",yes);
		confirmation.setTitle("Exit Confirmation");
		confirmation.setHeaderText(message);
		confirmation.setResult(yes[1]);
		confirmation.showAndWait();
		return confirmation.getResult();		
	}
 //=======================================================================================	
	//method for sign out Button
	public void singOut(ActionEvent event){
		ButtonType answer= confirme(exitMessage);
		if (answer.equals(ButtonType.YES)) {
			try {
				Alert goodbye = new Alert(AlertType.INFORMATION,"See you again",ButtonType.CLOSE);
				goodbye.setTitle("Goodbye");
				goodbye.setHeaderText("Have a good time");
			
				goodbye.showAndWait();
				((Node)event.getSource()).getScene().getWindow().hide();  
				Stage secondStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root_Control = loader.load(getClass().getResource("/application/DataBaseLogin/Login.fxml").openStream());
				Scene scene = new Scene(root_Control,700,600);
				scene.getStylesheets().add(getClass().getResource("/application/DataBaseLogin/login.css").toExternalForm());
				secondStage.setScene(scene);
				secondStage.setResizable(false);
				secondStage.setTitle("Login");
				secondStage.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
				secondStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
  //=======================================================================================
	
	public void gestion_Patients(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();  
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDV;
			root_RDV = loader.load(getClass().getResource("/application/ControlPanal/gestionPatients/GestionPations.fxml").openStream());
			Scene scene = new Scene(root_RDV,1365,710);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionPatients/GestionPations.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setFullScreen(true);
		//	stageRDV.setResizable(false);
			stageRDV.setTitle("Gestion Des Patients");
			stageRDV.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	//=======================================================================================
	
	public void gestionRDV(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();  
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDV;
			root_RDV = loader.load(getClass().getResource("/application/ControlPanal/gestionRDV/RDVControlPanal.fxml").openStream());
			Scene scene = new Scene(root_RDV,1365,710);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionRDV/RDVControlPanal.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setTitle("Gestion Des Rendez-Vous");
			stageRDV.setFullScreen(true);
			stageRDV.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//=======================================================================================
	
	public void gestion_Employees(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();  
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_ordon;
			root_ordon = loader.load(getClass().getResource("/application/ControlPanal/gestionEmployees/GestionEmployees.fxml").openStream());
			Scene scene = new Scene(root_ordon,1365,710);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionEmployees/GestionEmployees.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setFullScreen(true);
			stageRDV.setResizable(false);
			stageRDV.setTitle("Gestion Des Employees");
			stageRDV.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//=======================================================================================
	
		public void gestion_Ordonnances(ActionEvent event) {
			try {
				((Node)event.getSource()).getScene().getWindow().hide();  
				Stage stageOrd  = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root_Ordon;
				root_Ordon = loader.load(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/OrdonnancePanel.fxml").openStream());
				Scene scene = new Scene(root_Ordon,1365,710);
				scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/OrdonnancePanel.css").toExternalForm());
				stageOrd.setScene(scene);
				stageOrd.setFullScreen(true);
				stageOrd.setResizable(false);
				stageOrd.setTitle("Gestion Des Ordonnances");
				stageOrd.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
				stageOrd.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}	
	
	//=======================================================================================
		
		public void gestion_Medicament(ActionEvent event) {
			try {
				((Node)event.getSource()).getScene().getWindow().hide();  
				Stage stageMed  = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root_Med;
				root_Med = loader.load(getClass().getResource("/application/ControlPanal/gestionMedicament/GestionMedicament.fxml").openStream());
				Scene scene = new Scene(root_Med,1365,710);
				scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionMedicament/GestionMedicament.css").toExternalForm());
				stageMed.setScene(scene);
				stageMed.setFullScreen(true);
				stageMed.setResizable(false);
				stageMed.setTitle("Gestion Des Ordonnances");
				stageMed.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
				stageMed.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}	
	

}
