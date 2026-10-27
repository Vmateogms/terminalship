package terminalship.entities;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	
	private List<Weapon> weapons;
	private final Coordinate position;
	
	
	public Ship(Coordinate position) {
		this.position = position;
		this.weapons = new ArrayList<>();
	}
	
	
	public void addWeapon(Weapon w) {
		weapons.add(w);
	}



	public List<Weapon> getWeapons() {
		return List.copyOf(weapons);
	}


	
	public Coordinate position() {
		return position;
	}



	
	
	
	
	
	
	
	
}
