package dai.gomoku.client.swing;

import java.io.*;
import java.net.*;

public class Connection {
	
	public Connection() {
		//connectToServer("localhost", 4010);
	}
	
	Socket clientSocket = null;
	private void connectToServer(String serverName, int port) {
		try {
			clientSocket = new Socket(serverName, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Socket returnSocket(String serverName, int port) throws IOException {
		return clientSocket = new Socket(serverName, port);
	}
	
	public void closeConnection() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
