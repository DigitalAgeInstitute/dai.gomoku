package dai.gomoku.client.swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import dai.gomoku.client.Response;
import dai.gomoku.client.swing.responses.ResponseFactory;
import dai.gomoku.client.swing.responses.ResponseWrapper;

public class ResponseHandler implements Runnable {
	private InputStream inputFromServer;

	public ResponseHandler(InputStream inputFromServer) {
		this.inputFromServer = inputFromServer;
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
								.buildResponseFromWrapper(wrapper);
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
