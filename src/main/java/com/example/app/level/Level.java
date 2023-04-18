package com.example.app.level;

import com.example.app.Controller;
import com.example.app.level.roomdata.Room;
import com.example.app.level.roomdata.objecttypes.RoomObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Level {
    private Room[][] layout;
    private Image levelImage;

    public Level(int num) {
        constructLevel(num);
        levelImage = LevelTools.joinImages(layout);
    }

    public void constructLevel(int num) {
        layout = LevelTools.readLevelFile(num);
        int currentX;
        int currentY = 0;
        int bigHeight;

        for (int a = 0; a < layout.length; a++) {
            currentX = 0;
            bigHeight = 0;
            for (int b = 0; b < layout[a].length; b++) {
                int w = (int) layout[a][b].getRoomImage().getWidth();
                int h = (int) layout[a][b].getRoomImage().getHeight();
                for (RoomObject obj : layout[a][b].getObjects()) {
                    ImageView iv = new ImageView(obj.getSprite());
                    Controller.objectLayer.getChildren().add(iv);
                    iv.setLayoutX(currentX + obj.getX());
                    iv.setLayoutY(currentY + obj.getY());
                }
                if (bigHeight < h) {
                    bigHeight = h;
                }
                currentX = currentX + w;
            }
            currentY = currentY + bigHeight;
        }
        //System.out.println("Constructed Level");
    }

    public Image getLevelImage () {
        return levelImage;
    }
}
