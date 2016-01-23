package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Life extends GameActor {

	

	private int mLevel;

	public Life(int index) {
		mPositionX = GamePanel.WIDTH - (3 - index) * 48;
		init();
	}

	@Override
	public void init() {
		mImage = GameMain.mImageLibrary.get(ImageLibrary.LIFE + mLevel);
		mWidth = 32;
		mHeight = 32;
		mPositionY = mHeight;
		mLevel = 0;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D graphics2d) {
		int x = Math.round(mPositionX - mWidth / 2);
		int y = Math.round(mPositionY - mHeight / 2);
		graphics2d.drawImage(mImage, x, y, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public int getmLevel() {
		return mLevel;
	}

	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
		mImage = GameMain.mImageLibrary.get(ImageLibrary.LIFE + mLevel);
	}
	
	

}
