package com.example.app.level.roomdata;

import com.example.app.level.roomdata.objecttypes.RoomObject;
import com.example.app.level.roomdata.tiles.Tile;
import javafx.scene.image.Image;

public class Room {
    private RoomObject[] objects;
    private Tile[][] roomTiles;
    private Image roomImage;

    public Room (int roomNumber) {
        constructRoom(roomNumber);
        roomImage = RoomTools.joinImages(roomTiles);
    }

    public void constructRoom (int roomNumber) {
        try {
            roomTiles = RoomTools.readTileFile(roomNumber);
            objects = RoomTools.readObjectFile(roomNumber);
        }
        catch (Exception e) {
            //failed to construct room.
        }
        //System.out.println("Constructed Room");
    }

    public RoomObject[] getObjects () {
        return objects;
    }

    public Image getRoomImage () {
        return roomImage;
    }
}
