/* *********** Pledge of Honor ************************************************
*
* I hereby certify that I have completed this lab assignment on my own
* without any help from anyone else. I understand that the only sources of authorized
* information in this lab assignment are (1) the course textbook, (2) the
* materials  posted at the course website and (3) any study notes handwritten by myself.
*
* I have not used, accessed or received any information from any other unauthorized
* source in taking this lab assignment. The effort in the assignment thus belongs
* completely to me.
*
*  READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
*  SIGNATURE:   <Ahmet Taha Ekim,   79234>
* 
********************************************************************************/

package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


/**
 * A simple login application with a graphical user interface.
 * Allows users to enter a username and password for authentication.
 * Supports user validation based on stored credentials in a text file.
 * Provides options for login, signup, and additional functionality.
 */
public class TestLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestLogin frame = new TestLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(132, 56, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel usrname = new JLabel("Username");
		usrname.setBounds(33, 61, 87, 16);
		contentPane.add(usrname);
		
		JLabel pswrd = new JLabel("Password");
		pswrd.setBounds(33, 99, 61, 16);
		contentPane.add(pswrd);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(132, 94, 130, 26);
		contentPane.add(passwordField);
		
		JToggleButton toggleButton = new JToggleButton("Show");
		toggleButton.setBounds(269, 94, 61, 29);
		contentPane.add(toggleButton);
		
		toggleButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (toggleButton.isSelected()) {
		            passwordField.setEchoChar((char) 0);
		        } else {
		            passwordField.setEchoChar('*');
		        }
		    }
		});
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String username = textField.getText();
		        char[] passwordChars = passwordField.getPassword();
		        String password = new String(passwordChars);
		        
		        if (validateUser(username, password)) {
		            showUserProfilePage(username);
		        } else {
		            JOptionPane.showMessageDialog(TestLogin.this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButton.setBounds(145, 141, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SignUpPage signUpPage = new SignUpPage();
                signUpPage.setVisible(true);
            }
        });
		btnNewButton_1.setBounds(145, 182, 117, 29);
		contentPane.add(btnNewButton_1);
		
	}
	
	/**
     * Validates the user by checking the entered username and password against stored credentials.
     *
     * @param username The entered username for validation.
     * @param password The entered password for validation.
     * @return True if the user is valid, false otherwise.
     */
	private boolean validateUser(String username, String password) {
        File file = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/user.txt");

        if (!file.exists()) {
        	JOptionPane.showMessageDialog(this, "There is no account in database please click sign up!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lst = line.split(",");
                if (lst[0].equals(username) && lst[1].equals(password)) {
                    JOptionPane.showMessageDialog(TestLogin.this, "Login is succesfull!");
                    return true;
                }
            }
            JOptionPane.showMessageDialog(this, "Wrong username or password. Please try again!", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/**
     * Displays the user profile page for the authenticated user.
     *
     * @param username The username of the authenticated user.
     */
	private void showUserProfilePage(String username) {
		UserProfilePage userProfilePage = new UserProfilePage(username);
	    userProfilePage.setVisible(true);
	    dispose();
    }
	
}
