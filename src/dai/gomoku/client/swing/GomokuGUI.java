
package dai.gomoku.client.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author Muaad
 */
public class GomokuGUI extends javax.swing.JFrame {

	int count = 0;
	VirtualBoard vb = new VirtualBoard();
	GameWindow window = new GameWindow();
	List<Integer> xList;
	List<Integer> oList;
	List<Object> names;
	HashSet<List<Integer>> xxSet = new HashSet<List<Integer>>();
	HashSet<List<Integer>> ooSet = new HashSet<List<Integer>>();
	JList usersList;
	Object players[] = {"mohaa","muaad","abdikarim","john","tom","alim","taj","hudy","moses","abdisalam","caliph",
			"mohaa1","muaad1","abdikarim1","john1","tom1","alim1","taj1","hudy1","moses1","abdisalam1","caliph1",
			"mohaa2","muaad2","abdikarim2","john2","tom2","alim2","taj2","hudy2","moses2","abdisalam2","caliph2"};
	
    public GomokuGUI() {
        initComponents();
        setTitle("Gomoku");
        position(this);
        //createFrame();
    }

    
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        desktop = new javax.swing.JDesktopPane();
        internalFrame = new javax.swing.JInternalFrame();
        boardPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        searchTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        challengeButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        usersList = new JList();

        names = Arrays.asList(players);
        usersList.setListData(players);
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktop.setBackground(new java.awt.Color(240, 240, 240));
        desktop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        internalFrame.setPreferredSize(new java.awt.Dimension(650, 650));
        internalFrame.setVisible(true);

        boardPanel.setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(internalFrame.getContentPane());
        internalFrame.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        internalFrame.setBounds(0, 0, 630, 650);
        //desktop.add(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        searchTxt.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        searchTxt.setForeground(new java.awt.Color(102, 102, 102));
        searchTxt.setText("Search users . . . ");
        searchTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTxtFocusLost(evt);
            }
        });
        searchTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				searchTxtKeyTyped(e);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        jScrollPane2.setViewportView(usersList);

        challengeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        challengeButton.setText("Challenge");
        challengeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createFrame("Game VS " + usersList.getSelectedValue().toString());
			}
		});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(challengeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menu.setText("File");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        menu.add(newMenuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                           
        window.buttonArray[0][1].setBackground(Color.black);
    }   
    
    final char [] alphaNumeric = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
    		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    		'/','"','!','@','#','$','%','*','&','^','(',')','`','~',',','.',
    		'0','1','2','3','4','5','6','7','8','9',' '};
    
    private void searchTxtKeyTyped(KeyEvent e) {
		StringBuffer search = new StringBuffer();
		List<String> l;
		for(int i = 0; i < alphaNumeric.length; i++) {
			if(e.getKeyChar() == alphaNumeric[i]) {	
				search.append(e.getKeyChar());
				l = new ArrayList<String>();
				for(Object x : names) {
					Utilities.containsIgnoreCase(x.toString(), search.toString(), l);
				}
				players = l.toArray();
				usersList.setListData(players);
			}
		}
		if(((int)e.getKeyChar() == 8)) {
			System.out.println(search.toString());
			if(search.length() > 0) {
				search.deleteCharAt(search.length() - 1);
				l = new ArrayList<String>();
				for(Object x : names) {
					Utilities.containsIgnoreCase(x.toString(), search.toString(), l);
				}
				players = l.toArray();
				usersList.setListData(players);
				if(search.length() == 0) {
					usersList.setListData(players);
				}
			}
			else {
				usersList.setListData(players);	
			}
		        
		}
			
	}

    private void searchTxtFocusLost(java.awt.event.FocusEvent evt) {
        if(searchTxt.getText().equals("") || searchTxt.getText().equals("Search users . . . ")) {
            searchTxt.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
            searchTxt.setForeground(new java.awt.Color(102, 102, 102));
            searchTxt.setText("Search users . . . ");
        }
    }

    private void searchTxtFocusGained(java.awt.event.FocusEvent evt) {
        if(searchTxt.getText().equals("Search users . . . ")) {
            searchTxt.setText("");
            searchTxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            searchTxt.setForeground(Color.BLACK);
        }
    }
    /**
    *
    * Creates a game window for each game
    */
    
    protected void createFrame(String title) {
		GameWindow frame = new GameWindow();
		frame.setTitle(title);
		frame.setVisible(true);
		desktop.add(frame);
		
		statusLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        statusLabel.setBounds(10, 600, 610, 30);
        statusLabel.setHorizontalAlignment(JLabel.TRAILING);
        if (openFrameCount == 1) {
			desktop.add(statusLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
			reconnectButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					reconnectButton
							.setText("<html><body style = \"text-decoration:none;\">Reconnect</body></html>");
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					reconnectButton
							.setText("<html><body style = \"text-decoration:underline;\">Reconnect</body></html>");
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			reconnectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			reconnectButton.setForeground(Color.BLUE);
			reconnectButton.setBorderPainted(false);
			reconnectButton.setContentAreaFilled(false);
			reconnectButton.setBounds(620, 600, 140, 30);
			desktop.add(reconnectButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
		}
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
	}

/**
 *
 * Sets the position of the window
 */
    public static void position( Window window ) {

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 5;
        // Move the window
        window.setLocation(x, 0);
    }
    
    int openFrameCount = 0;
    class GameWindow extends JInternalFrame {
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

    	public GameWindow() {
    		super();
    		setResizable(true);
    		setIconifiable(true);
    		setMaximizable(false);
    		setClosable(true);
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

/**
 *
 * Create the game board
 */
    	String separator = System.getProperty("file.separator");
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
    							//requests.sendRequests(requests.generateMoveRequest("12hjg265", "x", a, b));
    							oList = new ArrayList<Integer>();
    							oList.add(a);
    							oList.add(b);
    							if(vb.occupiedCells.add(oList)) {
    								System.out.println("dai"+separator+"gomoku"+separator+"client"+separator+"swing"+separator+"o.PNG");
    								((JButton) e.getSource()).setIcon(new ImageIcon("images"+separator+"o.PNG"));
    								vb.board[a][b] = 'O';
    								if(winner.hasSomeOneWon(vb.board) == 'O') {
    									statusLabel.setText("O is the winner!");
    								}
    								for(String s : hints.generateHint(vb.board, 'O')) {
    									buttonArray[Integer.parseInt(s.split(",")[0])][Integer.parseInt(s.split(",")[1])].setBackground(Color.RED);
    								}
    							}
    							else {
    								count--;
    							}
    						}
    						else {	
    							//requests.sendRequests(requests.generateMoveRequest("12hjg265", "o", a, b));
    							xList = new ArrayList<Integer>();
    							xList.add(a);
    							xList.add(b);
    							if(vb.occupiedCells.add(xList)) {
    								((JButton) e.getSource()).setIcon(new ImageIcon("images"+separator+"x.PNG"));
    								vb.board[a][b] = 'X';
    								if(winner.hasSomeOneWon(vb.board) == 'X') {
    									statusLabel.setText("X is the winner!");
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

/**
 *
 * This is to capture the event of a game being abandoned by a player
 */
    	private void gameWindowClosing(InternalFrameEvent evt) {
    		System.out.println("closed");
    	}
    }

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GomokuGUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton challengeButton, reconnectButton = new JButton("Reconnect");
    JLabel statusLabel = new JLabel("This is a JLabel for error and status messages");
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JInternalFrame internalFrame;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchTxt;
    // End of variables declaration
}
