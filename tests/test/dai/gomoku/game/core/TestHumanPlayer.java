package test.dai.gomoku.game.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dai.gomoku.game.core.HumanPlayer;
import dai.gomoku.game.core.Player;

public class TestHumanPlayer {
	private HumanPlayer hPlayer1;
	private HumanPlayer hPlayer2;
	
	@Before
	public void setup ( ) {
		hPlayer1 = new HumanPlayer(1, "theone", "The", "One");
		hPlayer2 = new HumanPlayer(2, "otherone", "The", "Other");
	}

	@Test
	public void testHumanPlayer() {
		HumanPlayer player = null;
		assertNull(player);
		player = new HumanPlayer(3, "testPlayer", "Test", "Player");
		assertNotNull(player);
	}

	@Test
	public void testGetPlayerID() {
		assertEquals("1", hPlayer1.getUserID());
		assertEquals("2", hPlayer2.getUserID());
	}

	@Test
	public void testSetPlayerID() {
		assertEquals("1", hPlayer1.getUserID());
		hPlayer1.setUserID(13);
		assertEquals("13", hPlayer1.getUserID());
	}

	@Test
	public void testGetUserName() {
		assertEquals("theone", hPlayer1.getUserName());
		assertEquals("otherone", hPlayer2.getUserName());
	}

	@Test
	public void testSetUserName() {
		assertEquals("otherone", hPlayer2.getUserName());
		hPlayer2.setUserName("someother");
		assertEquals("someother", hPlayer2.getUserName());
	}

	@Test
	public void testGetFirstName() {
		assertEquals("The", hPlayer1.getFirstName());
		assertEquals("The", hPlayer2.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		assertEquals("The", hPlayer1.getFirstName());
		hPlayer1.setFirstName("The Only");
		assertEquals("The Only", hPlayer1.getFirstName());
	}

	@Test
	public void testGetLastName() {
		assertEquals("One", hPlayer1.getLastName());
		assertEquals("Other", hPlayer2.getLastName());
	}

	@Test
	public void testSetLastName() {
		assertEquals("Other", hPlayer2.getLastName());
		hPlayer2.setLastName("Second");
		assertEquals("Second", hPlayer2.getLastName());
	}

	@Test
	public void testEqualsObject() {
		Player test1 = new HumanPlayer(1, "theone", "The", "One");
		Player test2 = new HumanPlayer(2, "otherone", "The", "Other");
		assertTrue(test1.equals(hPlayer1));
		assertTrue(test2.equals(hPlayer2));
		assertFalse(test1.equals(hPlayer2));
		assertFalse(test2.equals(hPlayer1));
		assertFalse(test1.equals(test2));
		assertFalse(hPlayer1.equals(hPlayer2));
	}

}
