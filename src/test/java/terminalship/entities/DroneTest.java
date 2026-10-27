package terminalship.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DroneTest {

	
	@Test
	void newDroneStartsEnRoute() {
		Drone drone = new Drone(1, new Coordinate(0, 0), new Coordinate(3, 4), 60);
		DroneStatus expected = DroneStatus.EN_ROUTE;
		assertEquals(expected, drone.status());
		
	}
	
	@Test
	void etaAtStartIsFiveMinutes() {
		Drone drone = new Drone(2, new Coordinate(0, 0), new Coordinate(3, 4), 60);
		int expected = drone.etaMinutes();
		assertEquals(5, expected);
	}
	
	@Test
	void etaRoundUp() {
		Drone drone = new Drone(2, new Coordinate(0, 0), new Coordinate(0, 2.3), 60);
		int actual = drone.etaMinutes();
		assertEquals(3, actual);
	}
	
	@Test
	void advanceNeverOvershootsDestination() {
		Drone drone = new Drone(3, new Coordinate(0, 0), new Coordinate(0, 2.3), 60);
		drone.advance(5);
		Coordinate expected = new Coordinate(0, 2.3);
		assertEquals(expected, drone.position());
	}
	
	@Test
	void advanceOneMinuteMovesTowardsDestination() {
		Drone drone = new Drone(1, new Coordinate(0, 0), new Coordinate(3, 4), 60);
		drone.advance(1);
		assertEquals(0.6, drone.position().x(), 1e-9);
		assertEquals(0.8, drone.position().y(), 1e-9);
		
	}
	
	@Test
	void etaDecreasesAfterAdvancing() {
		Drone drone = new Drone(1, new Coordinate(0, 0), new Coordinate(3, 4), 60);
		drone.advance(1);
		assertEquals(4, drone.etaMinutes());
	}
	
	
	@Test
	void advanceFiveMinutesReachesDestination() {
		Drone drone = new Drone(1, new Coordinate(0, 0), new Coordinate(3, 4), 60);
		drone.advance(5);
		Coordinate expected = new Coordinate(3 ,4);
		assertEquals(expected, drone.position());
		
	}
	
}
