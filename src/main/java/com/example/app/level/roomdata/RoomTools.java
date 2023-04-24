package com.example.app.level.roomdata;

import com.example.app.AssetsController;
import com.example.app.level.roomdata.objecttypes.RoomObject;
import com.example.app.level.roomdata.objecttypes.container.Barrel;
import com.example.app.level.roomdata.objecttypes.container.Crate;
import com.example.app.level.roomdata.objecttypes.light.Generic;
import com.example.app.level.roomdata.tiles.Floor;
import com.example.app.level.roomdata.tiles.Material;
import com.example.app.level.roomdata.tiles.Tile;
import com.example.app.level.roomdata.tiles.Wall;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class RoomTools {
    public static Tile[][] readTileFile (int num) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(RoomTools.class.getResourceAsStream("/level/room/room " + num + "/room.txt"))));
        String line;
        String[] temp;
        ArrayList<ArrayList<Tile>> room = new ArrayList<>();
        int a = 0;
        while (true) {
            try {
                if ((line = br.readLine()) != null) {
                    room.add(new ArrayList<>());
                    temp = line.split(", ");
                    for (String s : temp) {
                        switch (s) {
                            case ("0") -> room.get(a).add(new Floor(Material.GRAYSTONE));
                            case ("1") -> room.get(a).add(new Floor(Material.BLACKSTONE));
                            case ("2") -> room.get(a).add(new Floor(Material.CLOTH));
                            case ("100") -> room.get(a).add(new Floor(AssetsController.floor[0]));
                            case ("200") -> room.get(a).add(new Wall(AssetsController.wall[0]));
                            case ("300") -> room.get(a).add(new Floor(AssetsController.cloth[0]));
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

        return room.stream().map(u -> u.toArray(new Tile[0])).toArray(Tile[][]::new);
    }

    public static RoomObject[] readObjectFile (int num) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(RoomTools.class.getResourceAsStream("/level/room/room " + num + "/objects.txt"))));
        String line;
        String[] temp;
        ArrayList<RoomObject> objects = new ArrayList<>();
        while (true) {
            try {
                if ((line = br.readLine()) != null) {
                    temp = line.split(", ");
                    switch (temp[0]) {
                        case ("Barrel") -> objects.add(new Barrel(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
                        case ("Crate") -> objects.add(new Crate(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
                        case ("Light") -> objects.add(new Generic(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), 1, 0));
                    }
                }
                else {
                    break;
                }
            }
            catch (Exception e) {
                break;
            }
        }
        return objects.toArray(new RoomObject[0]);
    }

    public static Image joinImages(Tile[][] tile) {
        int width = (int) (tile.length * tile[0][0].getTile().getWidth());
        int height = (int) (tile[0].length * tile[0][0].getTile().getHeight());
        int tileWidth = (int) tile[0][0].getMaterial().getWidth();
        int tileHeight = (int) tile[0][0].getMaterial().getHeight();

        WritableImage wi = new WritableImage(width, height);
        for (int a = 0; a < tile.length; a++) {
            for (int b = 0; b < tile[a].length; b++) {
                int w = (int) tile[a][b].getMaterial().getWidth();
                int h = (int) tile[a][b].getMaterial().getHeight();
                for (int c = 0; c < w; c++) {
                    for (int d = 0; d < h; d++) {
                        wi.getPixelWriter().setArgb(c + (a*tileWidth), d + (b * tileHeight), tile[a][b].getMaterial().getPixelReader().getArgb(d, c));
                    }
                }
            }
        }
        return wi;
    }
}
