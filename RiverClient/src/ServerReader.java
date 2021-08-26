/**
 * Thread for reading every second from the server
 * @author Stefan Ilic
 *
 */
public class ServerReader extends Thread{
	
	private RiverClient rc;
	//private Thread t;
	private MainUI mUI;
	
	/**
	 * Initialization of client and main UI
	 * @param rc
	 * @param mUI
	 */
	public ServerReader(RiverClient rc, MainUI mUI) {
		this.rc = rc;
		this.mUI = mUI;
	}
	
	/**
	 * Reads information from the server and updates the MainUI
	 */
	public void run() {
		String str = "";
		while(rc.getStatus()) {//(str!="stop")
			str = rc.readFrom();
			//if(!str.equals(""))
			mUI.updateText(str);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("Interrupt");
			}
		}
	}
}
