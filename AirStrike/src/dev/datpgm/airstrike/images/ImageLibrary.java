package dev.datpgm.airstrike.images;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class ImageLibrary {
	private static ImageLibrary mInstance;

	public static final String BACKGROUND = "background";
	public static final String LOADING = "loading";
	public static final String PLAYER = "player";
	public static final String BULLET = "bullet_";
	public static final String ENEMY = "enemy";
	public static final String BOSS = "boss";
	public static final String LIFE = "life";
	public static final String COIN = "coin_";
	public static final String EXPLOSION = "tx4_";
	public static final String GAME_OVER = "gameover";
	public static final String MAP = "map";
	public static final String BG_GAME_OVER = "over";
	public static final String ICON = "icon";
	public static final String GIFT = "p_";
	public static final String GROUND = "ground";
	public static final String FIRE_BOSS = "fire_boss_";

	public static final int NUMBER_OF_BULLETS = 6;
	public static final int NUMBER_OF_LIFES = 2;
	public static final int NUMBER_OF_MAPS = 6;
	public static final int NUMBER_OF_COINS = 4;
	public static final int NUMBER_OF_EXPLOSIONS = 7;
	public static final int NUMBER_OF_GIFTS = 5;
	public static final int NUMBER_OF_FIRE_BOSS = 2;
	public static final int NUMBER_OF_BOSS = 4;
	public static final int NUMBER_OF_ENEMY = 8;

	private HashMap<String, Image> mImageMap;
	private Toolkit mToolkit;

	private ImageLibrary() {
		mImageMap = new HashMap<>();
		mToolkit = Toolkit.getDefaultToolkit();
	}

	public static ImageLibrary getInstance() {
		if (mInstance == null) {
			mInstance = new ImageLibrary();
		}
		return mInstance;
	}

	public void loadAllImage() {
		put(BACKGROUND);
		put(LOADING);
		put(PLAYER);
		put(ENEMY);
		put(BOSS);
		put(GAME_OVER);
		put(BG_GAME_OVER);
		put(ICON);
		put(GROUND);
		for (int index = 1; index <= NUMBER_OF_BOSS; index++) {
			put(BOSS + index);
		}
		for (int index = 1; index <= NUMBER_OF_ENEMY; index++) {
			put(ENEMY + index);
		}
		for (int index = 0; index < NUMBER_OF_FIRE_BOSS; index++) {
			put(FIRE_BOSS + index);
		}
		for (int index = 0; index < NUMBER_OF_BULLETS; index++) {
			put(BULLET + index);
		}
		for (int index = 0; index < NUMBER_OF_LIFES; index++) {
			put(LIFE + index);
		}
		for (int index = 0; index < NUMBER_OF_MAPS; index++) {
			put(MAP + index);
		}
		for (int index = 0; index < NUMBER_OF_COINS; index++) {
			put(COIN + index);
		}
		for (int index = 1; index <= NUMBER_OF_EXPLOSIONS; index++) {
			put(EXPLOSION + index);
		}
		for (int index = 0; index < NUMBER_OF_GIFTS; index++) {
			put(GIFT + index);
		}
	}

	public Image get(String name) {
		return mImageMap.get(name);
	}

	public void put(String name) {
		mImageMap.put(name, loadImage(name));
	}

	private Image loadImage(String name) {
		String path = "dev/datpgm/airstrike/images/" + name + ".png";
		Image image = mToolkit.getImage(ImageLibrary.class.getClassLoader()
				.getResource(path));
		return image;
	}
}
