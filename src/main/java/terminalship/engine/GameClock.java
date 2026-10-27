package terminalship.engine;



public class GameClock {

	private GameTime current;
	
	public GameClock(GameTime current) {
		this.current = current;
	}

	public GameTime now() {
		return current;
	}
	
	public void tick() {
		current = current.plus(1);
	}
	
}
