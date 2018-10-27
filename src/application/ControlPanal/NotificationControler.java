package application.ControlPanal;
import org.controlsfx.control.Notifications;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class NotificationControler{

	public void insertion_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/success.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(75.0);
		imageView.setFitHeight(75.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Inserted into DATABASE")
				.graphic(imageView)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
	public void upation_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/success.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(75.0);
		imageView.setFitHeight(75.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Change saved into DATABASE")
				.graphic(new ImageView(image))
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
	public void deletion_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/success.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(75.0);
		imageView.setFitHeight(75.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Deleted From DATABASE")
				.graphic(new ImageView(image))
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
	public void erreur_Deletion_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/error.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(32.0);
		imageView.setFitHeight(32.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Can't Delete DATA from Database")
				.graphic(new ImageView(image))
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
	
	public void erreur_Updation_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/error.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(32.0);
		imageView.setFitHeight(32.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Can't Update the DATA")
				.graphic(new ImageView(image))
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
	public void erreur_Insetion_Notification(String message){
		Image image = new Image("/application/ControlPanal/ControlPanalImage/error.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(32.0);
		imageView.setFitHeight(32.0);
		Notifications notifications = Notifications.create()
				.title(message)
				.text("Can't Insert Into DataBase")
				.graphic(new ImageView(image))
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT);
		notifications.darkStyle();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notifications.show();
			}
		});
	}
}
