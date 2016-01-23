package dev.datpgm.airstrike;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import dev.datpgm.airstrike.entities.Sky;

public class GamePanel extends JPanel implements GameAction, KeyListener,
		Runnable {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final int FPS = 50;

	private static final long serialVersionUID = 7995668378011340731L;

	private Thread mThread;
	private boolean mRunning;
	private Graphics2D mGraphics2d;
	private BufferedImage mBufferedImage;
	private Sky sky;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		setBackground(Color.BLACK);
		addKeyListener(this);
		init();
	}

	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(mBufferedImage, 0, 0, null);
		graphics.dispose();
	}

	@Override
	public void run() {
		while (mRunning) {
			long start = System.currentTimeMillis();
			update();
			render(mGraphics2d);
			repaint();
			long sleep = 1000 / FPS - (System.currentTimeMillis() - start);
			if (sleep < 0) {
				sleep = 0;
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		sky.keyPressed(keyEvent);
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		sky.keyReleased(keyEvent);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
		sky.keyTyped(keyEvent);
	}

	@Override
	public void init() {
		mBufferedImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		mGraphics2d = (Graphics2D) mBufferedImage.getGraphics();
		mGraphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		sky = new Sky();
		mRunning = true;
		mThread = new Thread(this);
		mThread.start();
	}

	@Override
	public void update() {
		sky.update();
	}

	@Override
	public void render(Graphics2D graphics2d) {
		sky.render(graphics2d);
	}
}
