package application.DataBaseLogin;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginControler implements Initializable{
	
	@FXML private AnchorPane root;
	@FXML private JFXTextField user_name_input;
	@FXML private JFXPasswordField password_input;
	@FXML private Label loginText;
	@FXML private StackPane stackPane;
	private UserLogin user;
	private String username ;
	private String password;
 //=======================================================================================================================================	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(root, 4);
		JFXDepthManager.setDepth(loginText, 4);
		JFXDepthManager.setDepth(user_name_input, 4);
		JFXDepthManager.setDepth(password_input, 4);
		
	}
 //=======================================================================================================================================
	public boolean login() throws Exception {
		 username = user_name_input.getText();
		 password = password_input.getText();
		
		user = new UserLogin(username, password);
		return ConnectToDataBase.is_a_user(user);
	}
 //=======================================================================================================================================
	public void check(ActionEvent event) throws Exception {
		if(login()){
			((Node)event.getSource()).getScene().getWindow().hide();  
			Stage mainStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			BorderPane root_Control= loader.load(getClass().getResource("/application/ControlPanal/ControlPanal.fxml").openStream());
			Scene scene = new Scene(root_Control);
			scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/ControlPanal.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.setFullScreen(true);
			mainStage.getIcons().add(new Image(this.getClass().getResource("/application/ControlPanal/ControlPanalImage/cabinet2.png").toString()));
			mainStage.setTitle("Cabinet Medicale");
			mainStage.show();
		}else {
			loadDailog();
		}
		user_name_input.setText("");
		password_input.setText("");
		
	}
 //=======================================================================================================================================	
	public void clsoe() {
		System.exit(0);
		this.clsoe();
	}
 //=======================================================================================================================================
	public void loadDailog() {
		 javafx.scene.text.Font headerfont = new javafx.scene.text.Font(20);
		 javafx.scene.text.Font font = new javafx.scene.text.Font(16);
		 Text text =new Text("Sorry !!!");
		 text.setFont(headerfont);
		 JFXDialogLayout dialogLayout = new JFXDialogLayout();
		 dialogLayout.setHeading(text);
		 Text textMessagee = new Text("The User name or the password is incorect \n Please try again");
		 textMessagee.setFont(font);
		 dialogLayout.setBody(textMessagee);
		 JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
		 dialog.toFront();
		 JFXButton fermer = new JFXButton("Fermer");
		 fermer.setCancelButton(true);
		 fermer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		 dialogLayout.setActions(fermer);
		 dialog.show();
	}
}
