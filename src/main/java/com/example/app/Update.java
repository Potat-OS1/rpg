package com.example.app;

import com.example.app.leveleditor.LevelEditorController;
import javafx.animation.AnimationTimer;

public class Update extends AnimationTimer {
    private long lastUpdate = 0;
    public static boolean north;
    public static boolean south;
    public static boolean east;
    public static boolean west;

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 1_000_000) {
            if (north) {
                LevelEditorController.getLevel().setLayoutY(LevelEditorController.getLevel().getLayoutY() - 1);
            }
            if (south) {
                LevelEditorController.getLevel().setLayoutY(LevelEditorController.getLevel().getLayoutY() + 1);
            }
            if (east) {
                LevelEditorController.getLevel().setLayoutX(LevelEditorController.getLevel().getLayoutX() + 1);
            }
            if (west) {
                LevelEditorController.getLevel().setLayoutX(LevelEditorController.getLevel().getLayoutX() - 1);
            }
        }
    }
}
