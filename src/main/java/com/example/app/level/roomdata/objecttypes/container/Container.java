package com.example.app.level.roomdata.objecttypes.container;

import com.example.app.items.Item;
import com.example.app.level.roomdata.objecttypes.RoomObject;
import java.util.ArrayList;

public class Container extends RoomObject {
    private ArrayList<Item> inventory = new ArrayList<>();

    Container(int x, int y) {
        super(x, y);
    }

    public ArrayList<Item> getInventory () {
        return inventory;
    }

    public void addToInventory (Item i) {
        inventory.add(i);
    }

    public void removeFromInventory (Item i) {
        try {
            inventory.remove(i);
        }
        catch (Exception e) {
            System.out.println("no such item in inventory.");
        }
    }
}
