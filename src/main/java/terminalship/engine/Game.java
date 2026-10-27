package terminalship.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import terminalship.entities.Coordinate;
import terminalship.entities.Drone;
import terminalship.entities.Ship;
import terminalship.entities.Vessel;
import terminalship.event.DroneImpacted;
import terminalship.event.GameEvent;
import terminalship.event.InvestigationCompleted;
import terminalship.view.DroneView;
import terminalship.view.GameSnapshot;
import terminalship.view.RadarContact;
import terminalship.view.ShipView;

public class Game {
	
	GameClock clock = new GameClock(new GameTime(0));
	EventScheduler eventScheduler = new EventScheduler();
	Ship ship;
	Map<Integer, Drone> drones = new HashMap<>();
	List<Vessel> vessels = new ArrayList<>();	
	private final Set<Integer> playerIdentified = new HashSet<>();
	int nextDroneId;
	private static final int INVESTIGATION_MINUTES = 5;
	
	public Game(Ship ship) {
		this.ship = ship;
	}
	
	public int launchDrone(Coordinate destination, double speedKmh) {
		Drone drone = new Drone(nextDroneId, ship.position() , destination, speedKmh);
		nextDroneId++;
		DroneImpacted droneImpacted = new DroneImpacted(drone.id());
		eventScheduler.schedule(clock.now().plus(drone.etaMinutes()), droneImpacted);		
		drones.put(drone.id(), drone);
		return drone.id();
	}
	
	public Drone drone(int id) {
		return drones.get(id);
	}
	
	public void tick() {
		clock.tick();
		
		
		for(Drone drone : drones.values()) {
			drone.advance(1);
		}
		
		for(Vessel vessel : vessels) {
			vessel.advance(1);
		}
		
		List<GameEvent> due = eventScheduler.popDue(clock.now());
		for(GameEvent event : due) {
			apply(event);
		}
		
	}
	
	private void apply(GameEvent event){
		if (event instanceof DroneImpacted impacted) {
			int id = impacted.droneId();
			Drone droneImpact = drones.get(id);
			droneImpact.markImpacted();
		}
		
		if(event instanceof InvestigationCompleted investigationCompleted) {
			int id = investigationCompleted.vesselId();
			playerIdentified.add(id);
		}
		
	}
	
	public GameSnapshot snapshot() {
		List<DroneView> droneViews = new ArrayList<>();
		for(Drone drone : drones.values()) {
			 droneViews.add(DroneView.from(drone));
		}
		List<RadarContact> contacts = new ArrayList<>();
		for(Vessel vessel : vessels) {
			contacts.add(RadarContact.from(vessel, playerIdentified.contains(vessel.id())));
		}
		
		return new GameSnapshot(clock.now().minutes(), ShipView.from(ship), droneViews, contacts);
	}
	
	public void addVessel(Vessel vessel) {
		vessels.add(vessel);
	}
	
	public void investigate(int vesselId) {
		eventScheduler.schedule(clock.now().plus(INVESTIGATION_MINUTES), new InvestigationCompleted(vesselId));	
	}
	
}
