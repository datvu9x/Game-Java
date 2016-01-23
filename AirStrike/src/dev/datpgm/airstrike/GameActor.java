package dev.datpgm.airstrike;

import java.awt.Image;
import java.awt.event.KeyListener;

public abstract class GameActor implements GameAction, KeyListener {

	protected Image mImage;
	protected int mWidth;
	protected int mHeight;
	protected float mPositionX;
	protected float mPositionY;
	protected float mDeltaX;
	protected float mDeltaY;
	protected boolean mAlive;

	public Image getImage() {
		return mImage;
	}

	public void setImage(Image mImage) {
		this.mImage = mImage;
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int mWidth) {
		this.mWidth = mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}

	public float getPositionX() {
		return mPositionX;
	}

	public void setPositionX(float mPositionX) {
		this.mPositionX = mPositionX;
	}

	public float getPositionY() {
		return mPositionY;
	}

	public void setPositionY(float mPositionY) {
		this.mPositionY = mPositionY;
	}

	public float getDeltaX() {
		return mDeltaX;
	}

	public void setDeltaX(float mDeltaX) {
		this.mDeltaX = mDeltaX;
	}

	public float getDeltaY() {
		return mDeltaY;
	}

	public void setDeltaY(float mDeltaY) {
		this.mDeltaY = mDeltaY;
	}

	public boolean isAlive() {
		return mAlive;
	}

	public void setAlive(boolean mAlive) {
		this.mAlive = mAlive;
	}

}
