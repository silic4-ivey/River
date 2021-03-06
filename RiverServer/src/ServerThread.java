import java.net.*;
import java.io.*;

/**
 * Thread for each client
 * @author Stefan Ilic
 *
 */
public class ServerThread extends Thread{
    protected Socket socket;
    private DataInputStream inp = null;
    private BufferedReader brinp = null;
    private DataOutputStream out = null;
    RiverServer rs = null;

    public ServerThread(Socket clientSocket, RiverServer rs) {
        this.socket = clientSocket;
        this.rs = rs;
        
        try {
            inp = new DataInputStream(socket.getInputStream());
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
    }
    
    /**
     * Continuously looks for new input from the client, if found sends to all clients using the River server class.
     */
    public void run() {

        String line;
        while (true) {
            try {
                line = inp.readUTF();
                System.out.println("Read line"+ line);
                rs.sendMessage(line);
                if (line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    
    /**
     * Writes message to clients
     * @param line
     */
    public void write(String line) {
        try {

        	out.writeUTF(line /*+ "\n\r"*/);
        	out.flush();
        } catch (IOException e) {
            return;
        }
    }
    
}
