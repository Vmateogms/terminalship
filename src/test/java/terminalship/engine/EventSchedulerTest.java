package terminalship.engine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import terminalship.event.DroneImpacted;
import terminalship.event.DroneLost;
import terminalship.event.GameEvent;

class EventSchedulerTest {

	@Test
	void droneImpactedTest() {
		GameTime five = new GameTime(5);
		DroneImpacted dI = new DroneImpacted(1);
		EventScheduler eS = new EventScheduler();
		
		eS.schedule(five, dI);
		List<GameEvent> res = eS.popDue(five);
		assertEquals(List.of(dI) , res);
	}

	@Test
	void onlyResFromCalledEvent() {
		GameTime three = new GameTime(3);
		GameTime twelve = new GameTime(12);
		GameTime seven = new GameTime(7);
		DroneImpacted dI = new DroneImpacted(1);
		DroneLost dL = new DroneLost(2);
		EventScheduler eS = new EventScheduler();
		eS.schedule(three, dL);
		eS.schedule(twelve, dI);
		
		List<GameEvent> actual7 = eS.popDue(seven);
		assertEquals(List.of(dL) , actual7);
		List<GameEvent> actual12 = eS.popDue(twelve);
		assertEquals(List.of(dI) , actual12);
		
	}
	
}
