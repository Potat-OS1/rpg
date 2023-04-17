package com.example.app.level;

import com.example.app.level.roomdata.Room;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class LevelTools {
    public static Image joinImages(Room[][] rooms) {
        int width = (int) (rooms.length * rooms[0][0].getRoomImage().getWidth());
        int height = (int) (rooms[0].length * rooms[0][0].getRoomImage().getHeight());
        int tileWidth = (int) rooms[0][0].getRoomImage().getWidth();
        int tileHeight = (int) rooms[0][0].getRoomImage().getHeight();

        WritableImage wi = new WritableImage(width, height);
        for (int a = 0; a < rooms.length; a++) {
            for (int b = 0; b < rooms[a].length; b++) {
                int w = (int) rooms[a][b].getRoomImage().getWidth();
                int h = (int) rooms[a][b].getRoomImage().getHeight();
                for (int c = 0; c < w; c++) {
                    for (int d = 0; d < h; d++) {
                        wi.getPixelWriter().setArgb(c + (a*tileWidth), d + (b * tileHeight), rooms[a][b].getRoomImage().getPixelReader().getArgb(c, d));
                    }
                }
            }
        }
        return wi;
    }
}
