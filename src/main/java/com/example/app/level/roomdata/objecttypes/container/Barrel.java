package com.example.app.level.roomdata.objecttypes.container;

import com.example.app.AssetsController;

public class Barrel extends Container {
    public Barrel(int x, int y) {
        super(x, y);
        this.sprite = AssetsController.containers[1];
    }
}
