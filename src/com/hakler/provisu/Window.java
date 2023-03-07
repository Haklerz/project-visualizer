package com.hakler.provisu;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Window
 */
public class Window implements Runnable {
	private CallbackListener listener;

	private JFrame frame;
	private Canvas canvas;

	private BufferedImage backBuffer;
	private BufferStrategy frontBuffer;

	private long targetDeltaTimeNanos;

	public Window(String title, int width, int height, CallbackListener listener, double framesPerSecond) {
		this.listener = listener;

		this.frame = new JFrame(title, DefaultGraphicsConfiguration.getInstance());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setIgnoreRepaint(true);

		this.canvas = new Canvas(DefaultGraphicsConfiguration.getInstance());
		canvas.setSize(width, height);
		canvas.setIgnoreRepaint(true);

		this.backBuffer = DefaultGraphicsConfiguration.getInstance()
				.createCompatibleImage(width, height, Transparency.OPAQUE);

		frame.add(canvas);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		targetDeltaTimeNanos = (long) (1e9d / framesPerSecond);
	}

	@Override
	public void run() {
		SwingUtilities.invokeLater(() -> frame.setVisible(true));

		while (!frame.isDisplayable())
			;

		canvas.createBufferStrategy(2);
		frontBuffer = canvas.getBufferStrategy();

		Graphics2D backBufferGraphics = backBuffer.createGraphics();

		long lastUpdateNanos = System.nanoTime() - targetDeltaTimeNanos;
		while (frame.isDisplayable()) {
			long currentTimeNanos = System.nanoTime();
			if (currentTimeNanos - lastUpdateNanos < targetDeltaTimeNanos)
				continue;

			listener.onUpdate((currentTimeNanos - lastUpdateNanos) * 1e-9d);
			lastUpdateNanos = currentTimeNanos;
			listener.onDraw(backBufferGraphics);

			Graphics2D frontBufferGraphics = null;
			try {
				frontBufferGraphics = (Graphics2D) frontBuffer.getDrawGraphics();

				frontBufferGraphics.drawImage(backBuffer, 0, 0, null);
			} catch (IllegalStateException e) {
				break;
			} finally {
				if (frontBufferGraphics != null)
					frontBufferGraphics.dispose();
			}

			frontBuffer.show();
			Toolkit.getDefaultToolkit().sync();
		}

		System.out.println("Peice out!");
	}
}