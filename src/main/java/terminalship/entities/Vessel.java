package terminalship.entities;



public class Vessel implements Mobile{

	private final int id;
	private final VesselKind kind;
	private Coordinate position;
	private final double speedKmh;
	private final double headingDegrees;
	
	
	public Vessel(int id, Coordinate position, double headingDegrees, double speedKmh, VesselKind kind) {
		this.id = id;
		this.kind = kind;
		this.position = position;
		this.speedKmh = speedKmh;
		this.headingDegrees = headingDegrees;
	}


	public int id() {
		return id;
	}

	public VesselKind kind() {
		return kind;
	}


	@Override
	public void advance(int minutes) {
		
		
		double travelled = speedKmh * minutes / 60.0;
		double headingRadians = Math.toRadians(headingDegrees);
		double newX = position.x() + travelled * Math.sin(headingRadians);
		double newY = position.y() + travelled * Math.cos(headingRadians);
		Coordinate newValue = new Coordinate(newX, newY);
		this.position = newValue;
		
	}
	
	public double headingDegrees() {
		return headingDegrees;
	}



	@Override
	public Coordinate position() {
		return position;
	}



	@Override
	public double speedKmh() {
		return speedKmh;
	}







	

}
