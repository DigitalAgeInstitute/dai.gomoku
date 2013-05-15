package dai.gomoku.server.requests;

import dai.gomoku.server.JSONRequestHandler;
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
	public static Request buildRequestFromWrapper(RequestWrapper wrapper,
			JSONRequestHandler handler) {
		switch (wrapper.getType()) {
		case "LOGIN":
			return buildLoginRequestFromWrapper(wrapper, handler);
		case "MAKEMOVE":
			return buildMakeMoveRequestFromWrapper(wrapper);
		case "CHALLENGE":
			return buildChallengeRequestFromWrapper(wrapper);
		case "REGISTERUSER":
			return buildRegisterUserRequestRequestFromWrapper(wrapper);
		case "HEARTBEAT":
			return new HeartBeatRequest(handler);
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
	public static Request buildLoginRequestFromWrapper(RequestWrapper wrapper,
			JSONRequestHandler parent) {
		LoginRequest someRequest = new LoginRequest(wrapper.getUsername(),
				wrapper.getPassword(), parent);
		return someRequest;
	}

	public static Request buildMakeMoveRequestFromWrapper(RequestWrapper wrapper) {
		return new MakeMoveRequest(wrapper.getGameID(), wrapper.getUsername(),
				wrapper.getRow(), wrapper.getCol());
	}

	public static Request buildChallengeRequestFromWrapper(
			RequestWrapper wrapper) {
		return new ChallengeRequest(wrapper.getChallengerUsername(),
				wrapper.getChallengeeUsername(), wrapper.getMessage());
	}

	public static Request buildRegisterUserRequestRequestFromWrapper(
			RequestWrapper wrapper) {
		return new RegisterUserRequest(wrapper.getFirstName(),
				wrapper.getLastName(), wrapper.getEmail(), wrapper.getEmail(),
				wrapper.getPassword(), wrapper.getUsername());
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
		return new UnknownRequest(wrapper.getType());
	}

}
