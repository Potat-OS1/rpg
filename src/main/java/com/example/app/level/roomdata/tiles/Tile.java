package com.example.app.level.roomdata.tiles;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Tile {
    protected Image material;
    private final Rectangle tile;

    Tile (Image material) {
        this.material = material;
        tile = new Rectangle(material.getWidth(), material.getHeight());
        tile.setFill(new ImagePattern(material));
    }

    public Image getMaterial() {
        return material;
    }

    public Rectangle getTile() {
        return tile;
    }
}
