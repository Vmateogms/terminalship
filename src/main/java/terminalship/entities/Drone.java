package terminalship.entities;

import java.util.ArrayList;
import java.util.List;

public class Drone implements Mobile {
	
	private final int id;
	private Coordinate position;
	private List<Coordinate> destination;
	private final List<Coordinate> route;
	private int indexNextRouteMarker;
	private final double speedKmh;
	private DroneStatus status;
	
	public Drone(int id,Coordinate position, Coordinate destination, double speedKmh) {
		this(id, position, List.of(destination), speedKmh);
	}
	
	public Drone(int id, Coordinate position, List<Coordinate> route, double speedKmh) {
		if(route.size() == 0 || route.size() < 0) {
			throw new IllegalArgumentException("Route is 0 or less");
		}
		this.id = id;
		this.position = position;
		this.route = List.copyOf(route);
		this.speedKmh = speedKmh;
		this.status = DroneStatus.EN_ROUTE;
	}

	@Override
	public void advance(int minutes) {
		if(this.status != DroneStatus.EN_ROUTE) {
			return;
		}
		double budget = speedKmh * minutes / 60.0;
		while(budget > 0 && indexNextRouteMarker < route.size()) {
			Coordinate next = route.get(indexNextRouteMarker);
			double dist = position.distanceTo(next);
			if(budget >= dist) {
				position = next;
				budget -= dist;
				indexNextRouteMarker++;
			} else {
			double fraction = budget/dist;
			double x = position.x() + (next.x() - position.x()) * fraction;
			double y = position.y() + (next.y() - position.y()) * fraction;
			Coordinate newCor = new Coordinate(x, y);
			position = newCor;
			return;
  			}
		}

		
	}

	public int etaMinutes() {
		
		double left = 0;
		Coordinate from = position;
		for(int i = indexNextRouteMarker; i < route.size(); i++) {
			left += from.distanceTo(route.get(i));
			from = route.get(i);
		}
		
		double veloc = speedKmh/60.0;
		double mins = left/veloc;
		double end = Math.ceil(mins);
		int ret = (int) end;
		return ret;
	}

	public void markImpacted() {
		this.status = DroneStatus.IMPACTED;
	}
	
	public void markLostComms() {
		this.status = DroneStatus.LOST_COMMS;
	}
	
	@Override
	public double speedKmh() {
		return speedKmh;
	}

	@Override
	public Coordinate position() {
		return position;
	}

	public Coordinate destination() {
	    return route.get(route.size() - 1);
	}
	
	public List<Coordinate> route() {
		return route;
	}
	
	
	
	
	public DroneStatus status() {
		return status;
	}
	
	public int id() {
		return id;
	}
	
}
