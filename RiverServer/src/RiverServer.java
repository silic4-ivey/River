import java.net.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Server that handles requests from all clients
 * @author Stefan Ilic
 *
 */
public class RiverServer {
	private static RiverServer instance;
	private final int PORT_NUM = 3333;
	private ServerSocket ss;  
	private Socket s;  
	ArrayList<ServerThread> allClients = new ArrayList<ServerThread>();  // Reference to all clients

	
	private RiverServer () throws IOException{
		ss=new ServerSocket(PORT_NUM);  
	}
	
	public static RiverServer getInstance() {
		
		if(instance==null) {
			try {
				instance = new RiverServer();
			}catch(IOException io) {
				System.out.println("Initialization Failed");
			}
		}
		return instance;
		
	}
	
	/**
	 * Continues to accept connections from new clients, initializes a new thread whenever a connection is accepted
	 * @throws IOException
	 */
	public void runServer() throws IOException{
		
		//Socket s;
		try {
			String str="";
			while(!str.equals("QUIT")){ 
				
				s=ss.accept(); ////////////////////////
				ServerThread servT = new ServerThread(s, this);
				servT.start();/////////////
				allClients.add(servT);
				  
			}
		}catch(IOException io) {
			System.out.println("Failed in run");
		}
	}
	
	/**
	 * Sends message to all the clients
	 * @param line
	 */
	public void sendMessage(String line) {
		for(ServerThread st : allClients) {
			if(st.isAlive()) {
				st.write(line);
			}else {
				allClients.remove(st);
			}
		}
		
	}
	
	/**
	 * Closes the server
	 * @throws IOException
	 */
	public void closeServer() throws IOException{
		try {
			System.out.println("Server Closed");

			ss.close();
		}catch(IOException io) {
			System.out.println("Failed to close");
		}
	}
}
