package dai.gomoku.client.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

public class GameWindow extends JInternalFrame {
	//JInternalFrame internalFrame;

	static int openFrameCount = 0;
	static final int xOffset = 10, yOffset = 10;

	JPanel boardPanel;
	JButton[][] buttonArray = new JButton[15][15];
	int count = 0;
	VirtualBoard vb = new VirtualBoard();
	FindWinner winner = new FindWinner();
	Requests requests = new Requests();
	List<Integer> xList;
	List<Integer> oList;
	HashSet<List<Integer>> xxSet = new HashSet<List<Integer>>();
	HashSet<List<Integer>> ooSet = new HashSet<List<Integer>>();
	boolean dontWantActionListener = false;
	static String title = "Game VS ";

	public GameWindow() {
		super(title, true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
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
                internalFrameInternalFrameClosing(evt);
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
						count++;
						if (count % 2 == 0) {	
							requests.sendRequests(requests.generateMoveRequest("12hjg265", "x", a, b));
							oList = new ArrayList<Integer>();
							oList.add(a);
							oList.add(b);
							if(vb.occupiedCells.add(oList)) {
								((JButton) e.getSource()).setIcon(new ImageIcon("F:\\projects\\dai.gomoku\\src\\dai\\gomoku\\client\\swing\\o.PNG"));
								vb.board[a][b] = 'O';
								if(winner.hasSomeOneWon(vb.board) == 'O') {
									System.out.println("O is the winner!");
								}
							}
							else {
								count--;
							}
						}
						else {	
							requests.sendRequests(requests.generateMoveRequest("12hjg265", "o", a, b));
							xList = new ArrayList<Integer>();
							xList.add(a);
							xList.add(b);
							if(vb.occupiedCells.add(xList)) {
								((JButton) e.getSource()).setIcon(new ImageIcon("F:\\projects\\dai.gomoku\\src\\dai\\gomoku\\client\\swing\\x.PNG"));
								vb.board[a][b] = 'X';
								if(winner.hasSomeOneWon(vb.board) == 'X') {
									
								}
							}
							else {
								count--;
							}
						}
					}
				});
			boardPanel.add(buttonArray[x][i]);
			}
		}
	}
	private void internalFrameInternalFrameClosing(InternalFrameEvent evt) {
		System.out.println("closed");
	}
}
