package gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Represents the GUI page for modifying user information.
 * Allows users to change their email, age, and password.
 */
public class ModifyPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private String newEmail;
	private String newAge;
	private char[] currentPassword;
	private char[] newPassword;
	private UserInfoUpdateCallback callback;

	
	public ModifyPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("New Email: " );
        lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(19, 10, 98, 19);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("New Age: ");
        lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(19, 57, 78, 19);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Current Password: ");
        lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(19, 107, 137, 19);
        contentPane.add(lblNewLabel_6);

		setContentPane(contentPane);
		
		JLabel lblNewLabel_6_1 = new JLabel("New Password: ");
		lblNewLabel_6_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_6_1.setBounds(19, 157, 137, 19);
		contentPane.add(lblNewLabel_6_1);
		
		textField = new JTextField();
		textField.setBounds(111, 7, 224, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 54, 43, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 104, 167, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(147, 154, 188, 26);
		contentPane.add(passwordField_1);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                newEmail = textField.getText();
                newAge = textField_1.getText();
                currentPassword = passwordField.getPassword();
                newPassword = passwordField_1.getPassword();
                
                if(newAge.length() == 0 || newEmail.length() == 0 || newPassword.length == 0 || currentPassword.length == 0) {
                	if(newEmail.length() == 0) JOptionPane.showMessageDialog(ModifyPanel.this, "Please enter a e-mail address.");
                	if(newAge.length() == 0) JOptionPane.showMessageDialog(ModifyPanel.this, "Please enter a age.");
                	if(newPassword.length == 0 || currentPassword.length == 0) {
                		if(newPassword.length == 0 && currentPassword.length == 0) JOptionPane.showMessageDialog(ModifyPanel.this, "Please enter both password.");
                		else if (newPassword.length == 0) JOptionPane.showMessageDialog(ModifyPanel.this, "Please enter new password.");
                		else JOptionPane.showMessageDialog(ModifyPanel.this, "Please enter current password.");
                	}
                }
                else saveChanges(newEmail, newAge, currentPassword, newPassword);
            }
        });
		btnSaveChanges.setBounds(99, 195, 187, 29);
		contentPane.add(btnSaveChanges);
		
		JToggleButton toggleButton = new JToggleButton("Show");
		toggleButton.setBounds(330, 154, 61, 29);
		contentPane.add(toggleButton);
		
		toggleButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (toggleButton.isSelected()) {
		            passwordField_1.setEchoChar((char) 0);
		        } else {
		            passwordField_1.setEchoChar('*');
		        }
		    }
		});
		
		JToggleButton toggleButton1 = new JToggleButton("Show");
		toggleButton1.setBounds(330, 104, 61, 29);
		contentPane.add(toggleButton1);
		toggleButton1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (toggleButton1.isSelected()) {
		            passwordField.setEchoChar((char) 0);
		        } else {
		            passwordField.setEchoChar('*');
		        }
		    }
		});
	}
	
	/**
     * Sets the callback for updating user information.
     *
     * @param callback The callback to set.
     */
	public void setCallback(UserInfoUpdateCallback callback) {
        this.callback = callback;
    }


	/**
     * Saves the changes made by the user by invoking the callback.
     *
     * @param newEmail        The new email address.
     * @param newAge          The new age.
     * @param currentPassword The current password.
     * @param newPassword     The new password.
     */
    private void saveChanges(String newEmail, String newAge, char[] currentPassword, char[] newPassword) {
        if (callback != null) {
            callback.updateUserInformation(newEmail, newAge, new String(currentPassword), new String(newPassword));
        }
    }
    
    

}
