package dev.datpgm.airstrike;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import dev.datpgm.airstrike.images.ImageLibrary;

public class GameHighScore extends JFrame {

	private static final long serialVersionUID = -2134614003421727135L;
	
	private static final String TITLE = "HIGH SCORE";
	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	private Image image;
	private Graphics second;
	public int mHighScore;

	public GameHighScore(int mScore) {
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
		image = GameMain.mImageLibrary.get(ImageLibrary.GROUND);
		mHighScore = mScore;
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(WIDTH, HEIGHT);
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, WIDTH, HEIGHT);
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.BOLD, 30));
		g.drawString("High Score", WIDTH / 3 - 20, HEIGHT / 3);
		g.setFont(new Font(null, Font.ITALIC, 20));
		g.drawString("$" + " "+ getHighScore(), WIDTH / 3, HEIGHT / 3 + 50);
	}
	
	public int getHighScore() {
		return mHighScore;
	}

	public void setHighScore(int mHighScore) {
		this.mHighScore = mHighScore;
	}
}
