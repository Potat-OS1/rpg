package com.example.app;

import com.example.app.level.Level;
import com.example.app.leveleditor.LevelScene;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

public class Controller extends Application {
    public static BorderPane objectLayer = new BorderPane();
    boolean enterLevelEditor = false;
    int[] startingSize = new int[2];

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Better Final");
        Parameters params = getParameters();
        startingSize[0] = 600;
        startingSize[1] = 600;
        for (String s : params.getRaw()) {
            if (s.equals("Level_Editor")) {
                enterLevelEditor = true;
                startingSize[0] = 800;
                break;
            }
        }
        Pane congregateLayer = new Pane();
        Scene scene = new Scene(congregateLayer, startingSize[0], startingSize[1]);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        if (enterLevelEditor) {
            System.out.println(Controller.class);
            FXMLLoader loader = new FXMLLoader(Controller.class.getResource("level_editor.fxml"));
            congregateLayer.getChildren().add(loader.load());
            setBehavior(scene);
        }
        else {
            BorderPane levelLayer = new BorderPane();
            congregateLayer = new Pane(levelLayer, objectLayer);
            Level newLevel = new Level(1);
            levelLayer.getChildren().add(new ImageView(newLevel.getLevelImage()));
        }

        AnimationTimer t = new Update();
        t.start();

        scene.setRoot(congregateLayer);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setBehavior (Scene s) {
        s.setOnKeyPressed(Event ->{
            if (Event.getCode() == KeyCode.W) {
                Update.north = true;
            }
            if (Event.getCode() == KeyCode.S) {
                Update.south = true;
            }
            if (Event.getCode() == KeyCode.A) {
                Update.west = true;
            }
            if (Event.getCode() == KeyCode.D) {
                Update.east = true;
            }
            if (Event.getCode() == KeyCode.ADD) {
                LevelScene.scaleGrid(10);
            }
            if (Event.getCode() == KeyCode.SUBTRACT) {
                LevelScene.scaleGrid(-10);
            }
            if (Event.getCode() == KeyCode.K) {
                LevelScene.resizeGridPane(LevelScene.gp.getRowCount() + 1);
            }
        });

        s.setOnKeyReleased(Event ->{
            if (Event.getCode() == KeyCode.W) {
                Update.north = false;
            }
            if (Event.getCode() == KeyCode.S) {
                Update.south = false;
            }
            if (Event.getCode() == KeyCode.A) {
                Update.west = false;
            }
            if (Event.getCode() == KeyCode.D) {
                Update.east = false;
            }
        });
    }
}
