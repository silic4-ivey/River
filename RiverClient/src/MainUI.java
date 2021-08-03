import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Text; 


public class MainUI {
	
	private RiverClient rc;
	private String userName = "anon";
	private int size = 600;
	private Stage window;
	private Text textBox = new Text();
	
	public MainUI(Stage primaryStage) throws Exception{
		window = primaryStage;
		
		Label userLbl = new Label("Username:");
		TextField text = new TextField("anon");
				
		Button connectButton = new Button("Connect");
		connectButton.setOnAction(e ->startConnection(text.getText()));   ///////////////////////////////

		//VBox layout1 = new VBox();
		//layout1.getChildren().addAll(userLbl,text,connectButton);
		
		GridPane layout1 = new GridPane();
		
		layout1.setVgap(8);
		layout1.setHgap(10);
		
		/*layout1.add(userLbl, size/2 -1, size/2);
		layout1.add(text, size/2, size/2);
		layout1.add(connectButton, size/2, size/2 -1);*/
		
		GridPane.setConstraints(userLbl, 19, 20);
		GridPane.setConstraints(text, 20, 20);
		GridPane.setConstraints(connectButton, 20, 21);
		
		layout1.getChildren().addAll(userLbl,text,connectButton);

		layout1.setGridLinesVisible(false);
		Scene scene1 = new Scene(layout1, size, size);
		
		window.setScene(scene1);
		window.setTitle("The River");
		window.show();
		
		/*RiverClient cs = RiverClient.getInstance();
		cs.writeTo();
		cs.closeAccess();*/
	}
	
	public void startConnection(String userName) {
		initializeClient();  ///////////////////////////////////////////////////
		setUser(userName);
		switchStage();
	}
	
	public void initializeClient() {
		rc = RiverClient.getInstance();
	}
	
	public void setUser(String userName) {
		this.userName = userName;
	}
	
	public void switchStage() { 
		
		ServerReader sr = new ServerReader(rc, this);
		
		Label userLbl = new Label(userName + ": ");
		TextField text = new TextField("Type Here");
				
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> rc.writeTo(text.getText(), userName));   /////////////////////////////// create a logoff button
		
		Button logoffButton = new Button("Logoff");
		logoffButton.setOnAction(e -> rc.closeAccess());   /////////////////////////////// create a logoff button
		
		//Text textBox = new Text();
		
		
		GridPane layout1 = new GridPane();
		
		layout1.setVgap(8);
		layout1.setHgap(10);
		
		GridPane.setConstraints(userLbl, 19, 70);
		GridPane.setConstraints(text, 20, 70);
		GridPane.setConstraints(sendButton, 21, 70);
		GridPane.setConstraints(logoffButton, 22, 70);
		GridPane.setConstraints(textBox,20,0);
		
		layout1.getChildren().addAll(userLbl,text,sendButton, textBox, logoffButton);

		layout1.setGridLinesVisible(true); ////////// delete after
		Scene scene2 = new Scene(layout1, size, size);
		
		sr.start();  /////////////////////////////////
		
		window.setScene(scene2);
		window.setTitle("The River");
		window.show();
		
	}
	
	
	public void updateText(String s) {
		
		textBox.setText(textBox.getText() + "\n" + s);

		
	}

	

}
