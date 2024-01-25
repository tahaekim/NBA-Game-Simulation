package team;

import player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Team {
	
	private String name;
    private String logoPath;
    private HashMap<String,ArrayList<Player>> roster;
    private ArrayList<String> rosterSurety = new ArrayList<>(Arrays.asList("C", "PG", "PF", "SF", "SG"));
    private int wins;
    private int losses;
    private int ties;
    private int matchPlayed = 0;

    public Team(String name, String logoPath) {
        this.name = name;
        this.logoPath = logoPath;
        this.roster = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public HashMap<String,ArrayList<Player>> getRoster() {
        return roster;
    }

    public void addPlayer(Player player) {
    	roster.computeIfAbsent(player.getPosition(), k -> new ArrayList<>()).add(player);
    }
    
    /**
     * Gets a random position from the rosterSurety list.
     * Ensures that first 5 positions are different from each other.
     *
     * @return A randomly selected position from the rosterSurety list.
     */
    public String getRandomPosition() {
    	Random random = new Random();
    	String position = rosterSurety.get(random.nextInt(rosterSurety.size()));
    	rosterSurety.remove(position);
    	return position;
    }
    
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public void incrementTies() {
        ties++;
    }
    
    public int getMatch() {
        return matchPlayed;
    }

    public void incrementMatch() {
        matchPlayed++;
    }

}
