package terminalship.entities;

public class enemyBase {
	
	private int id;
	Coordinate Position = new Coordinate(30, 60);
	private AaDefences aaDefences;
	private BaseSize baseSize;
	private double radious = 10; //as km
	private boolean alert = false;
	
	//defenceActive
	
	//if drone.position() inside enemyBase.radious() -> alert == true;
	//{
		//if(baseSize.SMALL && alert == true) { aimingMethod(TimeTakenToAimToTarget = 40)} else(alert == false) { aimingMethod(tttt = 55}
		//if(baseSize.MEDIUM && alert == true) { aimingMethod(TimeTakenToAimToTarget = 25) else(alert == false) { aimingMethod(tttt = 35}
		//if(baseSize.BIG && alert == true) { aimingMethod(TimeTakenToAimToTarget = 15) else(alert == false) { aimingMethod(tttt = 25}
		//if(baseSize.ENORMOUS && alert == true) { aimingMethod(TimeTakenToAimToTarget = 5) else(alert == false) { aimingMethod(tttt = 15}
	//} 
}
