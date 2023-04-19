package com.example.app;

import com.example.app.level.Level;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

public class Controller extends Application {
    public static BorderPane objectLayer = new BorderPane();
    boolean enterLevelEditor = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Better Final");
        Parameters params = getParameters();
        for (String s : params.getRaw()) {
            if (s.equals("Level_Editor")) {
                enterLevelEditor = true;
            }
        }
        Pane congregateLayer = new Pane();
        if (enterLevelEditor) {
            System.out.println(Controller.class);
            FXMLLoader loader = new FXMLLoader(Controller.class.getResource("level_editor.fxml"));
            congregateLayer.getChildren().add(loader.load());
        }
        else {
            BorderPane levelLayer = new BorderPane();
            congregateLayer = new Pane(levelLayer, objectLayer);
            Level newLevel = new Level(1);
            levelLayer.getChildren().add(new ImageView(newLevel.getLevelImage()));
        }



        Scene scene = new Scene(congregateLayer, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
