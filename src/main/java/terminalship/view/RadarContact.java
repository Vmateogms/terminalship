package terminalship.view;

import terminalship.entities.Coordinate;
import terminalship.entities.Vessel;
import terminalship.entities.VesselKind;

public record RadarContact(int id, Coordinate position, double headingDegrees, double speedKmh, VesselKind kind) {

	public static RadarContact from(Vessel vessel, boolean identifiedByPlayer) {
		return new RadarContact(
				vessel.id(), vessel.position(), vessel.headingDegrees() ,vessel.speedKmh(), identifiedByPlayer ? vessel.kind() : VesselKind.UNKNOWN );
				
	}
	
}
