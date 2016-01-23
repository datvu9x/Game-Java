package dev.datpgm.airstrike;

import java.awt.Graphics2D;

public interface GameAction {
	void init();

	void update();

	void render(Graphics2D graphics2d);
}
