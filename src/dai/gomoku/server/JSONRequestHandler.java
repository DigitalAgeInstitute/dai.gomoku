package dai.gomoku.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dai.gomoku.server.jsonutils.LoginRequestCreator;
import dai.gomoku.server.requests.LoginRequest;

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

		/*JsonParser parser = new JsonParser();
		JsonElement jsonElement = parser.parse(reader);
		System.out.println(jsonElement.isJsonObject());
		System.out.println(jsonElement);
		System.err.println("Parse complete");*/
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(LoginRequest.class, new LoginRequestCreator());
		Gson parser = builder.create();
		Request request = parser.fromJson(completeInput, LoginRequest.class);
		System.out.println(request);

		// TODO: Create appropriate Request object
		// TODO: Process the request to get a response
		// TODO: (synchronized)Send the response to the client

	}

}
