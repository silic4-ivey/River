import javafx.geometry.Pos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text; 
import javafx.scene.control.ScrollPane;

/**
 * The main user interface of the client
 * @author Stefan Ilic
 *
 */
public class MainUI {
	
	private RiverClient rc;
	private String userName = "anon";
	private int size = 600;
	private Stage window;
	private Text textBox = new Text();
	private final String modeSeven = "Modeseven-L3n5.ttf";
	private final String fontPath = "./resources/fonts/";
	
	public MainUI(Stage primaryStage) throws Exception{
		window = primaryStage;
		
		Label userLbl = new Label("Username:");
		TextField text = new TextField("anon");
		
				
		Button connectButton = new Button("Connect");
		connectButton.setOnAction(e ->startConnection(text.getText()));
		
		/*try {
			Font terminalFont = Font.loadFont(new FileInputStream(new File(fontPath + modeSeven)),12);
			
			text.setFont(terminalFont);
			userLbl.setFont(terminalFont);
			connectButton.setFont(terminalFont);


		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}*/

		
		GridPane layout1 = new GridPane();
		
		layout1.setVgap(8);
		layout1.setHgap(10);
		
		
		GridPane.setConstraints(userLbl, 19, 20);
		GridPane.setConstraints(text, 20, 20);
		GridPane.setConstraints(connectButton, 20, 21);
		
		layout1.getChildren().addAll(userLbl,text,connectButton);

		layout1.setGridLinesVisible(false);
		
		Scene scene1 = new Scene(layout1, size, size);
		scene1.getStylesheets().add("application.css");
		
		window.setScene(scene1);
		window.setTitle("The River");
		window.show();
		
	}
	
	/**
	 * Starts connection to the server with the provided userName
	 * @param userName the user's username
	 */
	public void startConnection(String userName) {
		initializeClient();
		setUser(userName);
		switchStage();
	}
	
	/**
	 * Initializes the client backend
	 */
	public void initializeClient() {
		rc = RiverClient.getInstance();
	}
	
	/**
	 * Sets user name
	 * @param userName
	 */
	public void setUser(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Switches the screen to the second stage
	 */
	public void switchStage() { 
		
		ServerReader sr = new ServerReader(rc, this);
		
		Label userLbl = new Label(userName + ": ");
		TextField text = new TextField("Type Here");
				
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> rc.writeTo(text.getText(), userName));
		
		Button logoffButton = new Button("Logoff");
		logoffButton.setOnAction(e -> rc.closeAccess());  	

		GridPane layout1 = new GridPane();
		
		layout1.setVgap(8);
		layout1.setHgap(10);
		
		GridPane.setConstraints(userLbl, 19, 2);
		GridPane.setConstraints(text, 20, 2);
		GridPane.setConstraints(sendButton, 21, 2);
		GridPane.setConstraints(logoffButton, 22, 2);
		GridPane.setConstraints(textBox,20,0);
		
		
		layout1.getChildren().addAll(userLbl,text,sendButton, textBox, logoffButton);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(1, 1);
		scrollPane.setContent(textBox);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		layout1.setGridLinesVisible(false);
		Scene scene2 = new Scene(layout1, size, size);
		scene2.setFill(Color.BLACK);

		scene2.getStylesheets().add("application.css");
		
		sr.start();
		
		window.setScene(scene2);
		window.setTitle("The River");
		window.show();
		
	}
	
	/**
	 * Updates the text on the screen
	 * @param s the text to be shown on the screen
	 */
	public void updateText(String s) {
		
		textBox.setText(textBox.getText() + "\n" + s);

		
	}

}
