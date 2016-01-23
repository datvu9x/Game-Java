package dev.datpgm.airstrike;

import dev.datpgm.airstrike.audios.AudioLibrary;
import dev.datpgm.airstrike.images.ImageLibrary;

public class GameMain {

	public static ImageLibrary mImageLibrary;
	public static AudioLibrary mAudioLibrary;

	public static void main(String[] args) {
		mImageLibrary = ImageLibrary.getInstance();
		mImageLibrary.loadAllImage();
		mAudioLibrary = AudioLibrary.getInstance();
		mAudioLibrary.loadAllAudio();
		new GameFrame();
	}
}
