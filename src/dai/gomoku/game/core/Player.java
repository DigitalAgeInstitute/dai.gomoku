package dai.gomoku.game.core;

import dai.gomoku.server.JSONRequestHandler;

/**
 * This interface presents the common behaviours between of all types of
 * players.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public interface Player {
	/**
	 * 
	 * @return
	 */
	public String getUserName();

	/**
	 * 
	 * @return the Socket by which the player is connected to the server on
	 */
	public JSONRequestHandler getPlayerThread();

	/**
	 * 
	 * @param connectedOn
	 *            the Socket to set, by which the player is connected on to the
	 *            server
	 */
	public void setPlayerThread(JSONRequestHandler playerThread);

	/**
	 * 
	 * @return
	 */
	public String toJSONString();

	/**
	 * 
	 * Used to test for equality among {@link Player} objects
	 * 
	 * @param o
	 * @return
	 */
	boolean equals(Object o);

}
