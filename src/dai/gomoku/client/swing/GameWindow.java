package dai.gomoku.client.swing;

import javax.swing.JInternalFrame;

/**
 * This class provides the play area for the the swing applications
 * 
 * @author Muriithi Frederick Muriuki
 *
 */
public class GameWindow extends JInternalFrame implements
		GameBoardChangeListener {
	private static final long serialVersionUID = 6028423532504626449L;
	private ClientController controller;
	private String gameID;
	private String opponentUsername;
	private GameModel model;
	private BoardPanel boardPanel;

	public GameWindow(ClientController controller, String gameID,
			String opponentUsername, GameModel model) {
		super();
		this.controller = controller;
		this.model = model;
		this.gameID = gameID;
		this.opponentUsername = opponentUsername;
		
		initAndAddBoardPanel();
		
		initGameWindow();
	}

	/**
	 * @return the gameID
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * @return the opponentUsername
	 */
	public String getOpponentUsername() {
		return opponentUsername;
	}

	@Override
	public void updateBoard() {
		boardPanel.updateBoard();
	}

	private void initGameWindow() {
		this.setTitle("Game vs. " + opponentUsername);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setResizable(true);
		this.setSize(500, 500);
	}
	
	private void initAndAddBoardPanel() {
		boardPanel = new BoardPanel(controller, model);
		this.getContentPane().add(boardPanel);
	}

}
