package dai.gomoku.game.core;

import java.net.Socket;

/**
 * This interface presents the common behaviours between of all types of players.
 * 
 * @author Muriithi Frederick Muriuki
 *
 */
public interface Player {
	/**
	 * 
	 * @return the Socket by which the player is connected to the server on
	 */
	public Socket getConnectedOn ( );
	
	/**
	 * 
	 * @param connectedOn
	 *            the Socket to set, by which the player is connected on to the server
	 */
	public void setConnectedOn ( Socket connectedOn );
	
	/**
	 * 
	 * Used to test for equality among {@link Player} objects
	 * @param o
	 * @return
	 */
	boolean equals( Object o );
	
}
