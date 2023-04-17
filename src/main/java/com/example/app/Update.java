package com.example.app;

import javafx.animation.AnimationTimer;

public class Update extends AnimationTimer {

    private long lastUpdate = 0;

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 10_000_000) {
            //
        }
    }
}
