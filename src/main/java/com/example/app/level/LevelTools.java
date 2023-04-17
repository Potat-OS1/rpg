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

    public static Room[][] readLevelFile(int num) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(LevelTools.class.getResourceAsStream("/level/level 1.txt"))));
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
                        System.out.println(s);
                        switch (s) {
                            case ("0") -> level.get(a).add(new Room(1));
                        }
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
        Room[][] levelArray = level.stream().map(u -> u.toArray(new Room[0])).toArray(Room[][]::new);
        for (Room[] c : levelArray) {
            for (Room b : c) {
                System.out.print(c + "  ");
            }
            System.out.println();
        }
        return levelArray;
    }
}
