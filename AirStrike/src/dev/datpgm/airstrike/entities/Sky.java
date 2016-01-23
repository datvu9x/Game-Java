package dev.datpgm.airstrike.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import dev.datpgm.airstrike.GameAbout;
import dev.datpgm.airstrike.GameActor;
import dev.datpgm.airstrike.GameMain;
import dev.datpgm.airstrike.GamePanel;
import dev.datpgm.airstrike.GameHelp;
import dev.datpgm.airstrike.GameHighScore;
import dev.datpgm.airstrike.audios.AudioLibrary;
import dev.datpgm.airstrike.images.ImageLibrary;

public class Sky extends GameActor {

	public static final int MAX_ALIVES = 3;
	public static final int GOAL = 1000;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 80;

	private Player mPlayer;
	private Boss mBoss;
	private ArrayList<Bullet> mBulletList;
	private ArrayList<BulletOfEnemy> mBulletOfEnemyList;
	private ArrayList<Enemy> mEnemyList;
	private ArrayList<Explosion> mExplosionList;
	private ArrayList<Life> mLifeList;
	private ArrayList<Coin> mCoinList;
	// private ArrayList<Gift> mGiftList;
	private static Background bg1, bg2;
	// private GameHighScore mGameHighScore;

	private int mScore;
	private int mLevel;
	private int mBonus;
	private int mLives;
	private int mGoal;
	private int mMap;
	private boolean mPlaying;
	private boolean mGameOver;
	private boolean mShooting;
	private long mTick;
	private Random mRandom;

	public Sky() {
		init();
	}

	@Override
	public void init() {

		bg1 = new Background(0, 0);
		bg2 = new Background(0, -800);
		mPlayer = new Player();

		mBoss = new Boss(mWidth / 2 - 90, 150, -5, 0);
		mLifeList = new ArrayList<Life>();
		mCoinList = new ArrayList<Coin>();
		// mGiftList = new ArrayList<Gift>();
		mEnemyList = new ArrayList<Enemy>();
		mBulletList = new ArrayList<Bullet>();
		mExplosionList = new ArrayList<Explosion>();
		mBulletOfEnemyList = new ArrayList<BulletOfEnemy>();
		mImage = GameMain.mImageLibrary.get(ImageLibrary.BACKGROUND);
		mWidth = 480;
		mHeight = 800;
		mLevel = 0;
		mScore = 0;
		mBonus = 0;
		mMap = 0;
		mLives = MAX_ALIVES;
		mGoal = GOAL;
		for (int index = 0; index < MAX_ALIVES; index++) {
			mLifeList.add(new Life(index));
		}
		mTick = System.currentTimeMillis();
		mRandom = new Random();
		mShooting = false;
		mPlaying = false;
		mGameOver = false;
		GameMain.mAudioLibrary.get(AudioLibrary.TITLE).loop();
	}

	@Override
	public void update() {
		if (mPlaying && !mGameOver) {
			GameMain.mAudioLibrary.get(AudioLibrary.TITLE).stop();
			mPlayer.update();

			mBoss.update();

			bg1.update();
			bg2.update();

			if (mShooting) {
				mShooting = false;
				GameMain.mAudioLibrary.get(AudioLibrary.BULLET).play();
				mBulletList.add(new Bullet(mPlayer.getPositionX(), mPlayer
						.getPositionY() - 50));
			}

			for (int index = 0; index < mBulletList.size(); index++) {
				Bullet bullet = mBulletList.get(index);
				bullet.update();
				mBoss.checkLive(mBoss, mBulletList);
				if (!mBoss.isAlive()) {
					GameMain.mAudioLibrary.get(AudioLibrary.BOSS + 0).stop();
					GameMain.mAudioLibrary.get(AudioLibrary.EXPLOSION + 0)
							.play();
					mExplosionList.add(new Explosion(mBoss.getPositionX(),
							mBoss.getPositionY()));
					mCoinList.add(new Coin(mLevel, mBoss.getPositionX(), mBoss
							.getPositionY()));
					// mGiftList.add(new Gift(mRandom.nextInt(5), mBoss
					// .getPositionX(), mBoss.getPositionY()));
					mBoss.setPositionY(-200);
					mBoss.setAlive(true);
					mBoss.setLives(100);
				} else {
					GameMain.mAudioLibrary.get(AudioLibrary.WARNING).play();
					GameMain.mAudioLibrary.get(AudioLibrary.BOSS + 0).play();
				}
				if (!bullet.isAlive()) {
					mBulletList.remove(index);
				}
			}

			if (System.currentTimeMillis() - mTick >= 5000) {
				mTick = System.currentTimeMillis();
				for (int i = 0; i < 8; i++) {
					mEnemyList.add(new Enemy(mRandom.nextInt(GamePanel.WIDTH),
							0, -5, 5));
				}
			}

			for (int index = 0; index < mEnemyList.size(); index++) {
				Enemy enemy = mEnemyList.get(index);
				enemy.update();
				GameMain.mAudioLibrary.get(
						AudioLibrary.ENEMY + mRandom.nextInt(2)).play();
				enemy.check(mPlayer, mBulletList);
				mPlayer.check(mPlayer, mEnemyList);
				if (!mPlayer.isAlive()) {
					if (mLives > 0) {
						mLives--;
						mLifeList.get(mLives).setmLevel(1);
						mPlayer.setAlive(true);
					}
				}

				if (!enemy.isAlive()) {
					GameMain.mAudioLibrary.get(AudioLibrary.EXPLOSION + 1)
							.play();
					mEnemyList.remove(index);
					mExplosionList.add(new Explosion(enemy.getPositionX(),
							enemy.getPositionY()));
					mCoinList.add(new Coin(mLevel, enemy.getPositionX(), enemy
							.getPositionY()));
					// mGiftList.add(new Gift(mRandom.nextInt(5), enemy
					// .getPositionX(), enemy.getPositionY()));
				}

				if (enemy.isThrowing()) {
					enemy.setThrowing(false);
					float percent = mRandom.nextFloat();
					if (percent <= BulletOfEnemy.PERCENT) {
						mBulletOfEnemyList.add(new BulletOfEnemy(enemy
								.getPositionX(), enemy.getPositionY()));
						mBulletOfEnemyList.add(new BulletOfEnemy(enemy
								.getPositionX() - 32, enemy.getPositionY()));
						mBulletOfEnemyList.add(new BulletOfEnemy(enemy
								.getPositionX() + 32, enemy.getPositionY()));
					}
				}

				// if (mLevel >= 10 && mBoss.getLives() > 0) {
				// enemy.setAlive(false);
				// mBoss.setAlive(true);
				// }
			}

			if (mBoss.isThrowing()) {
				mBoss.setThrowing(false);
				float percent = mRandom.nextFloat();
				if (percent <= BulletOfEnemy.PERCENT) {
					mBulletOfEnemyList.add(new BulletOfEnemy(mBoss
							.getPositionX(), mBoss.getPositionY() - 64));
					mBulletOfEnemyList.add(new BulletOfEnemy(mBoss
							.getPositionX() - 64, mBoss.getPositionY() - 64));
					mBulletOfEnemyList.add(new BulletOfEnemy(mBoss
							.getPositionX() - 32, mBoss.getPositionY() - 64));
					mBulletOfEnemyList.add(new BulletOfEnemy(mBoss
							.getPositionX() + 32, mBoss.getPositionY() - 64));
					mBulletOfEnemyList.add(new BulletOfEnemy(mBoss
							.getPositionX() + 64, mBoss.getPositionY() - 64));
				}
			}

			for (int i = 0; i < mCoinList.size(); i++) {
				Coin coin = mCoinList.get(i);
				coin.update();
				mPlayer.checkCoin(mPlayer, mCoinList);
				if (mPlayer.isBonus_Coin()) {
					GameMain.mAudioLibrary.get(AudioLibrary.COIN).play();
					mBonus = coin.getBonus();
					mScore += mBonus;
					mPositionX = coin.getPositionX();
					mPositionY = coin.getPositionY();
					mPlayer.setBonus_Coin(false);
				}
				if (!coin.isAlive()) {
					mCoinList.remove(i);
				}
			}

			// for (int i = 0; i < mGiftList.size(); i++) {
			// Gift gift = mGiftList.get(i);
			// gift.update();
			// mPlayer.checkGift(mPlayer, mGiftList);
			// if (mPlayer.isBonus_Gift()) {
			// GameMain.mAudioLibrary.get(AudioLibrary.ITEM).play();
			// mPositionX = gift.getPositionX();
			// mPositionY = gift.getPositionY();
			// mPlayer.setBonus_Gift(false);
			// }
			// if (!gift.isAlive()) {
			// mGiftList.remove(i);
			// }
			// }

			for (int index = 0; index < mBulletOfEnemyList.size(); index++) {
				BulletOfEnemy bulletOfEnemy = mBulletOfEnemyList.get(index);
				bulletOfEnemy.update();
				mPlayer.checkLife(mPlayer, mBulletOfEnemyList);
				if (!mPlayer.isLives()) {
					if (mLives > 0) {
						mLives--;
						mLifeList.get(mLives).setmLevel(1);
						mPlayer.setLives(true);
					}
				}
				if (!bulletOfEnemy.isAlive()) {
					mBulletOfEnemyList.remove(index);
				}
			}

			if (mScore >= mGoal) {
				GameMain.mAudioLibrary.get(AudioLibrary.UP_LEVEL).play();
				mLevel++;
				mGoal = mGoal + mLevel * GOAL;
				if (mLevel >= 10 && (mLevel % 10 == 0) && mMap < 5) {
					mMap++;
					GameMain.mAudioLibrary.get(AudioLibrary.BG_SOUND).stop();
					GameMain.mAudioLibrary.get(AudioLibrary.STAGE + 1).loop();
				}
			}

			for (int index = 0; index < mExplosionList.size(); index++) {
				Explosion explosion = mExplosionList.get(index);
				explosion.update();
				if (!explosion.isAlive()) {
					mExplosionList.remove(index);
				}
			}

			if (mLives == 0) {
				// mGameHighScore.mHighScore = mScore;
				GameMain.mAudioLibrary.get(AudioLibrary.STAGE + 1).stop();
				GameMain.mAudioLibrary.get(AudioLibrary.GAMEOVER).play();
				mPlaying = false;
				mGameOver = true;
			}
		}
	}

	@Override
	public void render(Graphics2D graphics2d) {
		graphics2d.drawImage(mImage, 0, 0, null);
		if (mPlaying) {
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.LOADING), 0, 0,
					null);
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.LOADING), 0, 0,
					null);
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.LOADING), 0, 0,
					null);
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.MAP + mMap),
					bg1.getBgX(), bg1.getBgY(), null);
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.MAP + mMap),
					bg2.getBgX(), bg2.getBgY(), null);

			mPlayer.render(graphics2d);
			mBoss.render(graphics2d);

			graphics2d.setColor(Color.GREEN);
			graphics2d.setFont(new Font(null, Font.BOLD, 20));
			graphics2d.drawString("Score:  " + mScore, 10, 30);
			graphics2d.setFont(new Font(null, Font.BOLD, 20));
			graphics2d.drawString("Goal:  " + mGoal, 10, 50);
			graphics2d.setFont(new Font(null, Font.BOLD, 20));
			graphics2d.drawString("Level:  " + mLevel, 10, 70);

			for (Bullet bullet : mBulletList) {
				bullet.render(graphics2d);
			}

			for (Enemy enemy : mEnemyList) {
				enemy.render(graphics2d);
			}

			for (Explosion explosion : mExplosionList) {
				explosion.render(graphics2d);
			}

			for (Life life : mLifeList) {
				life.render(graphics2d);
			}

			for (BulletOfEnemy bulletOfEnemy : mBulletOfEnemyList) {
				bulletOfEnemy.render(graphics2d);
			}

			for (Coin coin : mCoinList) {
				coin.render(graphics2d);
			}

			// for (Gift gift : mGiftList) {
			// gift.render(graphics2d);
			// }
		}

		if (mGameOver) {
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.BG_GAME_OVER), 0,
					0, null);
			graphics2d.drawImage(
					GameMain.mImageLibrary.get(ImageLibrary.GAME_OVER),
					(GamePanel.WIDTH - WIDTH) / 2,
					(GamePanel.HEIGHT - HEIGHT) / 3 - 20, null);
			String replay = new String("Replay - R");
			String quit = new String("Quit - Q");
			graphics2d.setColor(Color.red);
			graphics2d.setFont(new Font(null, Font.ITALIC, 30));
			graphics2d.drawString(replay, GamePanel.WIDTH / 3 - replay.length()
					+ 20, GamePanel.HEIGHT / 2);
			graphics2d.drawString(quit, GamePanel.WIDTH / 3 - quit.length()
					+ 20, GamePanel.HEIGHT / 2 + 60);

			GameMain.mAudioLibrary.get(AudioLibrary.BG_SOUND).stop();
		}

		if (!mPlaying && !mGameOver) {
			String start = new String("Start Game - P");
			String score = new String("High Score - S");
			String exit = new String("Exit - E");
			String help = new String("Help - H");
			String about = new String("About - A");
			graphics2d.setColor(Color.WHITE);
			graphics2d.fillRect(GamePanel.WIDTH / 3 - 30,
					GamePanel.HEIGHT / 3 - 10, 250, 50);
			graphics2d.fillRect(GamePanel.WIDTH / 3 - 30,
					GamePanel.HEIGHT / 3 + 60, 250, 50);
			graphics2d.fillRect(GamePanel.WIDTH / 3 - 30,
					GamePanel.HEIGHT / 3 + 130, 250, 50);
			graphics2d.fillRect(GamePanel.WIDTH / 3 - 30,
					GamePanel.HEIGHT / 3 + 200, 250, 50);
			graphics2d.fillRect(GamePanel.WIDTH / 3 - 30,
					GamePanel.HEIGHT / 3 + 270, 250, 50);
			graphics2d.setFont(new Font(null, Font.ITALIC, 30));
			graphics2d.setColor(Color.RED);
			graphics2d.drawString(start, GamePanel.WIDTH / 3 - start.length()
					+ 10, GamePanel.HEIGHT / 3 + 25);
			graphics2d.drawString(score, GamePanel.WIDTH / 3 - score.length()
					+ 10, GamePanel.HEIGHT / 3 + 95);
			graphics2d.drawString(help, GamePanel.WIDTH / 3 - help.length()
					+ 10, GamePanel.HEIGHT / 3 + 165);
			graphics2d.drawString(about, GamePanel.WIDTH / 3 - about.length()
					+ 10, GamePanel.HEIGHT / 3 + 235);
			graphics2d.drawString(exit, GamePanel.WIDTH / 3 - exit.length()
					+ 10, GamePanel.HEIGHT / 3 + 305);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		mPlayer.keyPressed(arg0);

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			mShooting = true;
			break;

		case KeyEvent.VK_P:
			mPlaying = !mPlaying;
			if (mPlaying) {
				GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
				GameMain.mAudioLibrary.get(AudioLibrary.PLANE_START).play();
				GameMain.mAudioLibrary.get(AudioLibrary.BG_SOUND).loop();
			}
			break;

		case KeyEvent.VK_S:
			GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
			new GameHighScore(mScore);
			break;

		case KeyEvent.VK_H:
			GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
			new GameHelp();
			break;

		case KeyEvent.VK_A:
			GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
			new GameAbout();
			break;

		case KeyEvent.VK_E:
			GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
			System.exit(0);
			break;
		}

		if (mGameOver) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_R:
				GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
				init();
				break;

			case KeyEvent.VK_Q:
				GameMain.mAudioLibrary.get(AudioLibrary.BUTTON).play();
				System.exit(0);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		mPlayer.keyReleased(arg0);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		mPlayer.keyTyped(arg0);

	}

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		Sky.bg1 = bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		Sky.bg2 = bg2;
	}

}
