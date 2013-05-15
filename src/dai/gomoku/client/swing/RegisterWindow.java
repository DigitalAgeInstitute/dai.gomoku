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

public class RegisterWindow extends JFrame implements ActionListener {
	private final int DEFAULT_TEXT_FIELD_LENGTH = 20;
	private List<RegisterListener> registerListeners;
	
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

	public RegisterWindow() {
		this.registerListeners = new ArrayList<RegisterListener>();
		initComponents();
		addComponents();
		initJFrameProperties();
	}
	
	public void addRegisterListener ( RegisterListener listener ) {
		registerListeners.add(listener);
	}
	
	public void removeRegisterListener ( RegisterListener listener ) {
		if ( registerListeners.contains(listener) ) {
			registerListeners.remove(listener);
		}
	}
	
	public String getFirstName() {
		return txtFirstName.getText();
	}
	
	public String getLastName ( ) {
		return txtLastName.getText();
	}
	
	public String getEmail ( ) {
		return txtEmail.getText();
	}
	
	public String getPhone ( ) {
		return txtPhone.getText();
	}
	
	public String getPassword ( ) {
		char[] passChar = pswdPassword.getPassword();
		String passString = "";
		for ( int i = 0; i < passChar.length; i++ ) {
			passString += passChar[i];
		}
		return passString;
	}
	
	public String getConfirmPassword ( ) {
		char[] passChar = pswdConfirmPassword.getPassword();
		String passString = "";
		for ( int i = 0; i < passChar.length; i++ ) {
			passString += passChar[i];
		}
		return passString;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == btnRegister ) {
			notifyRegisterListeners();
		} else if ( e.getSource() == btnCancel ) {
			controller.displayLoginScreen();
			controller.hideLoginScreen();
		}
	}
	
	private void notifyRegisterListeners() {
		for ( int i = 0; i < registerListeners.size(); i++ ) {
			registerListeners.get(i).register();
		}
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
		lblPhone =  new JLabel("Phone");
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
		
		gbc.fill = GridBagConstraints.NONE;
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
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.weightx = 1.0;
		getContentPane().add(pswdConfirmPassword, gbc);
	}
	
	private void initJFrameProperties() {
		pack();
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
