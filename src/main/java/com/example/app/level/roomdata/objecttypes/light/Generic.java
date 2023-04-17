package com.example.app.level.roomdata.objecttypes.light;

import com.example.app.AssetsController;

public class Generic extends Light {
    public Generic(int x, int y, int strength, int state) {
        super(x, y, strength, state, 0);
    }

    @Override
    public void initialSprite() {
        if (state > endState) {
            state = 0;
            sprite = AssetsController.lightingEffects[0];
        }
        else {
            sprite = AssetsController.lightingEffects[state];
        }
    }

    @Override
    public void pulse() {

    }
}
