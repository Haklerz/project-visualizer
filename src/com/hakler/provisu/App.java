package com.hakler.provisu;

import java.awt.Graphics2D;

public interface App {
    
    public void update(double deltaTimeSeconds);

    public void draw(Graphics2D graphics);
}
