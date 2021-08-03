import java.net.*;
import java.io.*;

public class RiverServer {
	private static RiverServer instance;
	private final int PORT_NUM = 3333;
	private ServerSocket ss;  
	private Socket s;  // will become local variable
	private DataInputStream din;  // will
	private DataOutputStream dout;  // will
	
	private RiverServer () throws IOException{
		ss=new ServerSocket(PORT_NUM);  
		s=ss.accept();  // will be moved down to the run server method
		din=new DataInputStream(s.getInputStream());  // move
		dout=new DataOutputStream(s.getOutputStream());  // move
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
	
	public void runServer() throws IOException{
		try {
			String str="";
			while(!str.equals("stop")){ 
				System.out.println(str);
				str=din.readUTF();    
				dout.writeUTF(str);
				dout.flush();  
			}
		}catch(IOException io) {
			System.out.println("Failed in run");
		}
	}
	
	public void closeServer() throws IOException{
		try {
			System.out.println("Server Closed");
			din.close(); 
			dout.close();////
			s.close();  
			ss.close();
		}catch(IOException io) {
			System.out.println("Failed to close");
		}
	}
}
