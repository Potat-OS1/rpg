package com.example.app.level;

import com.example.app.AssetsController;
import com.example.app.Controller;
import com.example.app.level.roomdata.Room;
import com.example.app.level.roomdata.objecttypes.RoomObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;

public class Level {
    private int width;
    private int height;
    private Room[][] layout;
    private Image levelImage;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        constructLevel();
        levelImage = LevelTools.joinImages(layout);
    }

    public void constructLevel() {
        layout = LevelTools.readLevelFile(1);
        for (Room[] r : layout) {
            for (Room b : r) {
                for (RoomObject obj : b.getObjects()) {
                    ImageView iv = new ImageView(obj.getSprite());
                    Controller.objectLayer.getChildren().add(iv);
                    iv.setLayoutX(obj.getX() + (Arrays.asList(layout).indexOf(r) * AssetsController.tileWidth * b.getWidth()));
                    iv.setLayoutY(obj.getY() + (Arrays.asList(r).indexOf(b) * AssetsController.tileHeight * b.getHeight()));
                }
            }
        }
        System.out.println("Constructed Level");
    }

    public Room[][] getLayout() {
        return layout;
    }

    public Image getLevelImage () {
        return levelImage;
    }
}
