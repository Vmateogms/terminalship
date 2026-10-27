package terminalship.entities;

public interface Mobile {

	Coordinate position(); 
	double speedKmh();
	void advance(int minutes);
	
}
