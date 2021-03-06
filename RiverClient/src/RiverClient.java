import java.net.*;
import java.io.*;

/**
 * Client backend that reads and writes messages from the Server.
 * @author Stefan Ilic
 *
 */
public class RiverClient {
	private static RiverClient instance;
	private final int PORT_NUM = 3333;
	private Socket s;  
	private DataInputStream din;  
	private DataOutputStream dout;  
	private BufferedReader br;
	private Boolean status;
	
	/**
	 * Initializes input, output, and sockets
	 */
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
	
	/**
	 * Singleton design pattern to avoid multiple connections.
	 * @return itself
	 */
	public static RiverClient getInstance() {
		
		if(instance==null) {
			instance = new RiverClient();
		}
		return instance;
		
	}
	
	/**
	 * Writes to the ouput stream
	 * @param s string to write
	 * @param userName username to write
	 */
	public void writeTo(String s, String userName) {
		try {

			dout.writeUTF(userName + ": " + s);  
			dout.flush();  

		}catch(IOException io) {
			System.out.println("Failed to write");
			
		}
	}
	
	/**
	 * Reads information from server.
	 * @return
	 */
	public String readFrom() {
		try {
			return din.readUTF();
		}catch(IOException io) {
			return "";
		}
	}
	
	/**
	 * Closes access to the server.
	 */
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
	
	/**
	 * Gets status of the client
	 * @return
	 */
	public Boolean getStatus() {
		return status;
	}
}
