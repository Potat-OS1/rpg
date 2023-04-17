package com.example.app.level.roomdata.objecttypes.container;

import com.example.app.AssetsController;

public class Crate extends Container{
    public Crate(int x, int y) {
        super(x, y);
        this.sprite = AssetsController.containers[0];
    }
}
