package dev.datpgm.airstrike.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Player extends GameActor {

	private static final float SPEED = 8.0f;
	private float MOVE_SPEED = 5.0f;
	private boolean mLives;
	private boolean mBonus_Coin;
	private boolean mBonus_Gift;

	private static Background bg1 = Sky.getBg1();
	private static Background bg2 = Sky.getBg2();

	public Player() {
		init();
	}

	public void check(Player mPlayer, ArrayList<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (enemy.isLives()) {
				float x = enemy.getPositionX();
				float y = enemy.getPositionY();
				if (x >= mPositionX - mWidth / 2
						&& x <= mPositionX + mWidth / 2
						&& y >= mPositionY - mHeight / 2
						&& y <= mPositionY + mHeight / 2) {
					enemy.setLives(false);
					mAlive = false;
				}
			}
		}
	}

	public void checkLife(Player mPlayer,
			ArrayList<BulletOfEnemy> bulletOfEnemies) {
		for (BulletOfEnemy bulletOfEnemy : bulletOfEnemies) {
			if (bulletOfEnemy.isAlive()) {
				float x = bulletOfEnemy.getPositionX();
				float y = bulletOfEnemy.getPositionY();
				if (x >= mPositionX - mWidth / 2
						&& x <= mPositionX + mWidth / 2
						&& y >= mPositionY - mHeight / 2
						&& y <= mPositionY + mHeight / 2) {
					bulletOfEnemy.setAlive(false);
					mLives = false;
				}
			}
		}
	}

	public void checkCoin(Player mPlayer, ArrayList<Coin> coins) {
		for (Coin coin : coins) {
			if (coin.isAlive()) {
				float x = coin.getPositionX();
				float y = coin.getPositionY();
				if (x >= mPositionX - mWidth / 2
						&& x <= mPositionX + mWidth / 2
						&& y >= mPositionY - mHeight / 2
						&& y <= mPositionY + mHeight / 2) {
					coin.setAlive(false);
					mBonus_Coin = true;
				}
			}
		}
	}

	public void checkGift(Player mPlayer, ArrayList<Gift> gifts) {
		for (Gift gift : gifts) {
			if (gift.isAlive()) {
				float x = gift.getPositionX();
				float y = gift.getPositionY();
				if (x >= mPositionX - mWidth / 2
						&& x <= mPositionX + mWidth / 2
						&& y >= mPositionY - mHeight / 2
						&& y <= mPositionY + mHeight / 2) {
					gift.setAlive(false);
					mBonus_Gift = true;
				}
			}
		}
	}

	@Override
	public void init() {
		mImage = GameMain.mImageLibrary.get(ImageLibrary.PLAYER);
		mWidth = 141;
		mHeight = 89;
		mPositionX = 240;
		mPositionY = 700;
		mAlive = true;
		mLives = true;
		mBonus_Coin = false;
		mBonus_Gift = false;
	}

	@Override
	public void update() {
		if (mPositionX + mDeltaX <= 0
				|| mPositionX + mDeltaX >= GamePanel.WIDTH) {
			mDeltaX = 0;
		} else {
			mPositionX += mDeltaX;
			mPositionY += mDeltaY;
		}

		bg1.setSpeedY(MOVE_SPEED);
		bg2.setSpeedY(MOVE_SPEED);
	}

	@Override
	public void render(Graphics2D graphics2d) {
		int x = Math.round(mPositionX - mWidth / 2);
		int y = Math.round(mPositionY - mHeight / 2);
		graphics2d.drawImage(mImage, x, y, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			mDeltaX = -SPEED;
			break;
		case KeyEvent.VK_RIGHT:
			mDeltaX = SPEED;
			break;
		case KeyEvent.VK_UP:
			mDeltaY = -SPEED;
			break;
		case KeyEvent.VK_DOWN:
			mDeltaY = SPEED;
			break;
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			mDeltaX = 0;
			break;
		case KeyEvent.VK_RIGHT:
			mDeltaX = 0;
			break;
		case KeyEvent.VK_UP:
			mDeltaY = 0;
			break;
		case KeyEvent.VK_DOWN:
			mDeltaY = 0;
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isLives() {
		return mLives;
	}

	public void setLives(boolean mLives) {
		this.mLives = mLives;
	}

	public boolean isBonus_Coin() {
		return mBonus_Coin;
	}

	public void setBonus_Coin(boolean mBonus_Coin) {
		this.mBonus_Coin = mBonus_Coin;
	}

	public boolean isBonus_Gift() {
		return mBonus_Gift;
	}

	public void setBonus_Gift(boolean mBonus_Gift) {
		this.mBonus_Gift = mBonus_Gift;
	}

}
