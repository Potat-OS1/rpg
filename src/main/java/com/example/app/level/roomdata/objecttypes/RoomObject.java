package com.example.app.level.roomdata.objecttypes;

import javafx.scene.image.Image;

public class RoomObject {
    protected String type;
    private int x;
    private int y;
    protected Image sprite;

    protected RoomObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public Image getSprite () {
        return sprite;
    }
}
