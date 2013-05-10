/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.gomoku.client.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Muaad
 */
public class GomokuGUI extends javax.swing.JFrame {


	JButton[][] buttonArray = new JButton[16][16];
	int count = 0;
	VirtualBoard vb = new VirtualBoard();
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
        createFrame();
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
        usersTable = new javax.swing.JTable();
        challengeButton = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
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
				userTxtKeyTyped(e);
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

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane2.setViewportView(usersList);

        challengeButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        challengeButton.setText("Challenge");
        challengeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.title = "Game VS "+usersList.getSelectedValue().toString(); 
				createFrame();
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

        jMenu3.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
    }   
    
    final char [] alphaNumeric = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
    		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
    		'/','"','!','@','#','$','%','*','&','^','(',')','`','~',',','.',
    		'0','1','2','3','4','5','6','7','8','9',' '};
    
    private void userTxtKeyTyped(KeyEvent e) {
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
    
    protected void createFrame() {
		GameWindow frame = new GameWindow();
		frame.setVisible(true);
		desktop.add(frame);
		statusLabel = new JLabel("This is a JLabel for error and status messages");
		statusLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        statusLabel.setBounds(10, 600, 610, 30);
        statusLabel.setHorizontalAlignment(JLabel.TRAILING);
        desktop.add(statusLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        reconnectButton = new JButton("Reconnect");
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
				reconnectButton.setText("<html><body style = \"text-decoration:none;\">Reconnect</body></html>");
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				reconnectButton.setText("<html><body style = \"text-decoration:underline;\">Reconnect</body></html>");
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
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
	}
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GomokuGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton challengeButton, reconnectButton;
    JLabel statusLabel;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JInternalFrame internalFrame;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JTable usersTable;
    // End of variables declaration
}
