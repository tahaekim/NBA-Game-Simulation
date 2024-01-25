package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import user.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team.Team;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

/**
 * A JFrame class representing the user profile page.
 * Displays user information, allows modification of personal details,
 * and provides options for selecting and managing teams.
 */
public class UserProfilePage extends JFrame implements UserInfoUpdateCallback {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private User currentUser;
	private JLabel lblNewLabel;
	private PhotoSelectionPage photoSelectionPage;
	private ArrayList<Team> teams = new ArrayList<Team>();;

	/**
     * Constructs a UserProfilePage for the given username.
     * Initializes the user profile page with user information and options.
     *
     * @param username The username of the current user.
     */
    public UserProfilePage(String username) {
    	currentUser = findUser(username);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 516, 531);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(238, 238, 238));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon originalIcon = new ImageIcon(currentUser.getPhotoPath());

        lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setIcon(new ImageIcon(getScaledImage(originalIcon.getImage(), new Dimension(159, 160))));
        lblNewLabel.setBounds(20, 36, 159, 160);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Change Photo");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPhotoSelectionDialog();
            }
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(40, 208, 117, 29);
        contentPane.add(btnNewButton);

        JLabel lblUsername = new JLabel("Welcome, " + username + "!");
        lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblUsername.setBounds(10, 10, 200, 20);
        contentPane.add(lblUsername);
        
        JLabel lblNewLabel_1 = new JLabel("Name: " + currentUser.getName());
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(202, 42, 146, 16);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Surname: " + currentUser.getSurname());
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(202, 79, 164, 16);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Username:" + currentUser.getNickname());
        lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(202, 115, 200, 26);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Email: " + currentUser.getEmail());
        lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_4.setBounds(202, 153, 277, 25);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Age: " + currentUser.getAge());
        lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_5.setBounds(202, 190, 84, 25);
        contentPane.add(lblNewLabel_5);
        
        JButton btnNewButton_1 = new JButton("Modify Personal Info");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openModifyPersonalInfoDialog();
            }
        });
        btnNewButton_1.setBounds(267, 186, 159, 29);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Select Team");
        btnNewButton_2.setBounds(20, 275, 137, 45);
        contentPane.add(btnNewButton_2);
        
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTeamSelectionPage();
            }
        });
        
        JButton btnNewButton_2_1 = new JButton("View Team");
        btnNewButton_2_1.setBounds(188, 275, 137, 45);
        contentPane.add(btnNewButton_2_1);
        
        btnNewButton_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTeamView();
            }
        });
        
        JButton btnNewButton_2_1_1 = new JButton("Start Season");
        btnNewButton_2_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		startTournament();
        	}
        });
        btnNewButton_2_1_1.setBounds(352, 275, 137, 45);
        contentPane.add(btnNewButton_2_1_1);
        
        JButton btnNewButton_3 = new JButton("Sign Out");
        btnNewButton_3.setBounds(393, 6, 117, 38);
        contentPane.add(btnNewButton_3);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					TestLogin frame = new TestLogin();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
                dispose();
            }
        });
    }
    
    public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
    
	/**
     * Finds a user based on the given username.
     *
     * @param username The username to search for.
     * @return The found user or null if not found.
     */
    private User findUser(String username) {
    	SignUpPage signUpPage = new SignUpPage();
        ArrayList<User> users = signUpPage.getAllUsers();
        
        for (User u : users) {
        	if ((u.getNickname()).equals(username)) return u;
        }
        return null;
    }
    
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
    
    private void openPhotoSelectionDialog() {
    	JDialog dialog = new JDialog(this, "Photo Selection", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        photoSelectionPage = new PhotoSelectionPage();
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
     * Opens the team selection page.
     * Allows the user to select a team.
     */
    private void openTeamSelectionPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeamSelectionPage frame = new TeamSelectionPage(currentUser);
                    frame.setVisible(true);
                    teams = frame.getTeams(); // olmadi asagidakki iften ekle
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void updateProfilePhoto(String selectedPhotoPath) {
        ImageIcon newIcon = new ImageIcon(selectedPhotoPath);
        lblNewLabel.setIcon(new ImageIcon(getScaledImage(newIcon.getImage(), new Dimension(159, 160))));
    }
    
    private void openModifyPersonalInfoDialog() {
        JDialog modifyDialog = new JDialog(this, "Modify Personal Info", true);
        modifyDialog.setSize(400, 300);
        modifyDialog.setLocationRelativeTo(this);

        ModifyPanel modifyPanel = new ModifyPanel();
        modifyPanel.setCallback(this);

        modifyDialog.setContentPane(modifyPanel.getContentPane());
        modifyDialog.setVisible(true);
    }
    
    private boolean isValidPassword(String password) {
    	String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
    	Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    private boolean isValidEmail(String email) {
    	String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    	Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private boolean updateEmail(String newEmail) {
		if (isValidEmail(newEmail)) {
			currentUser.setEmail(newEmail);
			return true;
		}
		else JOptionPane.showMessageDialog(UserProfilePage.this, "Please enter a valid e-mail address.");
    	return false;
    }
    
    private boolean updateAge(String newAge) {
        try {
            int age = Integer.parseInt(newAge);
            if (age > 12) {
                currentUser.setAge(age);
                return true;
            } else {
                JOptionPane.showMessageDialog(UserProfilePage.this, "You must be at least 12 years old.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(UserProfilePage.this, "Please enter a valid age.");
        }
    	return false;
    }
    
    
	private boolean updatePassword(String currentPassword, String newPassword) {
		if(currentPassword.equals(currentUser.getPassword()) && isValidPassword(newPassword)) {
			currentUser.setPassword(newPassword);
			return true;
		}
		else if(currentPassword.equals(currentUser.getPassword()) && !isValidPassword(newPassword)) {
			JOptionPane.showMessageDialog(UserProfilePage.this, "Please enter a valid new password.");
		}
		else if(!currentPassword.equals(currentUser.getPassword()) && isValidPassword(newPassword)) {
			JOptionPane.showMessageDialog(UserProfilePage.this, "Please check your current password.");
		}
		else JOptionPane.showMessageDialog(UserProfilePage.this, "Please check your both password.");
		return false;
    }
    
	/**
	 * Updates the user information with the provided details.
	 * Validates and updates the user's email, age, and password.
	 * If all updates are successful, saves the changes to the user data file.
	 *
	 * @param newEmail         The new email address for the user.
	 * @param newAge           The new age for the user.
	 * @param currentPassword  The current password for validation.
	 * @param newPassword      The new password to set if validation is successful.
	 */
    public void updateUserInformation(String newEmail, String newAge, String currentPassword, String newPassword) {
    	boolean a = updateEmail(newEmail);
    	boolean b = updateAge(newAge);
    	boolean c = updatePassword(currentPassword, newPassword);
    	if((a == true) && (b == true) && (c == true)) {
    		JOptionPane.showMessageDialog(UserProfilePage.this, "You succesfully saved the changes.");
    		File file = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/user.txt");
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < lines.size(); i++) {
                String[] userInfo = lines.get(i).split(",");
                if (userInfo[0].equals(currentUser.getNickname())) {
                	userInfo[1] = newPassword;
                    userInfo[2] = newEmail;
                    userInfo[5] = newAge;
                    userInfo[6] = photoSelectionPage.getSelectedImagePath(); 
                    lines.set(i, String.join(",", userInfo));
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
    
    /**
     * Opens the team view page.
     * Displays information about the user's selected team.
     */
    private void openTeamView() {
    	if (currentUser.getTeam() != null && currentUser.isDraftingCheck()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        TeamViewPage frame = new TeamViewPage(currentUser.getTeam());
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(UserProfilePage.this, "First, you need to select a team and draft players.");
        }
    }
    
    /**
     * Starts a tournament.
     * Opens the TournamentPage with the user's selected teams.
     */
    private void startTournament() {
    	if (currentUser.getTeam() != null && currentUser.isDraftingCheck()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        TournamentPage frame = new TournamentPage(teams);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(UserProfilePage.this, "First, you need to select a team and draft players.");
        }
    }
}
