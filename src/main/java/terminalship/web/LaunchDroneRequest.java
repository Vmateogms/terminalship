package terminalship.web;

import java.util.List;

import terminalship.entities.Coordinate;

public record LaunchDroneRequest(List<Coordinate> route, double speedKmh) {

}
