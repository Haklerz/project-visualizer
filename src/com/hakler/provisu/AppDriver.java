package com.hakler.provisu;

public class AppDriver implements Runnable {
    private final App app;
    private boolean isRunning;
    private long deltaTimeNanos;

    public AppDriver(App app, double framesPerSecond) {
        this.app = app;
        this.deltaTimeNanos = (long) (1e9d / framesPerSecond);
    }

    @Override
    public void run() {
        isRunning = true;

        long previousUpdateNanos = 0;

        while (isRunning) {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - previousUpdateNanos;

            if (elapsedTimeNanos >= deltaTimeNanos) {
                previousUpdateNanos = currentTimeNanos;

                app.update(elapsedTimeNanos * 1e-9d);
            }
        }
    }
}
