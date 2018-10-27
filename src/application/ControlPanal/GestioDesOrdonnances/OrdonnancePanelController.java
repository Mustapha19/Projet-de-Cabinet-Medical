package application.ControlPanal.GestioDesOrdonnances;

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
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrdonnancePanelController implements Initializable {
	@FXML
	private AnchorPane ordonnancePanel;
	@FXML
	private Pane top_Pane;
	@FXML
	private JFXButton goBack;
	@FXML
	private JFXButton searchOrdon;
	@FXML
	private JFXButton faireOrdonnance;

	// Event Listener on JFXButton[#goBack].onAction
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
	// Event Listener on JFXButton[#searchOrdon].onAction
	@FXML
	public void search_for_Ord(ActionEvent event) {
		
		try {
			Stage stageRDV = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_RDV;
			root_RDV = loader.load(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/viewOdonnances/ViewOrdonnance.fxml").openStream());
			Scene scene = new Scene(root_RDV,850,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/viewOdonnances/ViewOrdonnance.css").toExternalForm());
			stageRDV.setScene(scene);
			stageRDV.setResizable(false);
			stageRDV.setTitle("La Recherche D'une Ordonnance");
			stageRDV.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	// Event Listener on JFXButton[#faireOrdonnance].onAction
	@FXML
	public void faire_ordonnance(ActionEvent event) {

		try {
			
			Stage secondStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root_View;
			root_View = loader.load(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/faireOrdonnances/AjouterOrdonnace.fxml").openStream());
			Scene scene = new Scene(root_View,1250,650);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/GestioDesOrdonnances/faireOrdonnances/AjouterOrdonnace.css").toExternalForm());
			secondStage.setScene(scene);
			secondStage.setTitle("Faire Une Ordonnance");
			secondStage.setResizable(false);
			secondStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(faireOrdonnance, 4);
		JFXDepthManager.setDepth(goBack, 4);
		JFXDepthManager.setDepth(ordonnancePanel, 4);
		JFXDepthManager.setDepth(searchOrdon, 4);
		JFXDepthManager.setDepth(top_Pane, 4);
		
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
