package dai.gomoku.client.swing;

import java.io.*;
import java.net.*;

public class Requests {
	String user, pass;
	Socket clientSocket = null;
	OutputStream outToServer = null;
	
	public Requests() {
		//connectToServer("localhost", 4010);
		//new Connection();
	}
	
	/*private void connectToServer(String serverName, int port) {
		try {
			clientSocket = new Socket(serverName, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	public String generateLoginRequest(String user, String pass) {
		return "{ \"type\":\"LOGIN\", \"username\":\""+user+"\", \"password\":\""+pass+"\" }";
	}

	public String generateSignUpRequest(String firstName, String lastName, String email, int phoneNo ,String user, String pass) {
		return "{ \"type\":\"SIGNUP\", \"firstname\":\""+firstName+"\", \"lastname\":\""+firstName+"\"," +
				" \"email\":\""+email+"\", \"phoneNo\":\""+phoneNo+"\"," +
						"\"username\":\""+user+"\", \"password\":\""+pass+"\",}";
	}
	
	public String generateMoveRequest(String gameID, String user, int row, int col) {
		return "{ \"type\":\"MAKEMOVE\", \"gameID\":\""+gameID+"\", \"username\":\""+user+"\"," +
				" \"row\":\""+row+"\", \"col\":\""+col+"\"}";
	}
	
	public void sendRequests(String request) {
		//connectToServer("localhost", 9203);
		try {
			outToServer = new Connection().returnSocket("localhost", 4010).getOutputStream();
			PrintWriter out = new PrintWriter(outToServer);

			out.write("\n[STARTJSON]\n"+request+"\n[ENDJSON]\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//closeConnection();
	}
	
	private void closeConnection() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
