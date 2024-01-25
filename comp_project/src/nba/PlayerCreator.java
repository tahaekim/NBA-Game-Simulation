package nba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import player.*;

/**
 * This class is responsible for creating Player objects by reading data from a CSV file.
 */
public class PlayerCreator {

	/**
     * Reads data from a CSV file and creates Player objects based on the information.
     *
     * @return An ArrayList of Player objects.
     */
	public ArrayList<Player> readCsvFile() {
		String file = "src/2022-2023 NBA Player Stats - Regular.csv";
		ArrayList<Player> players = new ArrayList<>();
		HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
		BufferedReader reader = null;
        String line = "";
        

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                
                String name = row[1];
                String tm = row[4];
                String position = row[2];
            	
            	if (!name.equals("Player")) {
            		double points = weightCalculator(Double.parseDouble(row[29]),"points");
                	double totalRebounds = weightCalculator(Double.parseDouble(row[23]),"totalRebounds");
                	double assist = weightCalculator(Double.parseDouble(row[24]),"assist");
                	double blocks = weightCalculator(Double.parseDouble(row[26]),"blocks");
                	double steals = weightCalculator(Double.parseDouble(row[25]),"steals");
                	hash.computeIfAbsent(name, k -> new ArrayList<>()).add(tm); //Gets all duplicates
                	
                	if(hash.get(name).size() == 1) { //Gets TOT
                		int score = (int) Math.round(points + totalRebounds + assist + blocks + steals);
	            		if (position.equals("C")) {
	            			Player center = new Center(name, points, totalRebounds, assist, blocks, steals, score);
	            			players.add(center);
						} 
	            		else if(position.equals("PG")){
	            			Player pg = new PointGuard(name, points, totalRebounds, assist, blocks, steals, score);
	            			players.add(pg);
	            		}
	            		else if(position.equals("PF")){
	            			Player pf = new PowerForward(name, points, totalRebounds, assist, blocks, steals, score);
	            			players.add(pf);
	            		}
	            		else if(position.equals("SF")){
	            			Player sf = new SmallForward(name, points, totalRebounds, assist, blocks, steals, score);
	            			players.add(sf);
	            		}
	            		else {
	            			Player sg = new ShootingGuard(name, points, totalRebounds, assist, blocks, steals, score);
	            			players.add(sg);
						}
            		}
            	}
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
	}
	
	/**
     * Calculates a weighted value based on the given data and type.
     *
     * @param data The data value to be weighted.
     * @param type The type of data (e.g., "points", "totalRebounds", etc.).
     * @return The weighted value.
     */
	private double weightCalculator(double data , String type) {
		int N;
		if(type.equals("points")) {
			N = 12;
		}else if(type.equals("totalRebounds")) {
			N = 3;
		}else if(type.equals("assist")) {
			N = 2;
		}else if(type.equals("blocks")) {
			N = 0;
		}else {
			N = 1;
		}
		double randomValue = Math.max(0, data - N) + new Random().nextDouble() * (2 * N);
		return randomValue;
	}
	
}
