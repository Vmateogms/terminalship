package terminalship.view;

import java.util.List;

import terminalship.entities.Coordinate;
import terminalship.entities.Drone;
import terminalship.entities.DroneStatus;

public record DroneView(int id, Coordinate position, Coordinate destination, List<Coordinate> route, DroneStatus status, int etaMinutes) {
	
	public static DroneView from(Drone drone) {
		return new DroneView(drone.id(), drone.position(), drone.destination(), drone.route(), drone.status(), drone.etaMinutes());
	}

}
