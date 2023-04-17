package com.example.app.level.roomdata.objecttypes.light;

import com.example.app.AssetsController;

public class Torch extends Light{
    Torch(int x, int y, int strength, int state) {
        super(x, y, strength, state, 3);
    }

    @Override
    public void initialSprite() {
        if (state > endState) {
            state = 0;
            sprite = AssetsController.lights[0];
        }
        else {
            sprite = AssetsController.lights[state];
        }
    }

    @Override
    public void pulse() {

    }
}
