package com.example.app;

import com.example.app.level.Level;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Controller extends Application {
    public static BorderPane objectLayer = new BorderPane();

    @Override
    public void start(Stage primaryStage) {
        BorderPane levelLayer = new BorderPane();
        Pane congregateLayer = new Pane(levelLayer, objectLayer);

        Scene scene = new Scene(congregateLayer, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        Level newLevel = new Level(3, 3);
        levelLayer.getChildren().add(new ImageView(newLevel.getLevelImage()));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
