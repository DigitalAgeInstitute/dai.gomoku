package dai.gomoku.client.swing;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;
import dai.gomoku.server.requests.RequestFactory;
import dai.gomoku.server.requests.RequestWrapper;

public class Responses implements Runnable {
	private InputStream inputFromServer;
	public Responses(InputStream inputFromServer) {
		this.inputFromServer = inputFromServer;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputFromServer));
		String inputLine;
		try {
			while ((inputLine = reader.readLine()) != null) {
				if (inputLine.equals("[STARTJSON]")) {
					String completeInput = "";
					while ((inputLine = reader.readLine()) != null) {
						if (inputLine.equals("[ENDJSON]")) {
							break;
						} else {
							completeInput += inputLine;
						}
						// TODO: Parse the JSON
						Gson gson = new Gson();
						ResponseParser parseResponses = gson.fromJson(
								completeInput, ResponseParser.class);

					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}
}
