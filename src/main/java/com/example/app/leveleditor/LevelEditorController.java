package com.example.app.leveleditor;

import com.example.app.AssetsController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LevelEditorController {
    private static Image brushImage;
    public static int brushIndex;
    @FXML
    private VBox tileVbox;
    @FXML
    private VBox objectVbox;
    @FXML
    private VBox entityVbox;
    @FXML
    private Pane levelScene;
    @FXML
    private HBox sceneDivider;
    @FXML
    private StackPane saveAs;
    private static Pane r = new Pane();
    @FXML
    public void initialize () {
        //something to do with the tiles
        r.getStyleClass().add("edge-to-edge");
        r.setMinSize(350, 350);
        r.setLayoutX(125);
        r.setLayoutY(125);
        r.getChildren().add(LevelScene.scene());
        //r.getChildren().get(0).resize(100, 100);
        sceneDivider.toFront();
        r.toBack();
        levelScene.getChildren().add(r);
        //levelBehavior(r);
        populateTileVbox();
        populateObjectVbox();
        saveLevel();
        //something to do with entities
        //when i get around to adding it to the editor, something to do with the rooms
    }

    public void saveLevel() {
        saveAs.setOnMouseClicked(Event -> {
            PrintWriter pw;
            try {
                pw = new PrintWriter("room.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (int a = 0; a < LevelScene.imageList.length; a++) {
                for (int b = 0; b < LevelScene.imageList[a].length; b++) {
                    switch (LevelScene.imageList[b][a]) {
                        case (2) -> writeTo(b, pw, 1);
                        case (1) -> writeTo(b, pw, 0);
                        case (0) -> writeTo(b, pw, 2);
                        default -> writeTo(b, pw, LevelScene.imageList[b][a]);
                    }
                }
                pw.println();
            }
            pw.close();
        });
    }
    public void writeTo (int b, PrintWriter pw, int entry) {
        if (b+1 == LevelScene.imageList.length) {
            pw.print(entry);
        }
        else {
            pw.print(entry + ", ");
        }
    }

    public static Image getBrushImage () {
        return brushImage;
    }

    public void populateTileVbox () {
        // calculate the number of rows needed:
        int b = (int) Math.ceil(AssetsController.material.length / 4.0);
        for (int a = 0; a < b; a++) {
            tileVbox.getChildren().add(makeNewRow(a, "material"));
        }

        b = (int) Math.ceil(AssetsController.wall.length / 4.0);
        for (int a = 0; a < b; a++) {
            tileVbox.getChildren().add(makeNewRow(a, "wall"));
        }

        b = (int) Math.ceil(AssetsController.cloth.length / 4.0);
        for (int a = 0; a < b; a++) {
            tileVbox.getChildren().add(makeNewRow(a, "cloth"));
        }

        b = (int) Math.ceil(AssetsController.floor.length / 4.0);
        for (int a = 0; a < b; a++) {
            tileVbox.getChildren().add(makeNewRow(a, "floor"));
        }
    }

    public void populateObjectVbox () {
//        int b = (int) Math.ceil(AssetsController.containers.length / 4.0);
//        for (int c = 0; c < b; c++) {
//            objectVbox.getChildren().add(makeNewRow(c, "container"));
//        }
//        b = (int) Math.ceil(AssetsController.lights.length / 4.0);
//        for (int c = 0; c < b; c++) {
//            objectVbox.getChildren().add(makeNewRow(c, "light"));
//        }
    }

    public void populateEntityVbox() {

    }

    public HBox makeNewRow (int c, String type) {
        HBox hbox = new HBox();
        hbox.getChildren().add(createRegion());
        for (int d = 0; d < 4; d++) {
            try {
                switch (type) {
                    case ("cloth") -> hbox.getChildren().add(newTile(AssetsController.cloth[(c * 4) + d], (c * 4) + d + 300));
                    case ("floor") -> hbox.getChildren().add(newTile(AssetsController.floor[(c * 4) + d], (c * 4) + d + 100));
                    case ("material") -> hbox.getChildren().add(newTile(AssetsController.material[(c * 4) + d], (c * 4) + d));
                    case ("wall") -> hbox.getChildren().add(newTile(AssetsController.wall[(c * 4) + d], (c * 4) + d + 200));
                }
                hbox.getChildren().add(createRegion());
            }
            catch (Exception ignored) {
            }
        }
        return hbox;
    }

    public Pane newTile (Image im, int a) {
        StackPane p = new StackPane();
        p.setPrefSize(30, 45);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(30, 30);

        Rectangle r = new Rectangle(30, 30);
        r.setFill(new ImagePattern(im));
        r.setOnMouseClicked(Event->{
            brushImage = im;
            brushIndex = a;
            System.out.println(brushIndex);
        });

        Label l = new Label(String.valueOf(a));
        vbox.getChildren().addAll(l, r);
        p.getChildren().add(vbox);
        return p;
    }

    public Region createRegion () {
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        return r;
    }

    public static Pane getLevel () {
        return r;
    }

//    public static void levelBehavior (Pane p) {
//        p.setOnMouseClicked(Event->{
//            System.out.println(Event.getX());
//            System.out.println(Event.getY());
//        });
//    }
}
