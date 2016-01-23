package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Bullet extends GameActor {

	private static final float SPEED = -15f;
	public static final float PERCENT = 0.3f;

	private Random mRandom;
	private int number;

	public Bullet(float x, float y) {
		mPositionX = x;
		mPositionY = y;
		init();
	}

	@Override
	public void init() {
		mRandom = new Random();
		number = mRandom.nextInt(ImageLibrary.NUMBER_OF_BULLETS);
		mImage = GameMain.mImageLibrary.get(ImageLibrary.BULLET + number);
		mWidth = 50;
		mHeight = 150;
		mDeltaY = SPEED;
		mAlive = true;

	}

	@Override
	public void update() {

		mPositionY += mDeltaY;

		if (mPositionY <= 0) {
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
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
