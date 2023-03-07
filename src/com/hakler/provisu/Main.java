package com.hakler.provisu;

import java.awt.Graphics2D;

public class Main implements App {
    public static void main(String[] args) {
        new AppDriver(new Main(), 10000).run();
    }

    @Override
    public void update(double deltaTimeSeconds) {
        System.out.println("Update! dt=" + deltaTimeSeconds + "s");
    }

    @Override
    public void draw(Graphics2D graphics) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}
