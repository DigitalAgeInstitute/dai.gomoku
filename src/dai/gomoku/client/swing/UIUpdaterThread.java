package dai.gomoku.client.swing;

import dai.gomoku.client.swing.responses.ResponseWrapper;



public class UIUpdaterThread implements Runnable {
	ResponseWrapper parser = new ResponseWrapper();
	static boolean loginStatus = false, signUpStatus = false;
	static String move = "";
	public static void intepretRespose(ResponseWrapper parser) {
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

	public static boolean loginUser(ResponseWrapper parser) {
		return parser.getStatus().equalsIgnoreCase("OK");
	}
	
	public static String makeMove(ResponseWrapper parser) {
		if(parser.getStatus().equalsIgnoreCase("OK")) {
			move = parser.getUsername() + ":" + parser.getRow() + " , " + parser.getCol();
		}
		return move;
	}
	
	public static boolean signUpUser(ResponseWrapper parser) {
		return parser.getStatus().equalsIgnoreCase("OK");
	}

	public static void logoutUser(ResponseWrapper parser) {
		if(parser.getStatus().equalsIgnoreCase("OK")) {
			new Connection().closeConnection();
		}
	}
	

	@Override
	public void run() {
		intepretRespose(parser);
	}
}