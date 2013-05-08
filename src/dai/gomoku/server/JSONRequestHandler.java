package dai.gomoku.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.google.gson.Gson;

import dai.gomoku.server.requests.RequestFactory;
import dai.gomoku.server.requests.RequestWrapper;

public class JSONRequestHandler implements Runnable {
	private InputStream inputFromClient;
	private OutputStream outputToClient;

	public JSONRequestHandler(InputStream inputFromClient,
			OutputStream outputToClient) {
		this.inputFromClient = inputFromClient;
		this.outputToClient = outputToClient;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputFromClient));
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
						RequestWrapper wrapper = gson.fromJson(
								completeInput, RequestWrapper.class);

						// TODO: Create appropriate Request object
						Request request = RequestFactory
								.buildRequestFromWrapper(wrapper);
						System.out.println("REQUEST ==> " + request);
						// TODO: Process the request to get a response
						Response response = request.process();
						System.out.println("RESPONSE ==> " + response);

						// TODO: (synchronized)Send the response to the
						// client
						// sendResponse( response );
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}

	private synchronized void sendResponse(Response response) {
		String responseJson = "\n[STARTJSON]\n" + response.toJSONString()  + "\n[ENDJSON]\n";
		PrintWriter writer = new PrintWriter(outputToClient);
		writer.write(responseJson);
	}

}
