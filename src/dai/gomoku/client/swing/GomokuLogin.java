package dai.gomoku.client.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dai.gomoku.client.swing.requests.LoginRequest;

public class GomokuLogin extends JFrame implements GameModelListener,
		ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4391853159413575379L;
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblPassword;
	private JPasswordField pswdPassword;

	private JButton btnSignIn;
	private JButton btnRegister;

	private LoginRequest loginReq;

	private ClientController controller;

	public GomokuLogin(ClientController controller) {
		this.controller = controller;
		initComponents();
		addComponents();
		setJFrameProperties();
	}

	public String getUsername() {
		return txtUserName.getText();
	}

	@Override
	public void update() {
		// TODO: Get details from the model
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSignIn) {
			if (isInputComplete()) {
				controller.hideLoginScreen();
				loginReq = new LoginRequest(controller, txtUserName.getText(),
						getPassword());
				controller.signIn( loginReq );
			}
		} else if (e.getSource() == btnRegister) {
			controller.hideLoginScreen();
			controller.displayRegisterScreen();
		}

	}

	private String getPassword() {
		String pass = "";
		char[] passChar = pswdPassword.getPassword();
		for (int i = 0; i < passChar.length; i++) {
			pass += passChar[i];
		}
		return pass;
	}

	private boolean isInputComplete() {
		if ((txtUserName.getText() != null)
				&& (!txtUserName.getText().equals(""))) {
			if (pswdPassword.getPassword().length > 0) {
				return true;
			}
		}
		return false;
	}

	private void initComponents() {
		initUsernameTextField();
		initUsernameLabel();
		initPasswordField();
		initPasswordLabel();
		initLoginButton();
		initRegisterButton();
	}

	private void addComponents() {
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		addUserNameLabel(gbc);
		addUserNameTextField(gbc);
		addPasswordLabel(gbc);
		addPasswordField(gbc);
		addSignUpButton(gbc);
		addRegisterButton(gbc);
	}

	private void setJFrameProperties() {
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initUsernameTextField() {
		txtUserName = new JTextField(20);
	}

	private void initUsernameLabel() {
		lblUserName = new JLabel("Username");
		lblUserName.setLabelFor(txtUserName);
	}

	private void initPasswordField() {
		pswdPassword = new JPasswordField();
	}

	private void initPasswordLabel() {
		lblPassword = new JLabel("Password");
		lblPassword.setLabelFor(pswdPassword);
	}

	private void initLoginButton() {
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(this);
	}

	private void initRegisterButton() {
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
	}

	private void addUserNameLabel(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.getContentPane().add(lblUserName, gbc);
	}

	private void addUserNameTextField(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		this.getContentPane().add(txtUserName, gbc);
	}

	private void addPasswordLabel(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.getContentPane().add(lblPassword, gbc);
	}

	private void addPasswordField(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		this.getContentPane().add(pswdPassword, gbc);
	}

	private void addSignUpButton(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.getContentPane().add(btnSignIn, gbc);
	}

	private void addRegisterButton(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.getContentPane().add(btnRegister, gbc);
	}

}
