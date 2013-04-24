package dai.gomoku.server.requests;

import java.util.Properties;

import dai.gomoku.server.Request;

/**
 * Creates and builds {@link Request} objects as requested. This will help
 * localise the creation of new {@link Request} objects to this class, which
 * will help in reducing dependency issues among classes, and avoiding bugs that
 * could result from such dependencies.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class RequestFactory {
	public static Request buildRequestFromProperties(Properties properties) {
		if (properties.get("type").equals("LOGIN")) {
			return buildLoginRequestFromProperties(properties);
		} else {
			return buildUnknownRequestFromProperties(properties);
		}
	}

	/**
	 * Builds a {@link LoginRequest} object from the given {@link Properties}
	 * object
	 * 
	 * @param properties
	 *            The {@link Properties} object from which to build a request
	 * 
	 * @return A new {@link LoginRequest} object created from the properties
	 */
	public static Request buildLoginRequestFromProperties(Properties properties) {
		return new LoginRequest(properties.get("username").toString(),
				properties.get("password").toString());
	}

	/**
	 * Builds an {@link UnknownRequest} object from the given {@link Properties}
	 * object
	 * 
	 * @param properties
	 *            The {@link Properties} object from which to build a request
	 * 
	 * @return A new {@link UnknownRequest} object created from the properties
	 */
	public static Request buildUnknownRequestFromProperties(
			Properties properties) {
		UnknownRequest unknown = new UnknownRequest(properties.get("type")
				.toString(), properties);
		return unknown;
	}
}
