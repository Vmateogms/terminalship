package terminalship.ui;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Panel {

	private String title;
	private int x;
	private int y;
	private int width;
	private int height;
	private List<String> lines = new ArrayList<>();

	public Panel(String title, int x, int y, int width, int height) {
		this.title = title;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void addLine(String line) {
		lines.add(line);
	}

	public void clear() {
		lines.clear();
	}

	public void draw(Screen screen) {
		// Lápiz restringido al rectángulo del panel: (0,0) es la esquina del panel
		TextGraphics g = screen.newTextGraphics()
				.newTextGraphics(new TerminalPosition(x, y), new TerminalSize(width, height));
		g.setForegroundColor(TextColor.ANSI.GREEN);

		g.fillRectangle(TerminalPosition.TOP_LEFT_CORNER, g.getSize(), ' ');
		g.drawRectangle(TerminalPosition.TOP_LEFT_CORNER, g.getSize(), '#');
		g.putString(2, 0, " " + title + " ");

		// La primera y la última fila son borde: las útiles son height - 2.
		// Si hay más líneas de las que caben, se muestran solo las últimas.
		int filasUtiles = height - 2;
		int inicio = Math.max(0, lines.size() - filasUtiles);
		for (int i = inicio; i < lines.size(); i++) {
			g.putString(2, 1 + (i - inicio), lines.get(i));
		}
	}
}