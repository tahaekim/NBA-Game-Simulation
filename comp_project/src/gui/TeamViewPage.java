package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import player.Player;
import team.Team;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Represents the GUI page for viewing detailed information about a team,
 * including its roster categorized by player positions.
 * Provides functionality to go back, view player details, and displays
 * the team's logo and player rosters.
 */
public class TeamViewPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane centers;
	private JScrollPane pointGuards;
	private JScrollPane powerForwards;
	private JScrollPane smallForwards;
	private JScrollPane shootingGuards;
	private JButton btnNewButton;
	private HashMap<String,ArrayList<Player>> roster = new HashMap<>();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	
	/**
     * Creates a new TeamViewPage for the specified team.
     *
     * @param team The team for which the page is created.
     */
	public TeamViewPage(Team team) {
		roster = team.getRoster();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1084, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 16, 211, 201);
		contentPane.add(panel);
		
		ImageIcon teamLogo = new ImageIcon(getScaledImage(new ImageIcon(team.getLogoPath()).getImage(), new Dimension(184, 184)));
        JLabel logoLabel = new JLabel(teamLogo);
        panel.add(logoLabel);
		
		btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(940, 12, 125, 40);
		contentPane.add(btnNewButton);

		centers = new JScrollPane();
        centers.setBounds(25, 271, 175, 340);
        contentPane.add(centers);
        updateTable(centers, "C");

        pointGuards = new JScrollPane();
        pointGuards.setBounds(239, 271, 175, 340);
        contentPane.add(pointGuards);
        updateTable(pointGuards, "PG");

        powerForwards = new JScrollPane();
        powerForwards.setBounds(457, 271, 175, 340);
        contentPane.add(powerForwards);
        updateTable(powerForwards, "PF");

        smallForwards = new JScrollPane();
        smallForwards.setBounds(670, 271, 175, 340);
        contentPane.add(smallForwards);
        updateTable(smallForwards, "SF");

        shootingGuards = new JScrollPane();
        shootingGuards.setBounds(884, 271, 175, 340);
        contentPane.add(shootingGuards);
        updateTable(shootingGuards, "SG");
		
		JLabel lblNewLabel = new JLabel("Welcome to " + team.getName() + "'s Team Page");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setBounds(239, 16, 393, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Centers");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1.setBounds(75, 246, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Point Guards");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2.setBounds(279, 246, 97, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Power Forwards");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_3.setBounds(488, 246, 123, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Small Forwards");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_4.setBounds(704, 247, 125, 16);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Shooting Guards");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_5.setBounds(915, 247, 120, 16);
		contentPane.add(lblNewLabel_5);
		
		btnNewButton_1 = new JButton("View Player");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton_1.setBounds(390, 625, 300, 40);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int selectedRow = -1;
		        JTable selectedTable = null;

		        for (JScrollPane scrollPane : Arrays.asList(centers, pointGuards, powerForwards, smallForwards, shootingGuards)) {
		            JTable table = (JTable) scrollPane.getViewport().getView();
		            selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                selectedTable = table;
		                break;
		            }
		        }

		        if (selectedTable != null) {
		            String playerName = (String) selectedTable.getValueAt(selectedRow, 0);
		            PlayerProfilePage profilePage = new PlayerProfilePage(findPlayer(playerName));
		            profilePage.setVisible(true);
		            selectedTable.clearSelection();
		        }
		    }
		});
	}
	
	/**
     * Updates the JTable in the specified JScrollPane with player information based on the position.
     *
     * @param scrollPane The JScrollPane containing the JTable to be updated.
     * @param position   The position for which the player information should be displayed.
     */
	private void updateTable(JScrollPane scrollPane, String position) {
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");

        ArrayList<Player> players = roster.get(position);
        if (players != null) {
            for (Player player : players) {
                model.addRow(new Object[] { player.getName() });
            }
        }
        table.setModel(model);
        scrollPane.setViewportView(table);
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
     * Finds and returns the Player object with the specified name from the team's roster.
     *
     * @param playerName The name of the player to be found.
     * @return The Player object with the specified name, or null if not found.
     */
	private Player findPlayer(String playerName) {
		for(String position : roster.keySet()) {
			for(Player p :roster.get(position)){
				if(p.getName().equals(playerName)) return p;
			}
		}
		return null;
	}
	
}
