package dai.gomoku.server;

import java.util.Properties;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class extends {@link DefaultHandler} and is used by
 * {@link javax.xml.parsers.SAXParser} to parse the XML sent from the client
 * into a {@link Properties} object which is then used to build relevant
 * {@link Request} objects
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class XMLRequestParserHandler extends DefaultHandler {
	private boolean atRootElement;
	private String currentKey;
	private Properties requestProperties;

	public XMLRequestParserHandler() {
		atRootElement = false;
		currentKey = "";
		requestProperties = new Properties();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (qName.equalsIgnoreCase("request")) {
			this.atRootElement = true;
			return;
		} else {
			this.atRootElement = false;
			this.currentKey = qName;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		if (atRootElement) {
			return;
		} else if (requestProperties.containsKey(currentKey)) {
			return;
		} else {
			requestProperties.put(currentKey, new String(ch, start, length));
		}
	}

	/**
	 * @return the requestProperties
	 */
	public Properties getRequestProperties() {
		return requestProperties;
	}

}
