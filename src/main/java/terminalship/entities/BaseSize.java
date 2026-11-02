package terminalship.entities;

public enum BaseSize {

	SMALL(5, 40, 55), //5km radius
	MEDIUM(10, 25, 35), //10km radius 
	BIG(25, 15, 25), //25km radius
	ENORMOUS(40, 5, 15); //40km radius
	
	private final double radiusKm;
	private final int aimAlertMinutes;
	private final int aimIdleMinutes;
	
	BaseSize(double radiusKm, int aimAlertMinutes, int aimIdleMinutes){
		this.radiusKm = radiusKm;
		this.aimAlertMinutes = aimAlertMinutes;
		this.aimIdleMinutes = aimIdleMinutes;
	}
	
}
