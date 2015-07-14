package net.eseb.jfxttt;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
			fxml_loader.load();
		} catch(Exception ex) {
			ex.printStackTrace(); 
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
