package dai.gomoku.game.core;

import java.net.Socket;

public class HumanPlayer implements Player {
	private String userID;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private int contacts;

	private Socket connectedOn;

	public HumanPlayer(String userID, String userName, String firstName,
			String lastName) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the playerID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the playerID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 
	 * @return the Socket by which the player is connected to the server on
	 */
	@Override
	public Socket getConnectedOn() {
		return connectedOn;
	}

	@Override
	/**
	 * @param connectedOn
	 *            the Socket to set, by which the player is connected on to the server
	 */
	public void setConnectedOn(Socket connectedOn) {
		this.connectedOn = connectedOn;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contacts
	 */
	public int getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	public void setContacts(int contacts) {
		this.contacts = contacts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((userID == null) ? 0 : userID.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HumanPlayer other = (HumanPlayer) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HumanPlayer [playerID=" + userID + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
