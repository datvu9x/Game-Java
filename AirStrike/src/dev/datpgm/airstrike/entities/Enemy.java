package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Enemy extends GameActor {

	private boolean mThrowing;
	private Random mRandom;
	private float mPositionToThrowShoot;
	private boolean mLives;
	private int number;

	public Enemy(float x, float y, float dx, float dy) {
		mPositionX = x;
		mPositionY = y;
		mDeltaX = dx;
		mDeltaY = dy;
		init();
	}

	public void check(Player mPlayer, ArrayList<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			if (bullet.isAlive()) {
				float x = bullet.getPositionX();
				float y = bullet.getPositionY();
				if (x >= mPositionX - mWidth / 2
						&& x <= mPositionX + mWidth / 2
						&& y >= mPositionY - mHeight / 2
						&& y <= mPositionY + mHeight / 2) {
					bullet.setAlive(false);
					mAlive = false;
				}
			}
		}
	}

	@Override
	public void init() {
		mRandom = new Random();
		number = mRandom.nextInt(ImageLibrary.NUMBER_OF_ENEMY) + 1;
		mImage = GameMain.mImageLibrary.get(ImageLibrary.ENEMY + number);
		mWidth = 128;
		mHeight = 32;
		mAlive = true;
		mLives = true;
		mThrowing = false;
		mPositionToThrowShoot = mRandom.nextFloat() * GamePanel.WIDTH / 3;
	}

	@Override
	public void update() {

		mPositionX += mDeltaX / 2;
		mPositionY += mDeltaY / 2;

		if (mPositionToThrowShoot >= mPositionX
				&& mPositionToThrowShoot <= mPositionX + mDeltaX) {
			mThrowing = true;
		}

		if (mPositionX <= 0 || mPositionX >= GamePanel.WIDTH) {
			mDeltaX *= -1;
			mDeltaY /= 8;
		}
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

	public boolean isThrowing() {
		return mThrowing;
	}

	public void setThrowing(boolean mThrowing) {
		this.mThrowing = mThrowing;
	}

	public boolean isLives() {
		return mLives;
	}

	public void setLives(boolean mLives) {
		this.mLives = mLives;
	}

}
