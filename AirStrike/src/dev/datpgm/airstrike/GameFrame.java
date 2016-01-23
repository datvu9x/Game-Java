package dev.datpgm.airstrike;

import javax.swing.JFrame;

import dev.datpgm.airstrike.images.ImageLibrary;

public class GameFrame extends JFrame {

	private static final String GAME_NAME = "Air Strike";

	private static final long serialVersionUID = -7814610702620086059L;

	public GameFrame() {
		setTitle(GAME_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(GameMain.mImageLibrary.get(ImageLibrary.ICON))	;
		setContentPane(new GamePanel());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
