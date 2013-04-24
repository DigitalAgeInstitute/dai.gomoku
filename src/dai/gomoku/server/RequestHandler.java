package dai.gomoku.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import dai.gomoku.server.requests.RequestFactory;

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
	
	private void releaseResources ( ) {
		try {
			releaseStreams();
			closeSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void releaseStreams ( ) throws IOException {
		if ( inputFromClient != null ) {
			inputFromClient.close();
		}
		if ( outputToClient != null ) {
			outputToClient.close();
		}
	}
	
	private void closeSocket ( ) throws IOException {
		if ( clientSocket != null ) {
			clientSocket.close();
		}
	}

	@Override
	public void run() {
		try {
			RequestParserHandler handler = new RequestParserHandler();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(inputFromClient, handler);
			Request request = RequestFactory.buildRequestFromProperties( handler.getRequestProperties() );
			Response response = request.process();
			
			PrintWriter writer = new PrintWriter( outputToClient );
			writer.write( response.getResponseXML() );
			writer.flush();
			writer.close();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		releaseResources();
	}

}
