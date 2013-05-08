package dai.gomoku.server.requests;

import java.util.Properties;

import dai.gomoku.server.Request;

/**
 * Creates and builds {@link Request} objects as requested. This will help
 * localise the creation of new {@link Request} objects to this class, which
 * will help in reducing dependency issues among classes, and avoiding bugs that
 * could result from such dependencies.
 * 
 * As anyone with any amount of sense can tell, this class is going to change
 * many times, mostly with code additions, as new types of requests are needed.
 * With this in mind, I think eventually, this class might be retired,
 * especially once the Gson library implements a polymorphic deserializer,
 * probably in the next version.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class RequestFactory {
	/**
	 * Creates a new object that implements the {@link Request} interface from
	 * the passed in {@link RequestWrapper} object
	 * 
	 * @param wrapper
	 *            a {@link RequestWrapper} object to be used to create an object
	 *            of a class implementing the {@link Request} interface
	 * 
	 * @return an object that implements the {@link Request} interface
	 */
	public static Request buildRequestFromWrapper(RequestWrapper wrapper) {
		switch (wrapper.getType()) {
		case "LOGIN":
			return buildLoginRequestFromWrapper(wrapper);
		case "MAKEMOVE":
			return buildMakeMoveRequestFromWrapper(wrapper);
		default:
			return buildUnknownRequestFromWrapper(wrapper);
		}
	}

	/**
	 * Creates a new {@link LoginRequest} object from the passed in
	 * {@link RequestWrapper} object
	 * 
	 * @param wrapper
	 *            a {@link RequestWrapper} object to be used to create a
	 *            {@link LoginRequest} object
	 * 
	 * @return an object that implements the {@link Request} interface, in this
	 *         case, an object of the {@link LoginRequest} class
	 */
	public static Request buildLoginRequestFromWrapper(RequestWrapper wrapper) {
		return new LoginRequest(wrapper.getUsername(), wrapper.getPassword());
	}

	public static Request buildMakeMoveRequestFromWrapper(RequestWrapper wrapper) {
		return new MakeMoveRequest(wrapper.getGameID(), wrapper.getUsername(),
				wrapper.getRow(), wrapper.getCol());
	}

	/**
	 * Creates a new {@link UnknownRequest} object from the passed in
	 * {@link RequestWrapper} object
	 * 
	 * @param wrapper
	 *            a {@link RequestWrapper} object to be used to create an
	 *            {@link UnknownRequest} object
	 * 
	 * @return an object that implements the {@link Request} interface, in this
	 *         case, an object of the {@link LoginRequest} class
	 */
	public static Request buildUnknownRequestFromWrapper(RequestWrapper wrapper) {
		// TODO: Implement a method that creates a java.util.Properties object
		// and uses it to create an UnknownRequestObject
		return new UnknownRequest( wrapper.getType() );
	}

	/**
	 * Builds a {@link Request} object from a passed in {@link Properties}
	 * object
	 * 
	 * @param properties
	 * @return
	 */
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
				.toString());
		return unknown;
	}
}
