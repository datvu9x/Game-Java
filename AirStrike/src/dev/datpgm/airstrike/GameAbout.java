package dev.datpgm.airstrike;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import dev.datpgm.airstrike.images.ImageLibrary;

public class GameAbout extends JFrame {

	private static final long serialVersionUID = -2134614003421727135L;

	private static final String TITLE = "ABOUT";
	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	private Image image;
	private Graphics second;

	public GameAbout() {
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		image = GameMain.mImageLibrary.get(ImageLibrary.GROUND);
		setBackground(Color.WHITE);
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

		second.setColor(Color.WHITE);
		second.fillRect(0, 0, WIDTH, HEIGHT);
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC, 20));
		g.drawString("Game AirStrike version 1.0", 10, HEIGHT / 3);
		g.drawString("A product of DatPgm", 10, HEIGHT / 3 + 30);
		g.drawString("CopyRight @ Studio : 8 - 2015", 10, HEIGHT / 3 + 60);
		g.drawString("A product of DatPgm", 10, HEIGHT / 3 + 90);
		g.drawString("Contact: thedat246@gmail.com ^^", 10, HEIGHT / 3 + 120);
	}

}
