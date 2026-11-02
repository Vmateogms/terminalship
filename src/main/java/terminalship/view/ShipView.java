package terminalship.view;

import terminalship.entities.Coordinate;
import terminalship.entities.Ship;


public record ShipView(Coordinate position) {

	public static ShipView from(Ship ship) {
		return new ShipView(ship.position());
	}
	
}
