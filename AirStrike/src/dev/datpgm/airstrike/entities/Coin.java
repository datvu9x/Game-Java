package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Coin extends GameActor {

	public static final float SPEED_PERCENT = 0.8f;
	public static final float SPEED = 8.0f;
	public static final int[] SCORES = { 100, 200, 500, 1000};

	private int mLevel;
	private int mBonus;
	private Random mRandom;

	public Coin(int gameLevel, float x, float y) {
		mPositionX = x;
		mPositionY = y;
		mDeltaY = (gameLevel + SPEED) * SPEED_PERCENT;
		init();
	}

	@Override
	public void init() {
		mRandom = new Random();
		mLevel = mRandom.nextInt(ImageLibrary.NUMBER_OF_COINS);
		mBonus = SCORES[mLevel];
		mImage = GameMain.mImageLibrary.get(ImageLibrary.COIN + mLevel);
		mWidth = 72;
		mHeight = 72;
		mAlive = true;
	}

	@Override
	public void update() {
		if (mPositionY <= GamePanel.HEIGHT) {
			mPositionY += mDeltaY;
		} else {
			mBonus = 0;
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

	public int getBonus() {
		return mBonus;
	}

	public void setBonus(int mBonus) {
		this.mBonus = mBonus;
	}

}
