package dev.datpgm.airstrike.entities;

public class Background {

	private int bgX, bgY;
	private float speedY;
	final int HEIGHT_BG = 800;

	public Background(int x, int y) {
		bgX = x;
		bgY = y;
		speedY = 1.0f;
	}

	public void update() {
		bgY += speedY;
		if (bgY >= HEIGHT_BG) {
			bgY -= HEIGHT_BG * 2;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
}
