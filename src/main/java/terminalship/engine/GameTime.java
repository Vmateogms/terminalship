package terminalship.engine;

public record GameTime(long minutes) implements Comparable<GameTime> {
	
	public GameTime plus(long amount) {
		return new GameTime(this.minutes + amount);
	}

	@Override
	public int compareTo(GameTime o) {
		// TODO Auto-generated method stub
		return Long.compare(this.minutes, o.minutes());
	}

}
