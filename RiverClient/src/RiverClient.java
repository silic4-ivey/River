import java.net.*;
import java.io.*;

public class RiverClient {
	private static RiverClient instance;
	private final int PORT_NUM = 3333;
	private Socket s;  
	private DataInputStream din;  
	private DataOutputStream dout;  
	private BufferedReader br;
	private Boolean status;
	
	private RiverClient() {
		try {
			s=new Socket("localhost",PORT_NUM); 
			din=new DataInputStream(s.getInputStream());  
			dout=new DataOutputStream(s.getOutputStream());  
			br=new BufferedReader(new InputStreamReader(System.in)); 
			status = true;
		}catch(IOException io) {
			System.out.println("Failed to initialize client");
			
		}
	}
	
	public static RiverClient getInstance() {
		
		if(instance==null) {
			instance = new RiverClient();
		}
		return instance;
		
	}
	
	public void writeTo(String s, String userName) {
		try {

			//String str="",str2="";  
			//while(!str.equals("stop")){
				//str=br.readLine();  
			dout.writeUTF(userName + ": " + s);  
			dout.flush();  
				//str2=din.readUTF();  		/////////////////
				//System.out.println(str2);  /////////////////
			
			//if(s.equals("stop")) {
				//closeAccess();
			//}
			//} 
		}catch(IOException io) {
			System.out.println("Failed to write");
			
		}
	}
	
	public String readFrom() {
		try {
			return din.readUTF();
		}catch(IOException io) {
			return "";
		}
	}
	
	public void closeAccess() {
		try {
			System.out.println("Client closed");
			dout.close();  
			din.close();//////
			s.close(); 
			status = false;
		}catch(IOException io) {
			System.out.println("Failed to close access");
		
		}
	}
	
	public Boolean getStatus() {
		return status;
	}
}
