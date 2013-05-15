package dai.gomoku.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import dai.gomoku.game.core.GameWinListener;
import dai.gomoku.game.core.GomokuGame;
import dai.gomoku.game.core.Player;
import dai.gomoku.server.requests.RequestFactory;
import dai.gomoku.server.requests.RequestWrapper;
import dai.gomoku.server.responses.GameOverResponse;
import dai.gomoku.server.responses.ResponseUtil;

public class JSONRequestHandler implements Runnable, GameWinListener {
	private Socket clientSocket;
	private InputStream inputFromClient;
	private OutputStream outputToClient;

	private Map<String, GomokuGame> games;

	private boolean stopped = false;

	public JSONRequestHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		games = new HashMap<String, GomokuGame>();
		initIO();
	}

	/**
	 * @return the clientSocket
	 */
	public Socket getClientSocket() {
		return clientSocket;
	}

	/**
	 * @param clientSocket
	 *            the clientSocket to set
	 */
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void addGame(String gameID, GomokuGame game) {
		this.games.put(gameID, game);
	}

	public void removeGame(String gameID) {
		if (this.games.keySet().contains(gameID)) {
			this.games.remove(gameID);
		}
	}

	public GomokuGame getGameByID(String gameID) {
		return this.games.get(gameID);
	}

	@Override
	public void gameIsOver(GomokuGame game) {
		Player winner = game.getWinner();
		Player loser = game.getLoser();
		GameOverResponse response = new GameOverResponse(game.getGameID(),
				winner.getUserName());
		response.respond(ResponseUtil.getPlayerSocket(winner.getUserName()));
		response.respond(ResponseUtil.getPlayerSocket(loser.getUserName()));
	}

	public void stop() {
		this.stopped = true;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputFromClient));
		String inputLine;

		try {
			while ((inputLine = reader.readLine()) != null) {
				if (stopped) {
					break;
				}
				if (inputLine.equals("[STARTJSON]")) {
					String completeInput = "";
					while ((inputLine = reader.readLine()) != null) {
						if (inputLine.equals("[ENDJSON]")) {
							break;
						} else {
							completeInput += inputLine;
						}

						System.out.println("RECEIVED: " + completeInput);
						// TODO: Parse the JSON
						Gson gson = new Gson();
						RequestWrapper wrapper = gson.fromJson(completeInput,
								RequestWrapper.class);
						System.out.println(wrapper);

						// TODO: Create appropriate Request object
						Request request = RequestFactory
								.buildRequestFromWrapper(wrapper, this);
						System.out.println("REQUEST ==> " + request);
						// TODO: Process the request to get a response
						Response response = request.process();
						System.out.println("RESPONSE ==> " + response);

						// TODO: (synchronized)Send the response to the
						// client
						response.respond(clientSocket);
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		releaseResources();
		System.out.println("Client Handler died... We shall miss her.");
	}

	private void initIO() throws IOException {
		inputFromClient = clientSocket.getInputStream();
		outputToClient = clientSocket.getOutputStream();
	}

	private void releaseResources() {
		try {
			closeIO();
			closeSocket();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void closeIO() throws IOException {
		if (inputFromClient != null) {
			inputFromClient.close();
		}
		if (outputToClient != null) {
			outputToClient.close();
		}
	}

	private void closeSocket() throws IOException {
		if (this.clientSocket != null) {
			clientSocket.close();
		}
	}

}
