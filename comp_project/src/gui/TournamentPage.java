package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import player.Player;
import team.Team;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Represents the main page for the basketball tournament GUI.
 * Manages the tournament flow, including regular season and playoffs.
 */
public class TournamentPage extends JFrame {
	
	private JPanel contentPane;
	private JLabel homeLogo;
	private JLabel awayLogo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JTable table;
	private JTextArea resultOutputArea;
    
    private ArrayList<Team> teams;
    private HashSet<String> playedMatches = new HashSet<>();
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
	private int awayScore;
	private int gamesPlayed;
	private int playoffMatchesPlayed;
	private boolean playoffStart;
	
	private JLabel label;
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JLabel label_1_3;
	private JLabel label_1_4;
	private JLabel label_1_5;
	private JLabel label_1_6;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private Random random;
	private Dimension bigLogoSize = new Dimension(159, 160);
	private Dimension smallLogoSize = new Dimension(50, 50);
	
	private boolean paused;
    private File regularSeasonResultsFile;
    private File playoffSeasonResultsFile;
    private File tableFile;
    private JButton pauseButton;
    private JButton resumeButton;
	
    /**
     * Constructs a new TournamentPage with the given list of teams.
     * Initializes GUI components, sets up the tournament, and starts the tournament thread.
     *
     * @param teams List of teams participating in the tournament.
     */
    public TournamentPage(ArrayList<Team> teams) {
        this.teams = teams;
        this.paused = false;
        this.playoffStart = false;
        this.gamesPlayed = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 1280);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
		homeLogo = new JLabel("");
		homeLogo.setBounds(53, 126, 173, 164);
        contentPane.add(homeLogo);
        
        awayLogo = new JLabel("");
        awayLogo.setBounds(615, 126, 173, 164);
        contentPane.add(awayLogo);
        
        JLabel lblNewLabel = new JLabel("-");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 87));
        lblNewLabel.setBounds(388, 188, 61, 16);
        contentPane.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel(String.valueOf(homeScore));
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        lblNewLabel_1.setBounds(265, 159, 90, 69);
        contentPane.add(lblNewLabel_1);
        
        lblNewLabel_1_1 = new JLabel(String.valueOf(awayScore));
        lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 25));
        lblNewLabel_1_1.setBounds(530, 159, 90, 69);
        contentPane.add(lblNewLabel_1_1);
        
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(40, 370, 350, 333);
        contentPane.add(tableScrollPane);
        
        JLabel logo = new JLabel("");
        logo.setBounds(328, 25, 405, 89);
        contentPane.add(logo);
        
        ImageIcon image = new ImageIcon("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/tournement.jpg");
        logo.setIcon(new ImageIcon(getScaledImage(image.getImage(),new Dimension(391, 69))));
        
        resultOutputArea = new JTextArea();
        resultOutputArea.setEditable(false);

        JScrollPane resultScrollPane = new JScrollPane(resultOutputArea);
        resultScrollPane.setBounds(455, 370, 350, 333);
        getContentPane().add(resultScrollPane);
        
        JLabel lblNewLabel_2 = new JLabel("Home");
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel_2.setBounds(117, 98, 61, 16);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Guest");
        lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel_2_1.setBounds(684, 98, 61, 16);
        contentPane.add(lblNewLabel_2_1);
        
        label = new JLabel("");
        label.setBounds(40, 750, 50, 50);
        contentPane.add(label);
        
        label_1 = new JLabel("");
        label_1.setBounds(115, 750, 50, 50);
        contentPane.add(label_1);
        
        label_1_1 = new JLabel("");
        label_1_1.setBounds(240, 750, 50, 50);
        contentPane.add(label_1_1);
        
        label_1_2 = new JLabel("");
        label_1_2.setBounds(315, 750, 50, 50);
        contentPane.add(label_1_2);
        
        label_1_3 = new JLabel("");
        label_1_3.setBounds(450, 750, 50, 50);
        contentPane.add(label_1_3);
        
        label_1_4 = new JLabel("");
        label_1_4.setBounds(525, 750, 50, 50);
        contentPane.add(label_1_4);
        
        label_1_5 = new JLabel("");
        label_1_5.setBounds(650, 750, 50, 50);
        contentPane.add(label_1_5);
        
        label_1_6 = new JLabel("");
        label_1_6.setBounds(725, 750, 50, 50);
        contentPane.add(label_1_6);
        
        JLabel lblNewLabel_3 = new JLabel("|_______|");
        lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(65, 812, 100, 28);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("|________|");
        lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(260, 812, 100, 28);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("|________|");
        lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(470, 812, 100, 28);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("|________|");
        lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_6.setBounds(670, 812, 100, 28);
        contentPane.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("|");
        lblNewLabel_7.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_7.setBounds(100, 837, 61, 16);
        contentPane.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("|");
        lblNewLabel_8.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_8.setBounds(100, 852, 61, 16);
        contentPane.add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("|");
        lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_9.setBounds(299, 839, 61, 16);
        contentPane.add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("|");
        lblNewLabel_10.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_10.setBounds(299, 854, 61, 16);
        contentPane.add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("|");
        lblNewLabel_11.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_11.setBounds(509, 839, 61, 16);
        contentPane.add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("|");
        lblNewLabel_12.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_12.setBounds(509, 854, 61, 16);
        contentPane.add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("|");
        lblNewLabel_13.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_13.setBounds(714, 839, 61, 16);
        contentPane.add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("|");
        lblNewLabel_14.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_14.setBounds(714, 854, 61, 16);
        contentPane.add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("____________________");
        lblNewLabel_15.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel_15.setBounds(135, 888, 150, 16);
        contentPane.add(lblNewLabel_15);
        
        label_2 = new JLabel("");
        label_2.setBounds(77, 875, 50, 50);
        contentPane.add(label_2);
        
        label_3 = new JLabel("");
        label_3.setBounds(280, 875, 50, 50);
        contentPane.add(label_3);
        
        label_4 = new JLabel("");
        label_4.setBounds(489, 875, 50, 50);
        contentPane.add(label_4);
        
        label_5 = new JLabel("");
        label_5.setBounds(695, 875, 50, 50);
        contentPane.add(label_5);
        
        JLabel lblNewLabel_16 = new JLabel("_________________________________________________");
        lblNewLabel_16.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel_16.setBounds(240, 945, 345, 16);
        contentPane.add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("____________________");
        lblNewLabel_17.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblNewLabel_17.setBounds(547, 888, 150, 16);
        contentPane.add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("|");
        lblNewLabel_18.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_18.setBounds(196, 909, 61, 16);
        contentPane.add(lblNewLabel_18);
        
        JLabel lblNewLabel_19 = new JLabel("|");
        lblNewLabel_19.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_19.setBounds(196, 924, 61, 16);
        contentPane.add(lblNewLabel_19);
        
        JLabel lblNewLabel_22 = new JLabel("|");
        lblNewLabel_22.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_22.setBounds(615, 909, 61, 16);
        contentPane.add(lblNewLabel_22);
        
        JLabel lblNewLabel_23 = new JLabel("|");
        lblNewLabel_23.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_23.setBounds(615, 924, 61, 16);
        contentPane.add(lblNewLabel_23);
        
        label_6 = new JLabel("");
        label_6.setBounds(176, 947, 50, 50);
        contentPane.add(label_6);
        
        label_7 = new JLabel("");
        label_7.setBounds(594, 947, 50, 50);
        contentPane.add(label_7);
        
        JLabel lblNewLabel_20 = new JLabel("|");
        lblNewLabel_20.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_20.setBounds(409, 960, 61, 16);
        contentPane.add(lblNewLabel_20);
        
        label_8 = new JLabel("");
        label_8.setBounds(388, 980, 50, 50);
        contentPane.add(label_8);
        
        random = new Random();
        
        paused = false;
        regularSeasonResultsFile = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/season logs/match_results.txt");
        playoffSeasonResultsFile = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/season logs/playoff_results.txt");
        tableFile = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/season logs/table_data.txt");

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paused = true;
            }
        });
        pauseButton.setBounds(700, 20, 100, 45);
        contentPane.add(pauseButton);

        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paused = false;
                pauseButton.setVisible(true);
                resumeButton.setVisible(false);
                startTournament();
            }
        });
        resumeButton.setBounds(700, 20, 100, 45);
        resumeButton.setVisible(false); // Initially, resume button is hidden
        contentPane.add(resumeButton);
        
        JButton btnNewButton = new JButton("View Team");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	paused = true;
                viewSelectedTeam();
            }
        });
        btnNewButton.setBounds(22, 25, 117, 45);
        contentPane.add(btnNewButton);
        
        startTournament();
    }
    
    /**
     * Saves the match results to a file based on whether it's a regular season or playoff match.
     *
     * @param isPlayoff Indicates whether the match is a playoff match.
     */
    private void saveMatchResultsToFile(boolean isPlayoff) {
        File resultsFile;
        if (isPlayoff) {
            resultsFile = playoffSeasonResultsFile;
        } else {
            resultsFile = regularSeasonResultsFile;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFile))) {
            writer.write("=== " + (isPlayoff ? "Playoff" : "Regular") + " Season Paused at Match " + gamesPlayed + " ===\n");
            writer.write(resultOutputArea.getText());
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current table data to a file.
     */
    private void saveTableDataToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tableFile))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Write column names
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write("\t");
                }
            }
            writer.write("\n");

            // Write data
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(String.valueOf(model.getValueAt(row, col)));
                    if (col < model.getColumnCount() - 1) {
                        writer.write("\t");
                    }
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a new window to view detailed information about the selected team.
     * Pauses the tournament while the team information is being viewed.
     */
    private void viewSelectedTeam() {
    	int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            String selectedTeamName = (String) table.getValueAt(selectedRowIndex, 0);
            Team selectedTeam = findTeamByName(selectedTeamName);

            if (selectedTeam != null) {
                TeamViewPage teamInfoPage = new TeamViewPage(selectedTeam);
                teamInfoPage.setVisible(true);
            }
        }
    }

    
    /**
     * Finds a team by its name in the list of teams.
     *
     * @param teamName The name of the team to find.
     * @return The Team object if found; otherwise, returns null.
     */
    private Team findTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    
    /**
     * Initiates and runs the sports tournament simulation in a separate thread.
     * Regular season and playoff matches are simulated with visual updates.
     * Saves match results and table data based on simulation outcomes.
     */
    private void startTournament() {
        new Thread(() -> {
            int totalTeams = teams.size();
            int M = 2 * totalTeams;

            // Regular Season Simulation Loop
            while (gamesPlayed < M) {
                // Select two different teams
                homeTeam = teams.get(random.nextInt(totalTeams));
                awayTeam = teams.get(random.nextInt(totalTeams));

                if (!homeTeam.equals(awayTeam) && !hasExceededMaxMatches() && (homeTeam.getMatch() < 4) && (awayTeam.getMatch() < 4)) {
                    playGame();
                    playedMatches.add(homeTeam.getName() + "-" + awayTeam.getName());
                    gamesPlayed++;
                    homeTeam.incrementMatch();
                    awayTeam.incrementMatch();
                    
                    // Display match results and update points table
                    resultOutputArea.append(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName() + "\n");
                    SwingUtilities.invokeLater(() -> {
                        updatePointsTable();
                    });
                }
                
                if (paused) {
                	saveMatchResultsToFile(false); // Save regular season results
                    saveTableDataToFile();
                    pauseButton.setVisible(false);
                    resumeButton.setVisible(true);
                    return;
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if (!paused) {
                saveMatchResultsToFile(true);
                saveTableDataToFile();
            }
            
            ArrayList<Team> playoffTeams = selectTopTeams(8);
            ArrayList<Team> semiTeams = new ArrayList<>();
            ArrayList<Team> finalTeams = new ArrayList<>();
            playoffMatchesPlayed = 1;
            resultOutputArea.append("Playoffs Starts\n");
            resultOutputArea.append("Quarter Finals\n");
            playoffStart = true;
            // Quarterfinals Simulation Loop
            while (playoffMatchesPlayed <= 4) {
            	homeTeam = playoffTeams.get(random.nextInt(playoffTeams.size()));
            	awayTeam = playoffTeams.get(random.nextInt(playoffTeams.size()));
            	
                if (!awayTeam.equals(homeTeam)) {
                    Team loser = playGame();
                    
                    if(playoffMatchesPlayed == 1) {
    	            	label.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	label_1.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	if(loser.equals(homeTeam)) {
    	            		semiTeams.add(awayTeam);
                        	label_2.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }else {
                        	semiTeams.add(homeTeam);
                        	label_2.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }
                	}
                    else if(playoffMatchesPlayed == 2) {
    	            	label_1_1.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	label_1_2.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	if(loser.equals(homeTeam)) {
    	            		semiTeams.add(awayTeam);
                        	label_3.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }else {
                        	semiTeams.add(homeTeam);
                        	label_3.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }
                	}
                    else if(playoffMatchesPlayed == 3) {
    	            	label_1_3.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	label_1_4.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	if(loser.equals(homeTeam)) {
    	            		semiTeams.add(awayTeam);
                        	label_4.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }else {
                        	semiTeams.add(homeTeam);
                        	label_4.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }
                	}
                    else if(playoffMatchesPlayed == 4) {
    	            	label_1_5.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	label_1_6.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
    	            	if(loser.equals(homeTeam)) {
    	            		semiTeams.add(awayTeam);
                        	label_5.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }else {
                        	semiTeams.add(homeTeam);
                        	label_5.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
                        }
                	}
                    playoffTeams.remove(homeTeam);
                    playoffTeams.remove(awayTeam);
                    
                    playoffMatchesPlayed += 1;
                    gamesPlayed++;
                    resultOutputArea.append(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName() + "\n");
                    if (loser.equals(awayTeam)) {
                    	resultOutputArea.append(homeTeam.getName() + " wins!" + "\n");
                    }else {
                    	resultOutputArea.append(awayTeam.getName() + " wins!" + "\n");
                    }
                    
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            // Semifinals Simulation Loop
            resultOutputArea.append("Semi Finals\n");
            while (playoffMatchesPlayed <= 6){
                if (playoffMatchesPlayed == 5) {
                	homeTeam = semiTeams.get(random.nextInt(2));
                	awayTeam = semiTeams.get(random.nextInt(2));
                	if (!awayTeam.equals(homeTeam)) {
	                	Team loser = playGame();
	                    if(loser.equals(homeTeam)) {
	                    	finalTeams.add(awayTeam);
	                    	label_6.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
	                    }else {
	                    	finalTeams.add(homeTeam);
	                    	label_6.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
	                    }
	                    resultOutputArea.append(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName() + "\n");
	                    if (loser.equals(awayTeam)) {
	                    	resultOutputArea.append(homeTeam.getName() + " wins!" + "\n");
	                    }else {
	                    	resultOutputArea.append(awayTeam.getName() + " wins!" + "\n");
	                    }
	                    playoffMatchesPlayed += 1;
	                    gamesPlayed++;
                	}
                }
                else {
                	homeTeam = semiTeams.get(random.nextInt(2) + 2);
                	awayTeam = semiTeams.get(random.nextInt(2) + 2);
                	if (!awayTeam.equals(homeTeam)) {
	                	Team loser = playGame();
	                    if(loser.equals(homeTeam)) {
	                    	finalTeams.add(awayTeam);
	                    	label_7.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
	                    }else {
	                    	finalTeams.add(homeTeam);
	                    	label_7.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
	                    }
	                    resultOutputArea.append(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName() + "\n");
	                    if (loser.equals(awayTeam)) {
	                    	resultOutputArea.append(homeTeam.getName() + " wins!" + "\n");
	                    }else {
	                    	resultOutputArea.append(awayTeam.getName() + " wins!" + "\n");
	                    }
	                    playoffMatchesPlayed += 1;
	                    gamesPlayed++;
                	}
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            // Final Simulation Loop
            resultOutputArea.append("Final\n");
            while (playoffMatchesPlayed == 7){
            	homeTeam = finalTeams.get(random.nextInt(2));
            	awayTeam = finalTeams.get(random.nextInt(2));

            	if (!awayTeam.equals(homeTeam)) {
	                Team loser = playGame();
	                playoffMatchesPlayed += 1;
	                resultOutputArea.append(homeTeam.getName() + " " + homeScore + " - " + awayScore + " " + awayTeam.getName() + "\n");
	
	                if(loser.equals(homeTeam)) {
	                	resultOutputArea.append(awayTeam.getName() + " wins the tournement!");
	                	label_8.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), smallLogoSize)));
	                }else {
	                	resultOutputArea.append(homeTeam.getName() + " wins the tournement!");
	                	label_8.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), smallLogoSize)));
	                }
                    gamesPlayed++;
            	}

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!paused) saveMatchResultsToFile(true);
        }).start();
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

    /**
     * Plays a single basketball game between two teams.
     * Updates scores and records based on the game outcome.
     *
     * @return The losing team in case of playoffs; otherwise, returns null.
     */
    private Team playGame() {
    	homeScore = 0;
    	awayScore = 0;
    	
    	homeLogo.setIcon(new ImageIcon(getScaledImage(new ImageIcon(homeTeam.getLogoPath()).getImage(), bigLogoSize)));
        awayLogo.setIcon(new ImageIcon(getScaledImage(new ImageIcon(awayTeam.getLogoPath()).getImage(), bigLogoSize)));
        
        SwingUtilities.invokeLater(() -> {
            lblNewLabel_1.setText(String.valueOf(homeScore));
            lblNewLabel_1_1.setText(String.valueOf(awayScore));
        });
    	
    	random = new Random();
    	HashMap<String,ArrayList<Player>> homeRoster = homeTeam.getRoster();
    	HashMap<String,ArrayList<Player>> awayRoster = awayTeam.getRoster();
    	
    	ArrayList<Player> homeFive = new ArrayList<>();
    	ArrayList<Player> awayFive = new ArrayList<>();
    	
    	for(String pos : homeRoster.keySet()) {
    		ArrayList<Player> playersAtPos = homeRoster.get(pos);
    		Player randomPlayer = playersAtPos.get(random.nextInt(playersAtPos.size()));
    		homeFive.add(randomPlayer);
    		homeScore += randomPlayer.getScore();
    	}
    	
    	for(String pos : awayRoster.keySet()) {
    		ArrayList<Player> playersAtPos = awayRoster.get(pos);
    		Player randomPlayer = playersAtPos.get(random.nextInt(playersAtPos.size()));
    		awayFive.add(randomPlayer);
    		awayScore += randomPlayer.getScore();
    	}
    	
    	homeScore *= 1.05;
    	awayScore = Math.round(awayScore);
        homeScore = Math.round(homeScore);
    	
    	if(homeScore > awayScore) {
    		if(playoffStart) {
    			return awayTeam;
    		}
    		homeTeam.incrementWins();
    		awayTeam.incrementLosses();
    	}else if(awayScore > homeScore) {
    		if(playoffStart) {
    			return homeTeam;
    		}
    		homeTeam.incrementLosses();
    		awayTeam.incrementWins();
    	}else {
    		if(playoffStart) {
    			playGame();
    		}else {
	    		homeTeam.incrementTies();
	    		awayTeam.incrementTies();
    		}
    	}
    	return null;
    }
    
    /**
     * Selects the top teams based on their performance in the regular season.
     *
     * @param count The number of top teams to select.
     * @return List of selected top teams.
     */
    private ArrayList<Team> selectTopTeams(int count) {
        teams.sort((t1, t2) -> Integer.compare(t2.getWins(), t1.getWins()));
        return new ArrayList<>(teams.subList(0, Math.min(count, teams.size())));
    }
    
    
    /**
     * Updates the points table in the GUI based on the teams' performance.
     */
    private void updatePointsTable() {
        teams.sort((t1, t2) -> Integer.compare(t2.getWins(), t1.getWins()));
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Team", "Wins", "Losses", "Ties"}, 0);

        for (Team team : teams) {
            model.addRow(new Object[]{team.getName(), team.getWins(), team.getLosses(), team.getTies()});
        }
        table.setModel(model);
    }

    /**
     * Checks if the maximum number of matches has been exceeded between two teams.
     *
     * @return True if the maximum matches limit has been reached; otherwise, false.
     */
    private boolean hasExceededMaxMatches() {
    	String matchKey1 = homeTeam.getName() + "-" + awayTeam.getName();
        return playedMatches.contains(matchKey1);
    }

}