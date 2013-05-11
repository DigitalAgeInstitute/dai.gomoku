package dai.gomoku.server;

import java.net.Socket;

public interface Response {
	void respond ( Socket socket );
	String toJSONString();
}
