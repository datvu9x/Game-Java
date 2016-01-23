package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class BulletOfEnemy extends GameActor {

	private static final float SPEED = 8.0f;
	public static final float PERCENT = 0.6f;

//	private Random mRandom;
//	private int number;

	public BulletOfEnemy(float x, float y) {
		mPositionX = x;
		mPositionY = y;
		init();
	}

	@Override
	public void init() {
//		mRandom = new Random();
//		number = mRandom.nextInt(2);
		mImage = GameMain.mImageLibrary.get(ImageLibrary.FIRE_BOSS + 0);
		mWidth = 36;
		mHeight = 75;
		mDeltaY = SPEED;
		mAlive = true;

	}

	@Override
	public void update() {

		mPositionY += mDeltaY;

		if (mPositionY >= GamePanel.HEIGHT) {
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
