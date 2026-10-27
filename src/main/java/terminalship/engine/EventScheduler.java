package terminalship.engine;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import terminalship.event.GameEvent;

public class EventScheduler {
	
	private final Queue<ScheduledEvent> queue = new PriorityQueue<>();
	
	public void schedule(GameTime when, GameEvent event) {
		ScheduledEvent scheduledEvent = new ScheduledEvent(when, event);
		queue.add(scheduledEvent);
	}
	
	public List<GameEvent> popDue(GameTime now){
		List<GameEvent> due = new ArrayList<>();
		while(!queue.isEmpty() && queue.peek().when().compareTo(now) <= 0) {
			ScheduledEvent next = queue.poll();
			due.add(next.event());
		}
		return due;
	}

}
