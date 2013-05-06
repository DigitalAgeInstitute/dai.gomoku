package dai.gomoku.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import dai.gomoku.server.requests.RequestFactory;

/**
 * Created originally to handle requests sent in as XML. Since the request to
 * move to JSON, this class might be retired, but for now, it sticks around for
 * a little while.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class XMLRequestHandler implements Runnable {
	private InputStream inputFromClient;
	private OutputStream outputToClient;

	public XMLRequestHandler(InputStream inFromClient, OutputStream outToClient)
			throws IOException {
		inputFromClient = inFromClient;
		outputToClient = outToClient;
	}

	@Override
	public void run() {
		try {
			XMLRequestParserHandler handler = new XMLRequestParserHandler();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(inputFromClient, handler);
			Request request = RequestFactory.buildRequestFromProperties(handler
					.getRequestProperties());
			Response response = request.process();

			PrintWriter writer = new PrintWriter(outputToClient);
			writer.write(response.getResponseXML());
			writer.flush();
		} catch (ParserConfigurationException e) {
			Logger.getLogger(XMLRequestHandler.class.getName()).log(
					Level.SEVERE, e.getStackTrace().toString());
		} catch (SAXException e) {
			Logger.getLogger(XMLRequestHandler.class.getName()).log(
					Level.SEVERE, e.getStackTrace().toString());
		} catch (IOException e) {
			Logger.getLogger(XMLRequestHandler.class.getName()).log(
					Level.SEVERE, e.getStackTrace().toString());
		}
	}

}
