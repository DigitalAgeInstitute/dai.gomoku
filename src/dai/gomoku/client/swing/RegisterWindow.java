package dai.gomoku.client.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import dai.gomoku.client.swing.requests.RegisterRequest;

public class RegisterWindow extends JFrame implements ActionListener {
	private final int DEFAULT_TEXT_FIELD_LENGTH = 20;

	private JLabel lblFirstName;
	private JTextField txtFirstName;

	private JLabel lblLastName;
	private JTextField txtLastName;

	private JLabel lblEmail;
	private JTextField txtEmail;

	private JLabel lblPhone;
	private JTextField txtPhone;

	private JLabel lblUsername;
	private JTextField txtUserName;

	private JLabel lblPassword;
	private JPasswordField pswdPassword;

	private JLabel lblConfirmPassword;
	private JPasswordField pswdConfirmPassword;

	private JButton btnRegister;
	private JButton btnCancel;

	private ClientController controller;

	public RegisterWindow( ClientController controller ) {
		this.controller = controller;
		initComponents();
		addComponents();
		initJFrameProperties();
	}

	public String getFirstName() {
		return txtFirstName.getText();
	}

	public String getLastName() {
		return txtLastName.getText();
	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getPhone() {
		return txtPhone.getText();
	}

	public String getPassword() {
		char[] passChar = pswdPassword.getPassword();
		String passString = "";
		for (int i = 0; i < passChar.length; i++) {
			passString += passChar[i];
		}
		return passString;
	}

	public String getConfirmPassword() {
		char[] passChar = pswdConfirmPassword.getPassword();
		String passString = "";
		for (int i = 0; i < passChar.length; i++) {
			passString += passChar[i];
		}
		return passString;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegister) {
			if (isInputComplete()) {
				RegisterRequest regReq = new RegisterRequest(controller);
				regReq.setFirstName(txtFirstName.getText());
				regReq.setLastName(txtLastName.getName());
				regReq.setEmail(txtEmail.getText());
				regReq.setPhone(txtPhone.getText());
				String password = "";
				char[] passChar = pswdPassword.getPassword();
				for (int i = 0; i < passChar.length; i++) {
					password += passChar[i];
				}
				passChar = null;
				regReq.setPassword(password);
			}
		} else if (e.getSource() == btnCancel) {
			controller.displayLoginScreen();
			controller.hideRegisterScreen();
		}
	}

	private boolean isInputComplete() {
		if (checkFirstName()) {
			if (checkLastName()) {
				if (checkEmail()) {
					if (checkPhone()) {
						if (checkUsername()) {
							if (checkPassword()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean checkFirstName() {
		String fname = txtFirstName.getText();
		if ((fname == null) || (fname.equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkLastName() {
		String lname = txtLastName.getText();
		if ((lname == null) || (lname.equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkEmail() {
		String email = txtEmail.getText();
		if ((email == null) || (email.equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkPhone() {
		String phone = txtFirstName.getText();
		if ((phone == null) || (phone.equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkUsername() {
		String username = txtUserName.getText();
		if ((username == null) || (username.equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkPassword() {
		String pass = "";
		char[] passChar = pswdPassword.getPassword();
		for (int i = 0; i < passChar.length; i++) {
			pass += passChar[i];
		}
		passChar = null;

		if ((pass == null) || (pass.equals(""))) {
			displayInputErrorDialog("Please fill in all fields");
			indicateComponentWithError(pswdPassword);
			return false;
		} else {
			String confPass = "";
			passChar = pswdConfirmPassword.getPassword();
			for (int i = 0; i < passChar.length; i++) {
				confPass += passChar[i];
			}
			passChar = null;

			if (confPass.equals(pass)) {
				return true;
			} else {
				displayInputErrorDialog("The passwords you provided do not match.");
				indicateComponentWithError(pswdConfirmPassword);
				return false;
			}
		}
	}

	private void displayInputErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Input Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private void indicateComponentWithError(JTextComponent component) {
		component.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		component.grabFocus();
	}

	private void initComponents() {
		initFirstNameComponents();
		initLastNameComponents();
		initEmailComponents();
		initPhoneComponents();
		initUsernameComponents();
		initPasswordComponents();
		initRegisterButton();
		initCancelButton();
	}

	private void addComponents() {
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		addFirstNameComponents(gbc);
		addLastNameComponents(gbc);
		addEmailComponents(gbc);
		addPhoneComponents(gbc);
		addUsernameComponents(gbc);
		addPasswordComponents(gbc);
		addRegisterButton(gbc);
		addCancelButton(gbc);
	}

	private void initFirstNameComponents() {
		txtFirstName = new JTextField(DEFAULT_TEXT_FIELD_LENGTH);
		lblFirstName = new JLabel("First Name");
		lblFirstName.setLabelFor(txtFirstName);
	}

	private void initLastNameComponents() {
		txtLastName = new JTextField(DEFAULT_TEXT_FIELD_LENGTH);
		lblLastName = new JLabel("Last Name");
		lblLastName.setLabelFor(txtLastName);
	}

	private void initEmailComponents() {
		txtEmail = new JTextField(DEFAULT_TEXT_FIELD_LENGTH);
		lblEmail = new JLabel("Email");
		lblEmail.setLabelFor(txtEmail);
	}

	private void initPhoneComponents() {
		txtPhone = new JTextField(DEFAULT_TEXT_FIELD_LENGTH);
		lblPhone = new JLabel("Phone");
		lblPhone.setLabelFor(txtPhone);
	}

	private void initUsernameComponents() {
		txtUserName = new JTextField(DEFAULT_TEXT_FIELD_LENGTH);
		lblUsername = new JLabel("Username");
		lblUsername.setLabelFor(txtUserName);
	}

	private void initPasswordComponents() {
		pswdPassword = new JPasswordField();
		pswdConfirmPassword = new JPasswordField();
		lblPassword = new JLabel("Password");
		lblPassword.setLabelFor(pswdPassword);
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setLabelFor(pswdConfirmPassword);
	}

	private void initRegisterButton() {
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		btnRegister.setEnabled(false);
	}

	private void initCancelButton() {
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
	}

	private void addFirstNameComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getContentPane().add(lblFirstName, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		getContentPane().add(txtFirstName, gbc);
	}

	private void addLastNameComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		getContentPane().add(lblLastName, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		getContentPane().add(txtLastName, gbc);
	}

	private void addEmailComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		getContentPane().add(lblEmail, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		getContentPane().add(txtEmail, gbc);
	}

	private void addPhoneComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		getContentPane().add(lblPhone, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		getContentPane().add(txtPhone, gbc);
	}

	private void addUsernameComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		getContentPane().add(lblUsername, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 1.0;
		getContentPane().add(txtUserName, gbc);
	}

	private void addPasswordComponents(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		getContentPane().add(lblPassword, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		getContentPane().add(pswdPassword, gbc);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		getContentPane().add(lblConfirmPassword, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 1.0;
		getContentPane().add(pswdConfirmPassword, gbc);
	}

	private void addRegisterButton(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 0;
		gbc.gridy = 7;
		getContentPane().add(btnRegister, gbc);
	}

	private void addCancelButton(GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 7;
		getContentPane().add(btnCancel, gbc);
	}

	private void initJFrameProperties() {
		pack();
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
