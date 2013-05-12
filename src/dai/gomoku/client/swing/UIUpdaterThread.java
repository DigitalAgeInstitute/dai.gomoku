package dai.gomoku.client.swing;

import java.util.Properties;


public class UIUpdaterThread implements Runnable {
	ResponseParser parser = new ResponseParser();
	static boolean loginStatus = false, signUpStatus = false;
	static String move = "";
	public static void intepretRespose(ResponseParser parser) {
		switch (parser.getType()) {
		case "SIGNUPRESPONSE":
			signUpStatus = signUpUser(parser);
		case "LOGINRESPONSE":
			loginStatus = loginUser(parser);
		case "MAKEMOVERESPONSE":
			makeMove(parser);
		case "LOGOUTRESPONSE":
			logoutUser(parser);
		default:
			
		}
	}

	public static boolean loginUser(ResponseParser parser) {
		return parser.getStatus().equalsIgnoreCase("OK");
	}
	
	public static String makeMove(ResponseParser parser) {
		if(parser.getStatus().equalsIgnoreCase("OK")) {
			move = parser.getUsername() + ":" + parser.getRow() + " , " + parser.getCol();
		}
		return move;
	}
	
	public static boolean signUpUser(ResponseParser parser) {
		return parser.getStatus().equalsIgnoreCase("OK");
	}

	public static void logoutUser(ResponseParser parser) {
		if(parser.getStatus().equalsIgnoreCase("OK")) {
			new Connection().closeConnection();
		}
	}
	

	@Override
	public void run() {
		intepretRespose(parser);
	}
}
