package dai.gomoku.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	private Socket socket;
	private InputStream inputFromFile;
	private OutputStream outputToServer;

	public ClientTest(Socket socket) throws IOException {
		this.socket = socket;
		initStreams();
	}

	public String readFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputFromFile));
		String content = "", input = "";
		while ((input = reader.readLine()) != null) {
			content += input;
		}
		return content;
	}

	public void sendToServer(String content) {
		PrintWriter writer = new PrintWriter(outputToServer);
		writer.write(content);
		writer.flush();
	}

	private void initStreams() throws IOException {
		initInputStream();
		initOutputStream();
	}

	private void initInputStream() throws FileNotFoundException {
		inputFromFile = new FileInputStream(new File("test.xml"));
	}

	private void initOutputStream() throws IOException {
		outputToServer = socket.getOutputStream();
	}

	public void cleanup() throws IOException {
		releaseStreams();
		closeSocket();
	}

	private void releaseStreams() throws IOException {
		if (inputFromFile != null) {
			inputFromFile.close();
		}
		if (outputToServer != null) {
			outputToServer.close();
		}
	}

	private void closeSocket() throws IOException {
		if (socket != null) {
			socket.close();
		}
	}

	public static void main(String[] args) {
		Socket sock;
		try {
			sock = new Socket("localhost", 4010);
			ClientTest test = new ClientTest(sock);
			test.sendToServer(test.readFromFile());
			test.cleanup();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}