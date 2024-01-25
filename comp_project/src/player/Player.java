package player;

public class Player {
	
	protected String name; 
	private String position;
	protected double points;
	protected double totalRebounds;
	protected double assist;
	protected double blocks;
	protected double steals;
	private int score;

	public Player(String name, String position, double points, double totalRebounds, double assist, double blocks, double steals, int score) {
		this.name = name;
		this.position = position;
		this.points = points;
		this.totalRebounds = totalRebounds;
		this.assist = assist;
		this.blocks = blocks;
		this.steals = steals;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getTotalRebounds() {
		return totalRebounds;
	}

	public void setTotalRebounds(double totalRebounds) {
		this.totalRebounds = totalRebounds;
	}

	public double getAssist() {
		return assist;
	}

	public void setAssist(double assist) {
		this.assist = assist;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getBlocks() {
		return blocks;
	}

	public void setBlocks(double blocks) {
		this.blocks = blocks;
	}

	public double getSteals() {
		return steals;
	}

	public void setSteals(double steals) {
		this.steals = steals;
	}
}
