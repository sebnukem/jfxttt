package net.eseb.jfxttt;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Controller controller;
	
	@Override
	public void start(Stage primary_stage) {
		try {
			FXMLLoader fxml_loader = new FXMLLoader();
			fxml_loader.setLocation(getClass().getResource("Main.fxml"));
			controller = new Controller();
			fxml_loader.setController(controller);
			root = fxml_loader.load();
			scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primary_stage;
			stage.setScene(scene);
			stage.setTitle("JFX TTT");
			stage.show();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
