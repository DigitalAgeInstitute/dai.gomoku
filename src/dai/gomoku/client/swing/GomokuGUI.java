package dai.gomoku.client.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import dai.gomoku.client.swing.requests.ChallengeRequest;
import dai.gomoku.game.core.HumanPlayer;

public class GomokuGUI extends JFrame implements ActionListener {
	private List<GameModel> games;
	private Map<String, GameWindow> gameWindows;

	private JDesktopPane leftSideDesktop;
	private JPanel rightSidePanel;

	private JList<String> lstPlayerUsernames;
	private JButton btnChallenge;
	private JLabel lblStatusMessages;

	private ClientController controller;

	public GomokuGUI(ClientController controller, List<GameModel> games) {
		this.controller = controller;
		this.games = games;
		gameWindows = new HashMap<String, GameWindow>();
		initComponents();
		addComponents();
		setJFrameProperties();
	}

	public void populateList(List<HumanPlayer> players) {
		lstPlayerUsernames.removeAll();
		lstPlayerUsernames.setModel(new DefaultListModel<String>());
		String[] usernames = new String[players.size()];
		for (int i = 0; i < players.size(); i++) {
			usernames[i] = players.get(i).getUserName();
		}
		lstPlayerUsernames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstPlayerUsernames.setListData(usernames);
	}

	public GameWindow createGameWindow(String gameID, String opponentUsername,
			GameModel model) {
		GameWindow gameWindow = new GameWindow(controller, gameID, opponentUsername, model);
		gameWindows.put(gameID, gameWindow);
		gameWindow.setMaximizable(true);
		gameWindow.setClosable(true);
		gameWindow.setIconifiable(true);
		gameWindow.setVisible(true);
		leftSideDesktop.add(gameWindow);
		return gameWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChallenge) {
			String challengee = lstPlayerUsernames.getSelectedValue();
			if (challengee != null) {
				ChallengeRequest req = new ChallengeRequest(controller);
				req.setChallengeeUsername(challengee);
				req.setChallengerUsername(controller.getUsername());
				req.setMessage("MAKE");
				controller.challengeUser(req);
			}
		}

	}

	private void initComponents() {
		initLeftSideDesktop();
		initRightSidePanel();
	}

	private void addComponents() {
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		addLeftSideDesktop(gbc);
		addRightSidePanel(gbc);
	}

	private void setJFrameProperties() {
		this.setSize(900, 700);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initLeftSideDesktop() {
		leftSideDesktop = new JDesktopPane();
		leftSideDesktop.setVisible(true);
	}

	private void initRightSidePanel() {
		rightSidePanel = new JPanel(new GridBagLayout());
		rightSidePanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));
		initLstPlayerUsernames();
		initBtnChallenge();
		initLblStatusMessages();
		addComponentsToRightSidePanel();
	}

	private void addLeftSideDesktop(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.85;
		gbc.weighty = 1.0;
		this.getContentPane().add(leftSideDesktop, gbc);
	}

	private void addRightSidePanel(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.15;
		gbc.weighty = 1.0;
		this.getContentPane().add(rightSidePanel, gbc);
	}

	private void addComponentsToRightSidePanel() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		addLstPlayerUsernames(gbc);
		addBtnChallenge(gbc);
		addLblStatusMessages(gbc);
	}

	private void initLstPlayerUsernames() {
		lstPlayerUsernames = new JList<String>();
		lstPlayerUsernames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstPlayerUsernames.setListData(new String[] {});
	}

	private void initBtnChallenge() {
		btnChallenge = new JButton("Challenge");
		btnChallenge.addActionListener(this);
	}

	private void initLblStatusMessages() {
		lblStatusMessages = new JLabel();
		lblStatusMessages.setBorder(BorderFactory.createLineBorder(Color.BLACK,
				1));
	}

	private void addLstPlayerUsernames(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.85;
		rightSidePanel.add(lstPlayerUsernames, gbc);
	}

	private void addBtnChallenge(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.00;
		rightSidePanel.add(btnChallenge, gbc);
	}

	private void addLblStatusMessages(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.15;
		rightSidePanel.add(lblStatusMessages, gbc);
	}

}
