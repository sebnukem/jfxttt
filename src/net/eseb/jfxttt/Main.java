package net.eseb.jfxttt;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage stage) {
		try {
			System.out.println("Main.start()");
			FXMLLoader fxml_loader = new FXMLLoader();
			fxml_loader.setLocation(getClass().getResource("Main.fxml"));
			System.out.println("instantiate controller");
			Controller controller = new Controller(this, stage);
			System.out.println("bind controller");
			fxml_loader.setController(controller);
			System.out.println("load fxml");
			Parent root = fxml_loader.load();
			
			Scene scene = new Scene(root, 500, 525);
			controller.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setOnKeyPressed(e -> controller.onSceneKeyPressed(e.getText()));

			stage.setScene(scene);
			stage.setMinWidth(120);
			stage.setMinHeight(145);
			stage.setTitle("JFX TTT");
//			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch(Exception ex) {
			ex.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
