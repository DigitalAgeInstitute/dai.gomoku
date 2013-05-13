package dai.gomoku.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dai.gomoku.game.core.Player;
import dai.gomoku.server.responses.SendPlayerResponse;

/**
 * This is created when the first thread ever needs the list of {@link Player}
 * objects that correspond to players that are already online. It makes use of
 * the Singleton Design Pattern to ensure only one 'list' of available players
 * is present per server instance.
 * 
 * @author Muriithi Frederick Muriuki
 * 
 */
public class AvailablePlayersList {
	private Set<Player> availablePlayers;

	private volatile static AvailablePlayersList availablePlayersList;

	private AvailablePlayersList() {
		this.availablePlayers = new HashSet<Player>();
	}

	/**
	 * Returns an existing instance of {@link AvailablePlayersList} - If none
	 * exists, it creates one and returns it.
	 * 
	 * @return Returns an instance of {@link AvailablePlayersList}
	 */
	public static AvailablePlayersList getInstance() {
		// use double-checked locking to ensure that availablePlayersList is not
		// reinitialised - Will not work for java1.4 and earlier
		if (availablePlayersList == null) {
			synchronized ("") {
				if (availablePlayersList == null) {
					availablePlayersList = new AvailablePlayersList();
				}
			}
		}
		return availablePlayersList;
	}

	public synchronized void addPlayerToList(Player player) {
		availablePlayers.add(player);
		sendPlayers();
	}

	public void removePlayerFromList(Player player) {
		if (availablePlayers.contains(player)) {
			synchronized (player) {
				availablePlayers.remove(player);
			}
		}
	}

	/**
	 * Creates a new {@link ArrayList} of {@link Player} objects and returns the
	 * list. Adding to, or removing from the returned list does not affect the
	 * internal representation of the {@link AvailablePlayersList} object.
	 * 
	 * @return an {@link ArrayList} of {@link Player} objects
	 */
	public List<Player> getAvailablePlayersList() {
		return new ArrayList<Player>(availablePlayers);
	}
	
	public Player getPlayerByUsername ( String username ) {
		Player player = null;
		for ( Iterator<Player> it =  availablePlayers.iterator(); it.hasNext(); )
		{
			Player temp = it.next();
			if( temp.getUserName().equals(username) ) {
				player = temp;
			}
		}
		return player;
	}
	
	private void sendPlayers ( ) {
		for ( Iterator<Player> it = availablePlayers.iterator(); it.hasNext(); ) {
			new SendPlayerResponse().respond( it.next().getPlayerThread().getClientSocket() );
		}
	}

}
