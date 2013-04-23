package dai.gomoku.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class RequestHandler implements Runnable {
	private Socket clientSocket;
	private InputStream inputFromClient;
	private OutputStream outputToClient;
	
	public RequestHandler ( Socket socket ) throws IOException {
		this.clientSocket = socket;
		initStreams();
	}
	
	private void initStreams ( ) throws IOException {
		inputFromClient = clientSocket.getInputStream();
		outputToClient = clientSocket.getOutputStream();
	}

	@Override
	public void run() {
		try {
			RequestParserHandler handler = new RequestParserHandler();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(inputFromClient, handler);
			System.out.println( handler.getRequestProperties() );
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
