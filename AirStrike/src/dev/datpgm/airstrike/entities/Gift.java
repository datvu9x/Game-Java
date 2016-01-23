package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Gift extends GameActor {

	public static final float SPEED_PERCENT = 0.3f;
	public static final float SPEED = 8.0f;

	private int mLevel;
	private Random mRandom;

	public Gift(int gameLevel, float x, float y) {
		mPositionX = x;
		mPositionY = y;
		mDeltaY = (gameLevel + SPEED) * SPEED_PERCENT;
		init();
	}

	@Override
	public void init() {
		mRandom = new Random();
		mLevel = mRandom.nextInt(ImageLibrary.NUMBER_OF_COINS);
		mImage = GameMain.mImageLibrary.get(ImageLibrary.GIFT + mLevel);
		mWidth = 35;
		mHeight = 35;
		mAlive = true;
	}

	@Override
	public void update() {
		if (mPositionY <= GamePanel.HEIGHT) {
			mPositionY += mDeltaY;
		} else {
			mAlive = false;
		}
	}

	@Override
	public void render(Graphics2D graphics2d) {
		int x = Math.round(mPositionX - mWidth / 2);
		int y = Math.round(mPositionY - mHeight / 2);
		graphics2d.drawImage(mImage, x, y, null);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public int getLevel() {
		return mLevel;
	}

	public void setLevel(int mLevel) {
		this.mLevel = mLevel;
	}

}
