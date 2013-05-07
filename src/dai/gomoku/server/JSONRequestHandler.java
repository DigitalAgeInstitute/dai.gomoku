package dai.gomoku.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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
		String completeInput = "";
		try {
			while ( (inputLine = reader.readLine()) != null ) {
				completeInput += inputLine;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// TODO: Parse the JSON
		System.err.println("Trying to parse");

		
		Gson gson = new Gson();
		RequestWrapper wrapper = gson.fromJson(completeInput, RequestWrapper.class);
		Request request = RequestFactory.buildRequestFromWrapper(wrapper);
		System.out.println(request);

		// TODO: Create appropriate Request object
		// TODO: Process the request to get a response
		// TODO: (synchronized)Send the response to the client

	}

}
