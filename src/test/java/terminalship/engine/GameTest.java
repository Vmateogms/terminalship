package terminalship.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import terminalship.entities.Coordinate;
import terminalship.entities.Drone;
import terminalship.entities.DroneStatus;
import terminalship.entities.Ship;
import terminalship.entities.Vessel;
import terminalship.entities.VesselKind;
import terminalship.view.GameSnapshot;
import terminalship.view.RadarContact;

class GameTest {

	@Test
	void droneImpactExactlyAtItsEta() {
		Ship ship = new Ship( new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		
		int id = game.launchDrone(new Coordinate(3, 4), 60);
		Drone drone = game.drone(id);
		
		for(int i = 0; i < 4; i++) {
			game.tick();
		}
		
		assertEquals(DroneStatus.EN_ROUTE, drone.status());
		game.tick();
		assertEquals(DroneStatus.IMPACTED, drone.status() ); 
	}
	
	@Test
	void tickMovesDroneTowardsDestination() {
		Ship ship = new Ship(new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		
		int id = game.launchDrone(new Coordinate(3, 4), 60);
		Drone drone = game.drone(id);
		game.tick();
		Coordinate expected = new Coordinate(0.6 , 0.8); 
		assertEquals(expected.x(), drone.position().x(), 1e-9);
		assertEquals(expected.y(), drone.position().y(), 1e-9);
		
	}
	
	@Test
	void twoDronesImpactIndependently() {
		Ship ship = new Ship(new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		
		int id1 = game.launchDrone(new Coordinate(3, 4), 60);
		int id2 = game.launchDrone(new Coordinate(0, 2), 60);
		Drone drone = game.drone(id1);
		Drone drone2 = game.drone(id2);
		game.tick();
		game.tick();
		assertEquals(DroneStatus.EN_ROUTE, drone.status());
		assertEquals(DroneStatus.IMPACTED, drone2.status());
	}
	
	@Test
	void snapshotContainsLaunchedDrone() {
		Ship ship = new Ship(new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		
		int id = game.launchDrone(new Coordinate(3, 4), 60);
		GameSnapshot gS = game.snapshot();
		assertEquals(0, gS.currentMinute());
		
		
	}
	
	@Test
	void unidentifiedVesselShowsAsUnknown() {
		Ship ship = new Ship(new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		Vessel vessel = new Vessel(1, new Coordinate(3, 4), 60 , 90 , VesselKind.CARGO);
		game.addVessel(vessel);
		GameSnapshot snapshot = game.snapshot();
		RadarContact contact = snapshot.contacts().get(0);
		assertEquals(1, snapshot.contacts().size());
		assertEquals(VesselKind.UNKNOWN , contact.kind());
		assertEquals(90 , contact.speedKmh());
		assertEquals(60, contact.headingDegrees());
	}
	
	@Test
	void investigatedVesselIsIdentifiedAfterFiveMinutes() {
		Ship ship = new Ship(new Coordinate(0 , 0)) ;
		Game game = new Game(ship);
		Vessel vessel = new Vessel(7, new Coordinate(3, 4), 60 , 90 , VesselKind.CARGO);
		game.addVessel(vessel);
		game.investigate(7);
		game.tick();
		game.tick();
		game.tick();
		game.tick();
		GameSnapshot snapshot = game.snapshot();
		assertEquals(VesselKind.UNKNOWN, snapshot.contacts().get(0).kind());
		game.tick();
		GameSnapshot snapshot2 = game.snapshot();
		assertEquals(VesselKind.CARGO, snapshot2.contacts().get(0).kind());
	}

}
