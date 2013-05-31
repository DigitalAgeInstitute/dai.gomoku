package dai.gomoku.game.core;

import dai.gomoku.server.JSONRequestHandler;

public class HumanPlayer implements Player {
	private long userID;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	private JSONRequestHandler playerThread;

	public HumanPlayer(long userID, String userName, String firstName,
			String lastName) {
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the playerID
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the playerID to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * @return the playerThread
	 */
	public JSONRequestHandler getPlayerThread() {
		return playerThread;
	}

	/**
	 * @param playerThread
	 *            the playerThread to set
	 */
	public void setPlayerThread(JSONRequestHandler playerThread) {
		this.playerThread = playerThread;
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
	public String getPhone() {
		return phone;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	public void setContacts(String contacts) {
		this.phone = contacts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + (int) (userID ^ (userID >>> 32));
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (userID != other.userID)
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
		return "HumanPlayer [userID=" + userID + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", contacts=" + phone + "]";
	}

	@Override
	public String toJSONString() {
		return "{\"playerID\":\"" + userID + "\", \"userName\":\"" + userName
				+ "\", \"firstName\":\"" + firstName + "\", \"lastName\":\""
				+ lastName + "\"}";
	}

}
