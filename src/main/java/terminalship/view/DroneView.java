package terminalship.view;

import terminalship.entities.Coordinate;
import terminalship.entities.Drone;
import terminalship.entities.DroneStatus;

public record DroneView(int id, Coordinate position, Coordinate destination, DroneStatus status, int etaMinutes) {
	
	public static DroneView from(Drone drone) {
		return new DroneView(drone.id(), drone.position(), drone.destination(), drone.status(), drone.etaMinutes());
	}

}
