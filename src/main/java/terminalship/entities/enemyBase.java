package terminalship.entities;

public class EnemyBase {
	
	private final int id;
	private Coordinate position ;
	private AaDefences aaDefences;
	private BaseSize baseSize;
	private boolean alert = false;
	
	//defenceActive
	
	//if drone.position() inside enemyBase.radious() -> alert == true;
	//{
		//if(baseSize.SMALL && alert == true) { aimingMethod(TimeTakenToAimToTarget = 40)} else(alert == false) { aimingMethod(tttt = 55}
		//if(baseSize.MEDIUM && alert == true) { aimingMethod(TimeTakenToAimToTarget = 25) else(alert == false) { aimingMethod(tttt = 35}
		//if(baseSize.BIG && alert == true) { aimingMethod(TimeTakenToAimToTarget = 15) else(alert == false) { aimingMethod(tttt = 25}
		//if(baseSize.ENORMOUS && alert == true) { aimingMethod(TimeTakenToAimToTarget = 5) else(alert == false) { aimingMethod(tttt = 15}
	//} 


	public EnemyBase(int id, Coordinate position, AaDefences aaDefences, BaseSize baseSize, boolean alert) {
		this.id = id;
		this.position = position;
		this.aaDefences = aaDefences;
		this.baseSize = baseSize;
		this.alert = alert;
	}
	
	public boolean isInside(Coordinate point) {
		
		double dx = this.position.x() - point.x();
		double dy = this.position.y() - point.y();
		return (dx * dx + dy * dy) <= (this.baseSize.getRadiusKm() * this.baseSize.getRadiusKm());
	}
	
	
	
	public int defenceActive(Coordinate dronePosition) {
		
		int timeExpected = 0;
		
		if(isInside(dronePosition)) {
			
			timeExpected = aimingTime();
			this.alert = true;
			
		}		
		return timeExpected;

	}

	public int aimingTime() {
		int totalTime = this.baseSize.aimingMinutes(this.alert);
		return totalTime;
	}


	public Coordinate getPosition() {
		return position;
	}



	
	
	
}
