package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Explosion extends GameActor {

	private int mIndex;
	private long mTick;

	public Explosion(float x, float y) {
		mPositionX = x;
		mPositionY = y;
		init();
	}

	@Override
	public void init() {
		mImage = GameMain.mImageLibrary.get(ImageLibrary.EXPLOSION);
		mWidth = 148;
		mHeight = 125;
		mAlive = true;
		mIndex = 0;
		mTick = System.currentTimeMillis();
	}

	@Override
	public void update() {

		if (System.currentTimeMillis() - mTick >= 50) {
			mTick = System.currentTimeMillis();
			mIndex++;
			if (mIndex == 7) {
				mAlive = false;
			} else {
				mImage = GameMain.mImageLibrary.get(ImageLibrary.EXPLOSION + mIndex);
			}
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
