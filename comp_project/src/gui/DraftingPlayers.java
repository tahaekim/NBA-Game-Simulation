package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import nba.PlayerCreator;
import player.Player;
import team.Team;
import user.User;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Represents a graphical user interface for drafting players in a basketball simulation game.
 * Allows users to select players for their team and automates player selection for other teams.
 */
public class DraftingPlayers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Player> players;
	private ArrayList<Team> teams;
	private Team currentTeam;
	private User currentUser;
	private JTable table;
	private int draftingRound = 1;
	private JTextArea teamOutputArea;

	/**
     * Constructs a DraftingPlayers object for the specified user.
     *
     * @param currentUser The user for whom the player drafting is performed.
     */
	public DraftingPlayers(User currentUser) {
		this.currentUser = currentUser;
		this.currentTeam = currentUser.getTeam();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 80, 915, 870);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 50, 875, 670);
        contentPane.add(scrollPane);
		
		table = new JTable();
        scrollPane.setViewportView(table);
        
        
        JLabel lblNewLabel = new JLabel("User: " + currentUser.getName() + "\tTeam: " + currentTeam.getName() + "\tDrafting Round: " + draftingRound);
        lblNewLabel.setBounds(19, 22, 502, 16);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
        contentPane.add(lblNewLabel);
        
        JButton doneButton = new JButton("Done");
        doneButton.setBounds(740, 780, 150, 40);
        contentPane.add(doneButton);
        
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if((currentTeam.getRoster().size() <= 15) && (currentTeam.getRoster().size() >= 5)) {
	                for(Team t : teams) {
	                	HashMap<String,ArrayList<Player>> roster = t.getRoster();
	                	if(roster.keySet().size() != 5) {
	                		JOptionPane.showMessageDialog(DraftingPlayers.this,t.getName() + " Does not satisfy the roster conditions. The teams must include players for all five positions. You must go back and restart the drafting!");
	                	}else teamLog(t);
	                }
	                currentUser.setDraftingCheck(true);
            		JOptionPane.showMessageDialog(DraftingPlayers.this,"Drafting part succesfully completed");
                }
            	else JOptionPane.showMessageDialog(DraftingPlayers.this, "To be done with drafting all the teams must select at least 5 players.");
            }
        });
        
        PlayerLister();
		updateTable();
		
		teamOutputArea = new JTextArea();
        teamOutputArea.setEditable(false);

        JScrollPane teamScrollPane = new JScrollPane(teamOutputArea);
        teamScrollPane.setBounds(19, 735, 700, 85);
        contentPane.add(teamScrollPane);
        
        JButton selectPlayerButton = new JButton("Select Player");
        selectPlayerButton.setBounds(740, 732, 150, 40);
        contentPane.add(selectPlayerButton);
        
        JButton btnNewButton = new JButton("Go Back");
        btnNewButton.setBounds(764, 6, 130, 38);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
        selectPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (draftingRound <= 15) {
                	lblNewLabel.setText("User: " + currentUser.getName() + "\tTeam: " + currentTeam.getName() + "\tDrafting Round: " + draftingRound);
                	teamOutputArea.append("--------------- Round" + draftingRound + "---------------\n");
                	
                    for(Team currentDraftingTeam : teams) {
                    	
	                    if (currentTeam == currentDraftingTeam) {
	                        // If it's the user's team, let the user choose a player
	                        int selectedRowIndex = table.getSelectedRow();
	                        if (selectedRowIndex != -1) {
	                            String selectedPlayerName = (String) table.getValueAt(selectedRowIndex, 0);
	                            Player player = findPlayer(selectedPlayerName);
	                            currentTeam.addPlayer(player);
	                            players.remove(player);
	                            JOptionPane.showMessageDialog(DraftingPlayers.this, "You selected " + selectedPlayerName + ". You are being forwarded to the next teams!");
	
	                        } else {
	                            JOptionPane.showMessageDialog(DraftingPlayers.this, "Please select a player before moving to the next round.");
	                        }
	                    } else {
	                        // If it's not the user's team, randomly assign a player to the team
	                    	if(draftingRound <= 5) {
		                        Player randomPlayer = getRandomPlayer1(currentDraftingTeam);
		                        currentDraftingTeam.addPlayer(randomPlayer);
		                        players.remove(randomPlayer);
		                        teamOutputArea.append(randomPlayer.getName() + " assigned to " + currentDraftingTeam.getName() + ".\n");
	                    	}else{
	                    		Player randomPlayer = getRandomPlayer2();
		                        currentDraftingTeam.addPlayer(randomPlayer);
		                        players.remove(randomPlayer);
		                        teamOutputArea.append(randomPlayer.getName() + " assigned to " + currentDraftingTeam.getName() + ".\n");
	                    	}
	                    }
                    }
                    draftingRound++;
                    Collections.reverse(teams);
                    updateTable();
                    teamOutputArea.append("\n");
                } 
                else {
                    JOptionPane.showMessageDialog(DraftingPlayers.this, "There can be max 15 round. You must click to Done button!");
                }
            }
        });
        
	}
	
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
	
    /**
     * Retrieves the list of players available for drafting.
     *
     * @return The list of available players.
     */
	private void PlayerLister(){
		PlayerCreator playerCreator = new PlayerCreator();
		players = playerCreator.readCsvFile();
	}
	
	/**
     * Updates the player information table with the latest player data.
     */
	private void updateTable() {
		if (players != null && players.size() > 0) {
	        String[] columnNames = {"Name", "Position", "Total Rebounds", "Assist", "Blocks", "Steals", "Points"};
	        DefaultTableModel model = new DefaultTableModel(null, columnNames);
	        DecimalFormat decimalFormat = new DecimalFormat("#.###");
	        
	        for (Player player : players) {
	            Object[] rowData = {player.getName(), player.getPosition(), decimalFormat.format(player.getTotalRebounds()), decimalFormat.format(player.getAssist()), decimalFormat.format(player.getBlocks()), decimalFormat.format(player.getSteals()), decimalFormat.format(player.getPoints())};
	            model.addRow(rowData);
	        }
	        table.setModel(model);
	    }
	}
	
	/**
     * Finds a player with the specified name in the list of available players.
     *
     * @param playerName The name of the player to find.
     * @return The Player object if found, or null otherwise.
     */
	private Player findPlayer(String PlayerName) {
		for(Player p : players) {
			if (p.getName().equals(PlayerName)) {
				return p;
			}
		}
		return null;
	}
	
	/**
     * Selects a random player based on the team's preferred position.
     * Ensures that the player's position matches the team's requirements.
     *
     * @param team The team for which the player is selected.
     * @return The randomly selected player.
     */
	private Player getRandomPlayer1(Team team) {
		String randomPosition = team.getRandomPosition();
		Random random = new Random();
        int randomIndex = random.nextInt(players.size());
        Player randomPlayer = players.get(randomIndex);
        
        while(!randomPlayer.getPosition().equals(randomPosition)) {
        	randomIndex = random.nextInt(players.size());
        	randomPlayer = players.get(randomIndex);
        }
		return randomPlayer;
	}
	
	/**
     * Selects a random player without considering the team's preferred position.
     *
     * @return The randomly selected player.
     */
	private Player getRandomPlayer2() {
		Random random = new Random();
        int randomIndex = random.nextInt(players.size());
		return players.get(randomIndex);
	}
	
	/**
     * Generates a log file for the drafted players in a team and saves it to a specified directory.
     *
     * @param team The team for which the log is generated.
     */
	private void teamLog(Team team) {
		File file = new File("/Users/a.tahaekim/git/Comp132-Project/comp_project/src/teamlogs/" + team.getName() + ".txt");
		HashMap<String,ArrayList<Player>> roster = team.getRoster();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(String position : roster.keySet()) {
				writer.write(position + ": ");
				for(Player p : roster.get(position)) {
					writer.write(p.getName() + ", ");
				}
				writer.write("\n");
			}
			writer.close();
		} catch (IOException a) {
			a.printStackTrace();
		}
	}

}
