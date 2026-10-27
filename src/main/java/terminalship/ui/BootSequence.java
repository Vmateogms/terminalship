package terminalship.ui;

import com.googlecode.lanterna.screen.Screen;

public class BootSequence {

	public void play(Screen screen) throws Exception {
		
		int fila = 2;
		
		
		
		playDots(screen, "", 10000);
		
		String activate = "ACTIVATING SYSTEM";
		System.out.println("Columnas: " + screen.getTerminalSize().getColumns());
		int colActivation = center(screen, activate);
		long start = System.currentTimeMillis();
		boolean visible = true;
		while (System.currentTimeMillis() - start < 10000) {
			screen.clear();
			if(visible) {
				screen.newTextGraphics().putString(colActivation, fila, activate);			
				}
		screen.refresh();
		visible = !visible;
		Thread.sleep(500);
		}
		
		String standBy = "STAND BY";
		int colStandBy = center(screen, standBy);
		screen.clear();
		screen.newTextGraphics().putString(colStandBy, fila, standBy);
		screen.refresh();
		Thread.sleep(5000);
		
		playDots(screen, "SYSTEM LOADED", 5000);
	}
	
	private int center(Screen screen, String text) {
		screen.doResizeIfNecessary();
		int width = screen.getTerminalSize().getColumns();
		return (width - text.length()) / 2;
	}
	
	private void playDots(Screen screen, String prefijo, long duracionMs) throws Exception {
	    long start = System.currentTimeMillis();
	    int puntos = 0;
	    while (System.currentTimeMillis() - start < duracionMs) {
	        puntos = (puntos % 4) + 1;
	        String texto = prefijo + ".".repeat(puntos);
	        screen.clear();
	        screen.newTextGraphics().putString(2, 2, texto);
	        screen.refresh();
	        Thread.sleep(200);
	    }
	}
	

	
}
