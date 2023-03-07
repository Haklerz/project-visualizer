package com.hakler.provisu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Main implements CallbackListener {

	private Random random;

    public static void main(String[] args) {
        new Window("Project Visualizer", 16 * 64, 9 * 64, new Main(), 60).run();
    }

	public Main() {
		this.random = new Random();
	}

	@Override
	public void onUpdate(double deltaTimeSeconds) {
		System.out.println("Update! dt = " + deltaTimeSeconds + "s");
	}

	@Override
	public void onDraw(Graphics2D graphics) {
		
		for (int i = 0; i < 10_000; i++) {
			graphics.setColor(new Color(random.nextInt()));
			graphics.drawLine(random.nextInt(16 * 64), random.nextInt(9 * 64), random.nextInt(16 * 64), random.nextInt(9 * 64));
		}
	}
}
