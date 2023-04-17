package com.example.app.level.roomdata.objecttypes.light;

import com.example.app.level.roomdata.objecttypes.RoomObject;

public abstract class Light extends RoomObject {
    protected int strength, state, endState;

    Light (int x, int y, int strength, int state, int endState) {
        super(x, y);
        this.state = state;
        this.endState = endState;
        this.strength = strength;
        this.type = "Light";
        initialSprite();
    }

    public int getStrength () {
        return strength;
    }

    public abstract void initialSprite ();

    public abstract void pulse ();
}
