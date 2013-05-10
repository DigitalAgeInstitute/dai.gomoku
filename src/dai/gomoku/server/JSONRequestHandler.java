package dai.gomoku.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import dai.gomoku.server.requests.RequestFactory;
import dai.gomoku.server.requests.RequestWrapper;

public class JSONRequestHandler implements Runnable {
	private Socket clientSocket;
	private InputStream inputFromClient;
	private OutputStream outputToClient;

	public JSONRequestHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
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
						RequestWrapper wrapper = gson.fromJson(completeInput,
								RequestWrapper.class);

						// TODO: Create appropriate Request object
						Request request = RequestFactory
								.buildRequestFromWrapper(wrapper, this);
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
		
		releaseResources();

	}

	private void sendResponse(Response response) {
		String responseJson = "\n[STARTJSON]\n" + response.toJSONString()
				+ "\n[ENDJSON]\n";
		PrintWriter writer = new PrintWriter(outputToClient);
		writer.write(responseJson);
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
