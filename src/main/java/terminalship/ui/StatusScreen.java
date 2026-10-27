package terminalship.ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import terminalship.entities.Ship;
import terminalship.entities.Weapon;

public class StatusScreen {
	
	public void draw(Screen screen, Ship ship) throws Exception {
		
		screen.clear();
		TextGraphics g = screen.newTextGraphics();
		g.setForegroundColor(TextColor.ANSI.GREEN);
		
		g.putString(2, 2, "STATUS		: " + "OK");
		g.putString(2, 3, "CONNECTION	: " + "ONLINE");
		g.putString(2, 5, "WEAPONS AVAILABLE"); 
		
		int fila = 6;
		for(Weapon w : ship.getWeapons()) {
			g.putString(2, fila, "WEAPON	: " + w.getName());
			fila++;
			g.putString(2, fila, "AMMO      : " + w.getQuantity());
			fila++;
		}
		
		screen.refresh();
		
	}

}
