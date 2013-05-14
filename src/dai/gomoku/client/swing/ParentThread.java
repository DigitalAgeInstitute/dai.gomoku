package dai.gomoku.client.swing;

import java.net.Socket;

public interface ParentThread {
	public void dispose();
	public Socket getSocket();
}
