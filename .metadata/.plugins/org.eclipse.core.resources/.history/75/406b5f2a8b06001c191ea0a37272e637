
public class ServerReader extends Thread{
	
	private RiverClient rc;
	//private Thread t;
	private MainUI mUI;
	
	public ServerReader(RiverClient rc, MainUI mUI) {
		this.rc = rc;
		this.mUI = mUI;
	}
	
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
