package terminalship.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VesselTest {

	//north
	@Test
	void advanceHeadingNorthIncreaseY() {
		Vessel vessel = new Vessel(1, new Coordinate(0, 0), 0, 60, VesselKind.CARGO);
		vessel.advance(1);
		assertEquals(0, vessel.position().x(), 1e-9);
		assertEquals(1, vessel.position().y(), 1e-9);
	}
	//east
	@Test
	void advanceHeadingEastIncreaseX() {
		Vessel vessel = new Vessel(2, new Coordinate(0, 0), 90, 60, VesselKind.CARGO);
		vessel.advance(1);
		assertEquals(1, vessel.position().x(), 1e-9);
		assertEquals(0, vessel.position().y(), 1e-9);
	}
	
	@Test
	void advanceHeadingNorthEastMovesDiagonally() {
		Vessel vessel = new Vessel(1, new Coordinate(0, 0), 45, 60, VesselKind.CARGO);
		vessel.advance(1);
		assertEquals(Math.sqrt(2)/2, vessel.position().x(), 1e-9);
		assertEquals(Math.sqrt(2)/2, vessel.position().y(), 1e-9);
		
	}
	
	@Test
	void twoOneMinuteStepsEqualOneTwoMinuteStep() {
		Vessel vessel = new Vessel(1, new Coordinate(0, 0), 45, 60, VesselKind.CARGO);
		Vessel vesselClone = new Vessel(2, new Coordinate(0, 0), 45, 60, VesselKind.CARGO);
		vessel.advance(1);
		vessel.advance(1);
		vesselClone.advance(2);
		
		assertEquals(vesselClone.position().x(), vessel.position().x(), 1e-9);
		assertEquals(vesselClone.position().y(), vessel.position().y(), 1e-9);
	}


	
}
