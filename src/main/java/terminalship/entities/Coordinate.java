package terminalship.entities;

public record Coordinate(double x, double y) {

	public double distanceTo(Coordinate other) {
		double xDiff = this.x - other.x();
		double yDiff = this.y - other.y();
		double diffSum = (xDiff * xDiff) + (yDiff * yDiff);
		double res = Math.sqrt(diffSum);		
		return res;
	}
	
}
