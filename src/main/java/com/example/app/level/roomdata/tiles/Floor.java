package com.example.app.level.roomdata.tiles;

import javafx.scene.image.Image;

public class Floor extends Tile{
    public Floor(Material mat) {
        super(mat.getMat());
    }

    public Floor(Image im) {
        super(im);
    }
}
