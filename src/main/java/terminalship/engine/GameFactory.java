package terminalship.engine;

import terminalship.entities.Coordinate;
import terminalship.entities.Ship;
import terminalship.entities.Vessel;
import terminalship.entities.VesselKind;

public class GameFactory {

	
	public static Game createDemoGame() {
		
		Ship ship = new Ship(new Coordinate(0, 0));
		Game newGame = new Game(ship);
		Vessel vessel1 = new Vessel(1, new Coordinate(-90, 20), 90, 40,  VesselKind.CARGO);
		Vessel vessel2 = new Vessel(2, new Coordinate(40, -90), 350, 35,  VesselKind.CIVILIAN_SHIP);
		Vessel vessel3 = new Vessel(3, new Coordinate(70, 40), 225, 30,  VesselKind.OIL_TANKER);
		Vessel vessel4 = new Vessel(4, new Coordinate(-50, -80), 20, 45,  VesselKind.COAST_GUARD);
		Vessel vessel5 = new Vessel(5, new Coordinate(0, 20), 10, 300,  VesselKind.CIVILIAN_AIRCRAFT);
		Vessel vessel6 = new Vessel(6, new Coordinate(247, -28), 270, 300,  VesselKind.MILITARY_AIRCRAFT);
		Vessel vessel7 = new Vessel(7, new Coordinate(251, -31), 270, 300,  VesselKind.MILITARY_AIRCRAFT);
		Vessel vessel8 = new Vessel(8, new Coordinate(254, -25), 270, 300,  VesselKind.MILITARY_AIRCRAFT);
		
		newGame.addVessel(vessel1);
		newGame.addVessel(vessel2);
		newGame.addVessel(vessel3);
		newGame.addVessel(vessel4);
		newGame.addVessel(vessel5);
		newGame.addVessel(vessel6);
		newGame.addVessel(vessel7);
		newGame.addVessel(vessel8);
		return newGame;
	}
	
}
