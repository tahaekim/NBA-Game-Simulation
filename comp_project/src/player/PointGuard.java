package player;

public class PointGuard extends Player {
	
	public PointGuard(String name, double points, double totalRebounds, double assist, double blocks, double steals, int score) {
		super(name, "PG", points, totalRebounds, assist, blocks, steals, score);
	}
}
