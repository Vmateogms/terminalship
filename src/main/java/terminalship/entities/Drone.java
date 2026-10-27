package terminalship.entities;

public class Drone implements Mobile {
	
	private final int id;
	private Coordinate position;
	private final Coordinate destination;
	private final double speedKmh;
	private DroneStatus status;
	
	public Drone(int id,Coordinate position, Coordinate destination, double speedKmh) {
		this.id = id;
		this.position = position;
		this.destination = destination;
		this.speedKmh = speedKmh;
		this.status = DroneStatus.EN_ROUTE;
	}

	@Override
	public void advance(int minutes) {
		if(this.status != DroneStatus.EN_ROUTE) {
			return;
		}
		double dis2target = position.distanceTo(destination);
		double travelled = speedKmh * minutes / 60.0;
		if(travelled >= dis2target) {
			this.position = this.destination;
			return;
		}
		double fraction = travelled / dis2target;
		
		double newX = position.x() + (destination.x() - position.x()) * fraction;
		double newY = position.y() + (destination.y() - position.y()) * fraction;
		Coordinate newValue = new Coordinate(newX, newY);
		this.position = newValue;
		
	}

	public int etaMinutes() {
		double left = position.distanceTo(destination);
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
		return destination;
	}
	
	public DroneStatus status() {
		return status;
	}
	
	public int id() {
		return id;
	}
	
}
