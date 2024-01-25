package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import team.Team;
import user.User;

/**
 * Represents a graphical user interface for team selection.
 * Users can choose a team from a list of available teams with logos.
 * The selected team is then associated with the user for further actions.
 */
public class TeamSelectionPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Team currentTeam;
    private User currentUser;
    private String currentTeamPath;
    private ArrayList<Team> teams;

    /**
     * Constructs a TeamSelectionPage object for the specified user.
     *
     * @param currentUser The user for whom the team selection is performed.
     */
    public TeamSelectionPage(User curntUser) {
    	this.currentUser = curntUser;
    	createTeams();
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 534, 608);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        scrollPane.setViewportView(contentPane);

        setContentPane(scrollPane);

        String[] sampleImagePaths = {
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/atlanta.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/besiktas.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/bulls.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/celtics.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/cleveland.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/detroit.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/fener.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/galatasaray.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/golden state.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/houston.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/indiana.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/la clippers.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/lakers.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/miami.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/milwaukee.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/new york.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/oklahoma.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/toronto.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/Trabzonspor.jpg",
                "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/washington.jpg"
        };

        int columns = 1;
        int rows = sampleImagePaths.length / columns;

        contentPane.setLayout(new GridLayout(rows, columns, 10, 10));

        for (String imagePath : sampleImagePaths) {
            addPhotoButton(imagePath);
        }

        
        JPanel buttonsPanel = new JPanel();
        JButton btnNewButton = new JButton("Go Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        buttonsPanel.add(btnNewButton);

        JButton btnSelectTeam = new JButton("Select Team"); //team listesini ve user i draftinge veriyor
        btnSelectTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCurrentTeam();
                DraftingPlayers draftingPlayers = new DraftingPlayers(currentUser);
                draftingPlayers.setTeams(teams);
                draftingPlayers.setVisible(true);
                dispose();
            }
        });
        
        buttonsPanel.add(btnSelectTeam);

        buttonsPanel.setBounds(5, 536, 524, 39);
        contentPane.add(buttonsPanel);
    }
    
    /**
     * Creates a list of available teams with their names and logo paths.
     * The teams are initialized and added to the instance variable.
     */
    public void createTeams(){
		teams = new ArrayList<>();
		teams.add(new Team("Boston Celticts", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/celtics.jpg"));
		teams.add(new Team("Chicago Bulls", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/bulls.jpg"));
		teams.add(new Team("Cleveland Cavaliers", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/cleveland.jpg"));
		teams.add(new Team("Detroit Pistons", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/detroit.jpg"));
		teams.add(new Team("Golden State Warriors", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/golden state.jpg"));
		teams.add(new Team("LA Clippers", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/la clippers.jpg"));
		teams.add(new Team("Los Angels Lakers", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/lakers.jpg"));
		teams.add(new Team("Miami Heat", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/miami.jpg"));
		teams.add(new Team("Milwaukee Bucks", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/milwaukee.jpg"));
		teams.add(new Team("New York Knicks", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/new york.jpg"));
		teams.add(new Team("Oklahoma City Thunder", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/oklahoma.jpg"));
		teams.add(new Team("Toronto Raptors", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/toronto.jpg"));
		teams.add(new Team("Atlanta Hawks", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/atlanta.jpg"));
		teams.add(new Team("Washington Wizards", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/washington.jpg"));
		teams.add(new Team("Houston Rockets", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/houston.jpg"));
		teams.add(new Team("Indiana Pacers", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/indiana.jpg"));
		teams.add(new Team("Trabzonspor", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/Trabzonspor.jpg"));
		teams.add(new Team("Fenerbahce", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/fener.jpg"));
		teams.add(new Team("Besiktas", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/besiktas.jpg"));
		teams.add(new Team("Galatasaray", "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/logos/galatasaray.jpg"));
		
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	/**
     * Sets the current team based on the selected logo path.
     * Associates the selected team with the current user.
     */
	private void setCurrentTeam() {
    	for (Team t : teams) {
    		if (t.getLogoPath().equals(currentTeamPath)) {
    			currentTeam = t;
    			currentUser.setTeam(currentTeam);
    			JOptionPane.showMessageDialog(TeamSelectionPage.this, "You succesfully selected the " + currentTeam.getName());
    		}
    	}
    }

	/**
     * Adds a button with a team logo to the content pane.
     * The button's action listener records the selected team's logo path.
     *
     * @param imagePath The path to the team logo image.
     */
    private void addPhotoButton(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(getScaledImage(new ImageIcon(imagePath).getImage(), new Dimension(100, 100)));
        JButton photoButton = new JButton(imageIcon);
        photoButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                currentTeamPath = imagePath;
            }
        });
        contentPane.add(photoButton);
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
}