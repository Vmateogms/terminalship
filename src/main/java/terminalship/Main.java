package terminalship;

import terminalship.engine.GameClock;
import terminalship.engine.GameTime;
import terminalship.entities.Coordinate;
import terminalship.entities.Drone;

public class Main {
	public static void main(String[] args) throws Exception {
		
		GameClock clock = new GameClock(new GameTime(540));
		
		
		for(int i = 0; i < 90; i++) {
			 clock.tick();
		}
		System.out.println(clock.now());

	
	Coordinate coordinate = new Coordinate(0, 0);
	Coordinate coordinate2 = new Coordinate(3, 4);
	System.out.println(coordinate.distanceTo(coordinate2));
	
	Drone drone = new Drone(1, new Coordinate(0, 0), new Coordinate(3,4), 60);
	drone.position().distanceTo(drone.destination());
	System.out.println(drone.status());
	
	
	}	
}