package com.hakler.provisu;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

public final class DefaultGraphicsConfiguration {
    private static GraphicsConfiguration instance;

    private DefaultGraphicsConfiguration() {
    }

    /**
     * Returns the <code>DefaultGraphicsConfiguration</code>.
     * 
     * @return the <code>DefaultGraphicsConfiguration</code>
     */
    public static GraphicsConfiguration getInstance() {
        if (instance == null)
            instance = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

        return instance;
    }
}