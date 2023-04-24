package com.example.app;

import javafx.scene.image.Image;

public class AssetsController {
    public static int tileWidth = 64;
    public static int tileHeight = 64;

    public static Image[] lights = {
            new Image(AssetsController.class.getResourceAsStream("/sprites/lights/smallleftflame.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lights/smallrightflame.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lights/tallleftflame.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lights/tallrightflame.png"), tileWidth, tileHeight, false, false),
    };

    public static Image[] lightingEffects = {
            new Image(AssetsController.class.getResourceAsStream("/sprites/lightingeffects/centerlight.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lightingeffects/leftlight.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lightingeffects/rightlight.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/lightingeffects/shadow.png"), tileWidth, tileHeight, false, false),
    };

    public static Image[] containers = {
            new Image(AssetsController.class.getResourceAsStream("/sprites/crate.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/sprites/barrel.png"), tileWidth, tileHeight, false, false),
    };
    public static Image[] material = {
            new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/redmat.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/graymat.png"), tileWidth, tileHeight, false, false),
            new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/blackmat.png"), tileWidth, tileHeight, false, false),
    };

    public static Image[] cloth = {
            new Image(AssetsController.class.getResourceAsStream("/tiledata/cloth/redcarpet.png"), tileWidth, tileHeight, false, false)
    };

    public static Image[] floor = {
            new Image(AssetsController.class.getResourceAsStream("/tiledata/floor/cobblestone.png"), tileWidth, tileHeight, false, false)
    };

    public static Image[] wall = {
            new Image(AssetsController.class.getResourceAsStream("/tiledata/wall/archway wall.png"), tileWidth, tileHeight, false, false)
    };

    public static Image[][] scenery = {
            material,
            cloth,
            floor,
            wall
    };
}
