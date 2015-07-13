package net.eseb.jfxttt;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
//	private Stage stage;
//	private Scene scene;
//	private Parent root;
//	private Controller controller;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxml_loader = new FXMLLoader();
			fxml_loader.setLocation(getClass().getResource("Main.fxml"));
			System.out.println("instantiate controller");
			Controller controller = new Controller(this, stage);
			System.out.println("bind controller");
			fxml_loader.setController(controller);
//			root = fxml_loader.load();
			System.out.println("load fxml");
			fxml_loader.load();
//			scene = new Scene(root, 400, 400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			stage.setScene(scene);
//			stage.setTitle("JFX TTT");
//			stage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
