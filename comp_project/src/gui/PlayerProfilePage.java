package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.Player;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * Represents the GUI page displaying detailed information about a player's profile.
 * Shows the player's name, position, points, rebounds, assists, blocks, steals, and overall score.
 * Provides functionality to go back to the previous page.
 */
public class PlayerProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
     * Creates a new PlayerProfilePage for the specified player.
     *
     * @param player The player for which the page is created.
     */
	public PlayerProfilePage(Player player) {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to " + player.getName() + "'s Profile Page");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setBounds(6, 6, 442, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Position:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1.setBounds(40, 43, 71, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Points:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(40, 89, 71, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Rebounds:");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(40, 135, 109, 34);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Assist:");
		lblNewLabel_1_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(322, 43, 71, 34);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Block:");
		lblNewLabel_1_4.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(322, 89, 71, 34);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Steal:");
		lblNewLabel_1_4_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_1_4_1.setBounds(322, 135, 71, 34);
		contentPane.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_2 = new JLabel(player.getPosition());
		lblNewLabel_2.setBounds(131, 53, 109, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(String.valueOf(decimalFormat.format(player.getPoints())));
		lblNewLabel_2_1.setBounds(131, 99, 97, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel(String.valueOf(decimalFormat.format(player.getTotalRebounds())));
		lblNewLabel_2_2.setBounds(131, 145, 109, 16);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel(String.valueOf(decimalFormat.format(player.getAssist())));
		lblNewLabel_2_3.setBounds(393, 53, 61, 16);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel(String.valueOf(decimalFormat.format(player.getBlocks())));
		lblNewLabel_2_4.setBounds(393, 99, 61, 16);
		contentPane.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel(String.valueOf(decimalFormat.format(player.getSteals())));
		lblNewLabel_2_5.setBounds(393, 145, 61, 16);
		contentPane.add(lblNewLabel_2_5);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(443, 6, 117, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Score:");
		lblNewLabel_1_2_1.setForeground(Color.RED);
		lblNewLabel_1_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNewLabel_1_2_1.setBounds(207, 173, 109, 34);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel(String.valueOf(player.getScore()));
		lblNewLabel_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(276, 183, 97, 16);
		contentPane.add(lblNewLabel_2_1_1);
	}

}
