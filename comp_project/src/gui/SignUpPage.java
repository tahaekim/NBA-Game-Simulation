package gui;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import user.*;

/**
 * A graphical user interface for user registration.
 * Allows users to provide main and personal information for account creation.
 * Validates user input and ensures unique usernames and emails.
 * Supports photo selection for user profile pictures.
 */
public class SignUpPage extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private String imagePath = "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/default.jpg";
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField textField_1;
    private JLabel lblNewLabel_4;
    private JPasswordField passwordField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    /**
     * Creates the SignUpPage frame.
     */
	public SignUpPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 516, 531);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(238, 238, 238));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon originalIcon = new ImageIcon(imagePath);

        lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setIcon(new ImageIcon(getScaledImage(originalIcon.getImage(), new Dimension(159, 160))));
        lblNewLabel.setBounds(20, 26, 159, 160);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Select Photo");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPhotoSelectionDialog();
            }
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(40, 198, 117, 29);
        contentPane.add(btnNewButton);
        
        textField = new JTextField();
        textField.setBounds(231, 71, 226, 34);
        contentPane.add(textField);
        textField.setColumns(10);
        
        lblNewLabel_1 = new JLabel("Main Info");
        lblNewLabel_1.setBackground(Color.WHITE);
        lblNewLabel_1.setBounds(214, 26, 61, 16);
        contentPane.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Username");
        lblNewLabel_2.setBounds(231, 54, 96, 16);
        contentPane.add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Email");
        lblNewLabel_3.setBounds(231, 112, 61, 16);
        contentPane.add(lblNewLabel_3);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(231, 131, 226, 34);
        contentPane.add(textField_1);
        
        lblNewLabel_4 = new JLabel("Password");
        lblNewLabel_4.setBounds(231, 177, 61, 16);
        contentPane.add(lblNewLabel_4);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(231, 197, 226, 34);
        contentPane.add(passwordField);
        
        JToggleButton toggleButton = new JToggleButton("Show");
        toggleButton.setBackground(Color.WHITE);
		toggleButton.setBounds(466, 202, 44, 21);
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
        
        JLabel lblNewLabel_1_1 = new JLabel("Personal Info");
        lblNewLabel_1_1.setBackground(Color.WHITE);
        lblNewLabel_1_1.setBounds(80, 266, 113, 16);
        contentPane.add(lblNewLabel_1_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(106, 312, 257, 34);
        contentPane.add(textField_2);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Name");
        lblNewLabel_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_1_1_1.setBounds(106, 294, 113, 16);
        contentPane.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Surname");
        lblNewLabel_1_1_1_1.setBackground(Color.WHITE);
        lblNewLabel_1_1_1_1.setBounds(106, 358, 113, 16);
        contentPane.add(lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_1_1_1_2 = new JLabel("Age");
        lblNewLabel_1_1_1_2.setBackground(Color.WHITE);
        lblNewLabel_1_1_1_2.setBounds(106, 425, 113, 16);
        contentPane.add(lblNewLabel_1_1_1_2);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(106, 379, 257, 34);
        contentPane.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(106, 449, 68, 34);
        contentPane.add(textField_4);
        
        JButton btnNewButton_1 = new JButton("Submit");
        btnNewButton_1.setBackground(new Color(134, 215, 77));
        btnNewButton_1.setBounds(370, 445, 113, 44);
        contentPane.add(btnNewButton_1);
        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String email = textField_1.getText();
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Please enter a valid e-mail address.");
                    return;
                }
                
                String ageText = textField_4.getText();
                try {
                    int age = Integer.parseInt(ageText);
                    if (age < 12) {
                        JOptionPane.showMessageDialog(SignUpPage.this, "You must be at least 12 years old.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Enter a valid number in the age field.");
                    return;
                }

                String username = textField.getText();
                if (!isValidUsername(username)) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Your username must contain only letters and numbers.");
                    return;
                }

                String name = textField_2.getText();
                String surname = textField_3.getText();
                if (!isValidName(name) || !isValidName(surname)) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Name and surname must contain only letters and must contain at least 3 letters.");
                    return;
                }

                String password = new String(passwordField.getPassword());
                if (!isValidPassword(password)) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Your password must be at least 8 characters and contain at least one letter, one number and one special character.");
                    return;
                }

                if(isValidEmail(email) && isValidName(surname) && isValidName(name) && isValidPassword(password) && isValidUsername(username) && isUniqueUser(username, email)) {
                	
                	try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/user.txt", true));
						writer.write(username + "," + password + "," +  email + "," + name + "," + surname + "," +  ageText + "," +  imagePath + "\n");
						writer.close();
						JOptionPane.showMessageDialog(SignUpPage.this, "Registration successful! User added.");
					} catch (IOException a) {
						a.printStackTrace();
					}
                }
            }
        });

        contentPane.add(btnNewButton_1); 
        
        JButton btnNewButton_1_1 = new JButton("Go Back");
        btnNewButton_1_1.setBackground(new Color(134, 215, 77));
        btnNewButton_1_1.setBounds(245, 445, 113, 44);
        contentPane.add(btnNewButton_1_1);
        
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
	/**
     * Validates the format of the entered email address.
     *
     * @param email The email address to be validated.
     * @return True if the email address is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
    	String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    	Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates the format of the entered username.
     *
     * @param username The username to be validated.
     * @return True if the username is valid, false otherwise.
     */
    private boolean isValidUsername(String username) {
    	String usernameRegex = "^[a-zA-Z0-9]+$";
    	Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * Validates the format of the entered name.
     *
     * @param name The name to be validated.
     * @return True if the name is valid, false otherwise.
     */
    private boolean isValidName(String name) {
    	String nameSurnameRegex = "^[a-zA-Z]{3,}$";
    	Pattern pattern = Pattern.compile(nameSurnameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Validates the format of the entered password.
     *
     * @param password The password to be validated.
     * @return True if the password is valid, false otherwise.
     */
    private boolean isValidPassword(String password) {
    	String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
    	Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    /**
     * Checks if the entered username and email are unique in the user database.
     *
     * @param username The entered username.
     * @param email    The entered email address.
     * @return True if the username and email are unique, false otherwise.
     */
    private boolean isUniqueUser(String username, String email) {
    	File file = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/user.txt");

        if (!file.exists()) {
            return true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lst = line.split(",");
                if (lst[0].equals(username) || lst[2].equals(email)) {
                    if (lst[0].equals(username) && lst[2].equals(email)) {
                        JOptionPane.showMessageDialog(SignUpPage.this, "This user already exists. Please change your username and email");
                    } else if (lst[0].equals(username)) {
                        JOptionPane.showMessageDialog(SignUpPage.this, "This user already exists. Please change your username");
                    } else if (lst[2].equals(email)) {
                        JOptionPane.showMessageDialog(SignUpPage.this, "This user already exists. Please change your email");
                    }
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
   
    /**
     * Retrieves a list of all users from the user database.
     *
     * @return An ArrayList of User objects representing all users.
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        File file = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/user.txt");

        if (!file.exists()) {
            return userList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lst = line.split(",");
                if (lst.length == 7) {
                    String username = lst[0];
                    String password = lst[1];
                    String email = lst[2];
                    String name = lst[3];
                    String surname = lst[4];
                    int age = Integer.parseInt(lst[5]);
                    String photoPath = lst[6];

                    User user = new User(username, name, surname, age, email, password, photoPath);
                    userList.add(user);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * Scales the given image to fit within the specified dimensions while maintaining its aspect ratio.
     * Uses the Image.SCALE_SMOOTH algorithm for smooth scaling.
     *
     * @param srcImg The original Image to be scaled.
     * @param size   The desired dimensions (width and height) to fit the image into.
     * @return A new Image instance representing the scaled image.
     */
    private Image getScaledImage(Image srcImg, Dimension size) {
        int originalWidth = srcImg.getWidth(null);
        int originalHeight = srcImg.getHeight(null);
        int boundWidth = size.width;
        int boundHeight = size.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > boundWidth || originalHeight > boundHeight) {
            double ratio = (double) originalWidth / (double) originalHeight;
            newWidth = (int) (boundHeight * ratio);
            newHeight = boundHeight;

            if (newWidth > boundWidth) {
                newWidth = boundWidth;
                newHeight = (int) (boundWidth / ratio);
            }
        }

        return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    /**
     * Opens a dialog for selecting a user profile photo.
     */
    private void openPhotoSelectionDialog() {
    	JDialog dialog = new JDialog(this, "Photo Selection", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        PhotoSelectionPage photoSelectionPage = new PhotoSelectionPage();
        dialog.setContentPane(photoSelectionPage.getContentPane());

        dialog.setVisible(true);

        if (!photoSelectionPage.isDialogCancelled()) {
            String selectedPhotoPath = photoSelectionPage.getSelectedImagePath();

            if (selectedPhotoPath != null) {
                updateProfilePhoto(selectedPhotoPath);
            }
        }
    }

    /**
     * Updates the user profile photo with the selected photo path.
     *
     * @param selectedPhotoPath The path of the selected photo.
     */
    public void updateProfilePhoto(String selectedPhotoPath) {
        ImageIcon newIcon = new ImageIcon(selectedPhotoPath);
        imagePath = selectedPhotoPath;
        lblNewLabel.setIcon(new ImageIcon(getScaledImage(newIcon.getImage(), new Dimension(159, 160))));
    }
}