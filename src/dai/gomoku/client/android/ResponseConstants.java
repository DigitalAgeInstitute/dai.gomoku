package dai.gomoku.client.android;

public class ResponseConstants {
	public static final int UNKNOWN = 0;
	public static final int LOGIN = 1;
	public static final int MAKEMOVE = 2;
	public static final int GAMEOVER = 3;
	public static final int CHALLENGE = 4;
	public static final int REGISTERUSER = 5;
	public static final int SENDPLAYER = 6;

	public static int convertType(String type) {
		if (type.equals("LOGIN")) {
			return LOGIN;
		} else if (type.equals("MAKEMOVE")) {
			return MAKEMOVE;
		} else if (type.equals("GAMEOVER")) {
			return GAMEOVER;
		} else if (type.equals("CHALLENGE")) {
			return CHALLENGE;
		} else if (type.equals("SENDPLAYER")) {
			return SENDPLAYER;
		} else if (type.equals("REGISTERUSER")) {
			return REGISTERUSER;
		} else {
			return UNKNOWN;
		}
	}
}
