package dev.datpgm.airstrike.audios;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class AudioLibrary {
	private static AudioLibrary mInstance;
	
	public static final String BULLET = "bullet";
	public static final String BG_SOUND = "bgMusic";
	public static final String BUTTON = "button";
	public static final String UP_LEVEL = "uplevel";
	public static final String READY = "ready";
	public static final String UP_BULLET = "upBullet";
	public static final String EXPLOSION = "explosion_";
	public static final String COIN = "coin";
	public static final String WIN = "win";
	public static final String PLANE_START = "planestart";
	public static final String ITEM = "item";
	public static final String POWER = "power";
	public static final String WARNING = "warning";
	public static final String STAGE = "stage";
	public static final String ENEMY = "enemy_";
	public static final String TITLE = "title";
	public static final String GAMEOVER = "gameover";
	public static final String BOSS = "boss";
	
	public static final int NUMBER_EXPLOSION = 2;
	public static final int NUMBER_STAGE = 3;
	public static final int NUMBER_ENEMY = 2;
	public static final int NUMBER_BOSS = 2;


	private HashMap<String, AudioClip> mAudioMap;

	private AudioLibrary() {
		mAudioMap = new HashMap<String, AudioClip>();
	}

	public static AudioLibrary getInstance() {
		if (mInstance == null) {
			mInstance = new AudioLibrary();
		}
		return mInstance;
	}

	public void loadAllAudio() {
		put(BG_SOUND);
		put(TITLE);
		put(BULLET);
		put(BUTTON);
		put(READY);
		put(ITEM);
		put(POWER);
		put(WARNING);
		put(WIN);
		put(COIN);
		put(PLANE_START);
		put(UP_BULLET);
		put(UP_LEVEL);
		put(GAMEOVER);
		for (int index = 0; index  < NUMBER_EXPLOSION; index++) {
			put(EXPLOSION + index);
		}
		for (int index = 0; index  < NUMBER_STAGE; index++) {
			put(STAGE + index);
		}
		for (int index = 0; index  < NUMBER_ENEMY; index++) {
			put(ENEMY + index);
		}
		for (int index = 0; index  < NUMBER_BOSS; index++) {
			put(BOSS + index);
		}
		
	}

	public void stopAll() {

	}

	public void put(String name) {
		AudioClip audioClip = Applet.newAudioClip(AudioLibrary.class
				.getResource(name + ".wav"));
		mAudioMap.put(name, audioClip);
	}

	public AudioClip get(String name) {
		return mAudioMap.get(name);
	}
}
