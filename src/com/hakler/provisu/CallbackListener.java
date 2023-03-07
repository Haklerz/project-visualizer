package com.hakler.provisu;

import java.awt.Graphics2D;

public interface CallbackListener {
    
    public void onUpdate(double deltaTimeSeconds);

    public void onDraw(Graphics2D graphics);
}
