package terminalship.engine;

import terminalship.event.GameEvent;

public record ScheduledEvent(GameTime when, GameEvent event) implements Comparable<ScheduledEvent> {

	@Override
	public int compareTo(ScheduledEvent o) {
		//int res = Integer.compare(this.when, o.when());
 	
		return this.when.compareTo(o.when());
	}

}
