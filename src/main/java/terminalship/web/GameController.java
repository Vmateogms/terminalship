package terminalship.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import terminalship.engine.Game;
import terminalship.engine.GameFactory;
import terminalship.view.GameSnapshot;

@RestController
@RequestMapping("/api")
public class GameController {

	
	private final Game game = GameFactory.createDemoGame(); 
	
	@GetMapping("/state")
	public synchronized GameSnapshot status() {
		
		return game.snapshot();
	}
	
	@PostMapping("/tick")
	public synchronized GameSnapshot tick() {
		game.tick();
		return game.snapshot();		
	}
	
	@PostMapping("/drones")
	public synchronized int launchDrone(@RequestBody LaunchDroneRequest launchDroneRequest) {
		int idLaunched = game.launchDrone(launchDroneRequest.route(), launchDroneRequest.speedKmh());
		return idLaunched;
	}
	
	@PostMapping("/vessels/{id}/investigate")
	public synchronized void investigate(@PathVariable int id) {
		game.investigate(id);
	}
	
	
}
