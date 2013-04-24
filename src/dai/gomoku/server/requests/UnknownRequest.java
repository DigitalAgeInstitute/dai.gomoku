package dai.gomoku.server.requests;

import java.util.Properties;

import dai.gomoku.server.Request;
import dai.gomoku.server.Response;

public class UnknownRequest implements Request {
	private String type;
	private Properties otherProperties;
	
	public UnknownRequest ( ) {
		type = "UNKNOWN";
	}
	
	public UnknownRequest ( String type, Properties properties ) {
		this.type = type;
		this.otherProperties = properties;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the otherProperties
	 */
	public Properties getOtherProperties() {
		return otherProperties;
	}

	/**
	 * @param otherProperties the otherProperties to set
	 */
	public void setOtherProperties(Properties otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public Response process() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
