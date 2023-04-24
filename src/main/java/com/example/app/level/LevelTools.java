package com.example.app.level;

import com.example.app.level.roomdata.Room;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class LevelTools {
    public static Image joinImages(Room[][] rooms) {
        int width = 10000;
        int height = 10000;
        int currentX;
        int currentY = 0;
        int bigHeight;

        // ADD A HANDLER THAT MAKES THE WIDTH AND HEIGHT OF THIS THE LONGEST ROW OR TILE * TILEWIDTH.

        WritableImage wi = new WritableImage(width, height);
        for (Room[] room : rooms) {
            currentX = 0;
            bigHeight = 0;
            for (Room value : room) {
                int h = (int) value.getRoomImage().getWidth();
                int w = (int) value.getRoomImage().getHeight();
                for (int c = 0; c < w; c++) {
                    for (int d = 0; d < h; d++) {
                        wi.getPixelWriter().setArgb(c + currentX, d + currentY, value.getRoomImage().getPixelReader().getArgb(d, c));
                    }
                }
                if (bigHeight < h) {
                    bigHeight = h;
                }
                currentX = currentX + w;
            }
            currentY = currentY + bigHeight;
        }
        return wi;
    }

    public static Room[][] readLevelFile(int num) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(LevelTools.class.getResourceAsStream("/level/level " + num + ".txt"))));
        String line;
        String[] temp;
        ArrayList<ArrayList<Room>> level = new ArrayList<>();
        int a = 0;
        while (true) {
            try {
                if ((line = br.readLine()) != null) {
                    level.add(new ArrayList<>());
                    temp = line.split(", ");
                    for (String s : temp) {
                        level.get(a).add(new Room(Integer.parseInt(s)));
                    }
                }
                else {
                    break;
                }
            }
            catch (Exception e) {
                break;
            }
            a++;
        }
        return level.stream().map(u -> u.toArray(new Room[0])).toArray(Room[][]::new);
    }
}
