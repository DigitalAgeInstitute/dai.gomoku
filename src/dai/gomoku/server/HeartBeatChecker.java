package dai.gomoku.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dai.gomoku.game.core.Player;

/**
 * This is built to keep a check on the connected clients. If a client
 * disconnects, then it makes no sense to maintain the thread in the running
 * state.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class HeartBeatChecker implements Runnable {
	private boolean stopped;

	private Map<JSONRequestHandler, Integer> timeouts;

	private volatile static HeartBeatChecker checker;

	private HeartBeatChecker() {
		stopped = false;
		timeouts = new HashMap<JSONRequestHandler, Integer>();
	}

	public static HeartBeatChecker getInstance() {
		if (checker == null) {
			synchronized ("") {
				if (checker == null) {
					checker = new HeartBeatChecker();
				}
			}
		}
		return checker;
	}

	public synchronized void resetTimeout(JSONRequestHandler handler) {
		if (timeouts.containsKey(handler)) {
			timeouts.remove(handler);
		}
	}

	public void stop() {
		this.stopped = true;
	}

	@Override
	public void run() {
		while (!stopped) {
			List<Player> players = AvailablePlayersList.getInstance()
					.getAvailablePlayersList();
			for (Iterator<Player> it = players.iterator(); it.hasNext();) {
				JSONRequestHandler playerThread = it.next().getPlayerThread();
				Socket sock = playerThread.getClientSocket();
				try {
					PrintWriter writer = new PrintWriter(sock.getOutputStream());
					writer.write("\n[STARTJSON]\n"
							+ "{\"type\":\"HEARTBEAT\" }" + "\n[ENDJSON]\n");
					writer.flush();
				} catch (IOException e) {
					if (timeouts.containsKey(playerThread)) {
						int count = timeouts.get(playerThread);
						if (count >= 5) {
							playerThread.stop();
							it.remove();
						} else {
							++count;
							timeouts.put(playerThread, count);
						}
					} else {
						timeouts.put(playerThread, 1);
					}
				}
			}

			try {
				/* Sleep for 1 minute */
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
