package dai.gomoku.client.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class GameWindow extends JInternalFrame {
	//JInternalFrame internalFrame;

	static int openFrameCount = 0;
	static final int xOffset = 10, yOffset = 10;

	JPanel boardPanel;
	JButton[][] buttonArray = new JButton[15][15];
	int count = 0;
	VirtualBoard vb = new VirtualBoard();
	FindWinner winner = new FindWinner();
	List<Integer> xList;
	List<Integer> oList;
	HashSet<List<Integer>> xxSet = new HashSet<List<Integer>>();
	HashSet<List<Integer>> ooSet = new HashSet<List<Integer>>();
	boolean dontWantActionListener = false;
	static String title = "Game VS ";
	String separator = System.getProperty("file.separator");
	Requests requests = null;
	ClientController controller;

	public GameWindow(ClientController controller) {
		super(title, true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		this.controller = controller;
		addInternalFrameListener(new InternalFrameListener() {
            public void internalFrameActivated(InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(InternalFrameEvent evt) {
            }
            public void internalFrameIconified(InternalFrameEvent evt) {
            }
            public void internalFrameClosed(InternalFrameEvent evt) {
            }
            public void internalFrameClosing(InternalFrameEvent evt) {
                gameWindowClosing(evt);
            }
            public void internalFrameOpened(InternalFrameEvent evt) {
            }
        });

		
		createBoardPanel();

		setPreferredSize(new Dimension(650, 650));

        boardPanel.setPreferredSize(new Dimension(500, 500));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        setBounds(0, 0, 610, 600);
        setVisible(true);
	
		setSize(610, 590);
        //setPreferredSize(new Dimension(650, 650));
		openFrameCount++;
		setLocation(xOffset * openFrameCount, yOffset);
		
	}
	HintsImplementation hints = new HintsImplementation();
	void createBoardPanel() {
		boardPanel = new JPanel(new GridLayout(15, 15));
		for (int x = 0; x < buttonArray.length; x++) {
			for (int i = 0; i < buttonArray[x].length; i++) {
				final int a = x, b = i;
				buttonArray[x][i] = new JButton();//x+", "+i
				if (x % 2 == 0 && i % 2 == 0 || x % 2 == 1 && i % 2 == 1) {
					buttonArray[x][i].setBackground(new Color(240,240,240));
				}
				else {
					buttonArray[x][i].setBackground(Color.white);
				}
				buttonArray[x][i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//requests.sendRequests(requests.generateMoveRequest(gameID, user, a, col));
						
						requests.row = a;
						requests.row = b;
						requests.sendRequests(requests.generateMoveRequest(
								requests.gameID, requests.user, requests.row, requests.col));
						
						
					}
				});
			boardPanel.add(buttonArray[x][i]);
			}
		}
	}
	
	public void markButton(int row, int col, char user) {
		if(user == 'X' || user == 'x') {
			buttonArray[row][col].setIcon(new ImageIcon("images"+separator+"x.PNG"));
		}
		else if(user == 'O' || user == 'o') {
			buttonArray[row][col].setIcon(new ImageIcon("images"+separator+"o.PNG"));
		} 
	}
	private void gameWindowClosing(InternalFrameEvent evt) {
		System.out.println("closed");
	}
}