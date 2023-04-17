package com.example.app.level;

import com.example.app.AssetsController;
import com.example.app.Controller;
import com.example.app.level.roomdata.Room;
import com.example.app.level.roomdata.objecttypes.RoomObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        layout = new Room[width][height];
        for (int a = 0; a < width; a++) {
            for (int b = 0; b < height; b++) {
                //eventually add a file reader.
                layout[a][b] = new Room(1);
                for (RoomObject obj : layout[a][b].getObjects()) {
                    ImageView iv = new ImageView(obj.getSprite());
                    Controller.objectLayer.getChildren().add(iv);
                    iv.setLayoutX(obj.getX() + (a * AssetsController.tileWidth * layout[a][b].getWidth()));
                    iv.setLayoutY(obj.getY() + (b * AssetsController.tileHeight * layout[a][b].getHeight()));
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
