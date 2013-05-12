package dai.gomoku.client.swing;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * @author Muaad
 */
public class GomokuLogin extends JFrame {

    /**
     * Creates new form GomokuLogin
     */
    public GomokuLogin() {
    	setTitle("Gomoku :: Login");
    	setResizable(false);
    	GUIUtilities.center(this);
        initComponents();
    }

    Requests requests = new Requests();
    
    private void initComponents() {

        panel = new JPanel();
        loginPanel = new JPanel();
        userLabel = new JLabel();
        userTxt = new JTextField();
        passTxt = new JTextField();
        passwordField = new JPasswordField();
        passLabel = new JLabel();
        loginButton = new JButton();
        cancelButton = new JButton();
        jLabel3 = new JLabel();
        signUpButton = new JButton();
        forgotDetailsButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panel.setLayout(new java.awt.CardLayout());

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        userLabel.setText("Username");

        passLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passLabel.setText("Password");

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Don't have an account?");

        signUpButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        signUpButton.setForeground(new java.awt.Color(0, 0, 255));
        signUpButton.setText("<html><body style = \"text-decoration:underline;\">Sign Up</body></html>");
        signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpButtonMouseExited(evt);
            }
        });
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
                setTitle("Gomoku :: Sign Up");
            }
        });

        forgotDetailsButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        forgotDetailsButton.setForeground(new java.awt.Color(0, 0, 255));
        forgotDetailsButton.setText("<html><body style = \"text-decoration:underline;\">I forgot my details</body></html>");
        forgotDetailsButton.setBorderPainted(false);
        forgotDetailsButton.setContentAreaFilled(false);
        forgotDetailsButton.setHorizontalAlignment(SwingConstants.RIGHT);
        forgotDetailsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotDetailsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotDetailsButtonMouseExited(evt);
            }
        });
        forgotDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotDetailsButtonActionPerformed(evt);
            }
        });

        GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(userTxt, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                    .addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(forgotDetailsButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(userTxt, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(userLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forgotDetailsButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
    
        panel.add(loginPanel, "login");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

	private void loginButtonActionPerformed(ActionEvent evt) {
		requests.user = userTxt.getText();
		requests.pass = new String(passwordField.getPassword());
		requests.sendRequests(requests.generateLoginRequest(requests.user, requests.pass));
		if (true) {
			this.dispose();
			GomokuGUI gomo = new GomokuGUI();
			//gomo.lblScore.setText("You are logged in as: "+ userTxt.getText());
		}
		else {
			JOptionPane.showMessageDialog(null, "Login Failed!!");
		}
	}
	
    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	new SignUpFrame().setVisible(true);
    	this.dispose();
    }

    private void forgotDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void signUpButtonMouseEntered(java.awt.event.MouseEvent evt) {
        
        signUpButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUpButton.setText("<html><body style = \"text-decoration:none;\">Sign Up</body></html>");
    }

    private void signUpButtonMouseExited(java.awt.event.MouseEvent evt) {
        signUpButton.setText("<html><body style = \"text-decoration:underline;\">Sign Up</body></html>");
    }

    private void forgotDetailsButtonMouseEntered(java.awt.event.MouseEvent evt) {
        forgotDetailsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotDetailsButton.setText("<html><body style = \"text-decoration:none;\">I forgot my details</body></html>");
    }

    private void forgotDetailsButtonMouseExited(java.awt.event.MouseEvent evt) {
        forgotDetailsButton.setText("<html><body style = \"text-decoration:underline;\">I forgot my details</body></html>");
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GomokuLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private JButton cancelButton;
    private JButton forgotDetailsButton;
    private JLabel jLabel3;
    private JButton loginButton;
    private JPanel loginPanel;
    private JPanel panel;
    private JLabel passLabel;
    JPasswordField passwordField;
    private JTextField passTxt;
    private JButton signUpButton;
    private JLabel userLabel;
    private JTextField userTxt;
    // End of variables declaration
}
