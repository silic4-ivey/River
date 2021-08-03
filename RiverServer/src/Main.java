import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException {
		RiverServer rs = RiverServer.getInstance();
		rs.runServer();
		rs.closeServer();
	}
}
