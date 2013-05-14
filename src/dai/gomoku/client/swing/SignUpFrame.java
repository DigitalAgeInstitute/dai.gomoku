package dai.gomoku.client.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SignUpFrame extends JFrame implements ParentThread {
	
	public SignUpFrame() {
		createGUI();
		GUIUtilities.center(this);
		initRequests();
		connectToServer();
	}
	
	User user = new User();
	Requests requests = null;
	Socket socket;
	ClientController controller;
	
	@Override
    public Socket getSocket ( ) {
    	return socket;
    }

    private void initRequests ( ) {
    	try {
			requests = new Requests(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void connectToServer ( ) {
    	try {
			socket = new Connection().returnSocket("localhost", 4010);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    private void createGUI() {
    	topPanel = new JPanel();
        signUpPanel = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        verifyUsernameLabel = new javax.swing.JLabel();
        reTypePassLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        phoneTxt = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        checkPasswordMatchLabel = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        firstNameTxt = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTxt = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        userLabel.setText("Username");

        passLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passLabel.setText("Password");

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        loginButton.setForeground(new java.awt.Color(0, 0, 255));
        loginButton.setText("<html><body style = \"text-decoration:underline;\">Login if you already have an account.</body></html>");
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }
        });
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        verifyUsernameLabel.setForeground(new java.awt.Color(153, 153, 153));
        verifyUsernameLabel.setText("A - Z, 0 ~ 9 And must be unique");

        reTypePassLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reTypePassLabel.setText("Re-type Password");

        passwordField.setText("jPasswordField1");

        reTypePasswordField.setText("jPasswordField1");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phoneLabel.setText("Phone Number");

        signUpButton.setText("Sign Up");

        cancelButton.setText("Cancel");

        firstNameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        firstNameLabel.setText("First Name");

        lastNameLabel.setText("Last Name");

        emailLabel.setText("Email");
        
        signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(userTxt.getText() != null)
				if(new String(passwordField.getPassword()).equalsIgnoreCase(new String(reTypePasswordField.getPassword()))) {
					checkPasswordMatchLabel.setText("");
					User user = new User();
					user.setUserName(userTxt.getText());
					user.setPassword(new String(passwordField.getPassword()));
					user.setPhoneNumber(Integer.parseInt(phoneTxt.getText()));
					//db.insertUserDetails(user.getUserName(), user.getPassword(), user.getPhoneNumber());
					//userList.add(userTxt.getText());
					userTxt.setText("");
					passwordField.setText("");
					reTypePasswordField.setText("");
					phoneTxt.setText("");
					userTxt.requestFocus();
					try {
						requests.sendRequests(requests.generateSignUpRequest(firstNameTxt.getText(), 
								lastNameTxt.getText(), emailTxt.getText(), Integer.parseInt(phoneTxt.getName()),
								userTxt.getText(), new String(passwordField.getPassword())));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please provide the right data format");
					}
				}
				else {
					checkPasswordMatchLabel.setText("Passwords Don't Match");
					checkPasswordMatchLabel.setForeground(Color.RED);
				}
			}
		});

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phoneTxt))
                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(signUpPanelLayout.createSequentialGroup()
                                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(signUpButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton))
                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(verifyUsernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userTxt))
                                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reTypePasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(signUpPanelLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(201, 201, 201))
                                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                                .addGap(108, 108, 108)
                                                .addComponent(emailTxt))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                                        .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(signUpPanelLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addComponent(checkPasswordMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(reTypePassLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)))))
                        .addGap(2, 2, 2)))
                .addGap(117, 117, 117))
        );

        signUpPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {passwordField, reTypePasswordField, userTxt, verifyUsernameLabel});

        signUpPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {passLabel, phoneLabel, reTypePassLabel, userLabel});

        signUpPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {firstNameTxt, lastNameTxt});

        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verifyUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reTypePassLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPasswordMatchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        signUpPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {firstNameTxt, lastNameTxt});

        signUpPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {emailLabel, phoneLabel});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(31, Short.MAX_VALUE)))
        );
        getContentPane().add(topPanel);
        setVisible(true);
        pack();
    }// </editor-fold>

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.setText("<html><body style = \"text-decoration:none;\">Login if you already have an account</body></html>");
    }                                        

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        loginButton.setText("<html><body style = \"text-decoration:underline;\">Login if you already have an account</body></html>");
    }                                       

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //new GomokuLogin().setVisible(true);
        this.dispose();
    } 
    
    public static void main(String[] args) {
		new SignUpFrame();
	}

    // Variables declaration - do not modify
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel checkPasswordMatchLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JLabel reTypePassLabel;
    private javax.swing.JPasswordField reTypePasswordField;
    private javax.swing.JButton signUpButton;
    private javax.swing.JPanel signUpPanel, topPanel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTxt;
    private javax.swing.JLabel verifyUsernameLabel;
   
}