import java.io.IOException;
/**
 * Main method, run to start the server.
 * @author Stefan Ilic
 *
 */
public class Main {
	public static void main(String args[]) throws IOException {
		RiverServer rs = RiverServer.getInstance();
		rs.runServer();
		rs.closeServer();
	}
}
