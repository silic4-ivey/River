import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Run to start the client
 * @author Stefan Ilic
 *
 */
public class Main extends Application{
	
	private MainUI mainUI;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String args[]) {
		launch (args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		mainUI = new MainUI(primaryStage);

	}
}
