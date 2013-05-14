package dai.gomoku.client.swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.google.gson.Gson;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.responses.ResponseFactory;
import dai.gomoku.client.swing.responses.ResponseWrapper;

public class ResponseHandler implements Runnable {
	private Socket socket;

	private ClientController controller;
	private InputStream inputFromServer;

	public ResponseHandler(Socket socket, ClientController controller) {
		this.socket = socket;
		this.controller = controller;
		initIOStreams();
	}

	private void initIOStreams() {
		try {
			this.inputFromServer = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputFromServer));
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
						ResponseWrapper wrapper = gson.fromJson(completeInput,
								ResponseWrapper.class);
						Response response = ResponseFactory
								.buildResponseFromWrapper(wrapper, controller);
						response.process();
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