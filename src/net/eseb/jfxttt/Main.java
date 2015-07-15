package net.eseb.jfxttt;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class Main extends Application
{
	@Override
	public void start(Stage stage) {
		try {
			System.out.println("Main.start()");
			FXMLLoader fxml_loader = new FXMLLoader();
			fxml_loader.setLocation(getClass().getResource("Main.fxml"));
			System.out.println("instantiate controller");
			Controller controller = new Controller(stage);
			System.out.println("bind controller");
			fxml_loader.setController(controller);
			System.out.println("load fxml");
			Parent root = fxml_loader.load(); 

			Light.Distant light = new Light.Distant();
			light.setAzimuth(-135);
			light.setElevation(75);
			Lighting l = new Lighting(light);
			root.setEffect(l);

			Scene scene = new Scene(root, 400, 450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setOnKeyPressed(e -> controller.onSceneKeyPressed(e.getText()));

			stage.setScene(scene);
			stage.setMinWidth(180);
			stage.setMinHeight(240);
			stage.setTitle("JFX TTT");
//			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

			controller.play();
		} catch(Exception ex) {
			ex.printStackTrace(); 
		}
	}

//	public static String usage() {
//		String nl = System.getProperty("line.separator");
//		return "Tic Tac Toe usage: app [TYPE] [TYPE]" + nl + nl +
//				"  TYPE = h | c" + nl +
//				"        h: human player, default" + nl +
//				"        c: AI player";
//	}

	public static void main(String[] args) {

//		// Need some help?
//		for (String arg: args) {
//			if (arg.equals("--help") || arg.equals("-h") || arg.equals("-?")) {
//				System.out.println(usage());
//				return;
//			}
//		}
		launch(args);
	}
}
