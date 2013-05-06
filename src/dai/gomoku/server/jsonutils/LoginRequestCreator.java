package dai.gomoku.server.jsonutils;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

import dai.gomoku.server.requests.LoginRequest;

public class LoginRequestCreator implements InstanceCreator<LoginRequest> {

	@Override
	public LoginRequest createInstance(Type arg0) {
		return new LoginRequest("username", "password");
	}

}
