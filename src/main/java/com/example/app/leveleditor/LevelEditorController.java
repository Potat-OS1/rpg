package com.example.app.leveleditor;

import com.example.app.AssetsController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LevelEditorController {
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
    private static Pane r = new Pane();
    @FXML
    public void initialize () {
        //something to do with the tiles
        r.setBackground(new Background(new BackgroundFill(new Color(0.0, 0.0, 0.0, 0.2), null, null)));
        r.setStyle(
                "-fx-border-color: black;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n"
        );
        r.setMinSize(350, 350);
        r.setLayoutX(125);
        r.setLayoutY(125);
        sceneDivider.toFront();
        r.toBack();
        levelScene.getChildren().add(r);
        levelBehavior(r);
        populateTileVbox();
        populateObjectVbox();
        //something to do with the objects
        //something to do with entities
        //when i get around to adding it to the editor, something to do with the rooms
    }

    public void populateTileVbox () {
        // calculate the number of rows needed:
        int b = (int) Math.ceil(AssetsController.material.length / 4.0);
        for (int c = 0; c < b; c++) {
            tileVbox.getChildren().add(makeNewRow(c, "material"));
        }
    }

    public void populateObjectVbox () {
        int b = (int) Math.ceil(AssetsController.containers.length / 4.0);
        System.out.println(b);
        for (int c = 0; c < b; c++) {
            objectVbox.getChildren().add(makeNewRow(c, "container"));
        }
        b = (int) Math.ceil(AssetsController.lights.length / 4.0);
        System.out.println(b);
        for (int c = 0; c < b; c++) {
            objectVbox.getChildren().add(makeNewRow(c, "light"));
        }
    }

    public void populateEntityVbox() {

    }

    public HBox makeNewRow (int c, String field) {
        HBox hbox = new HBox();
        hbox.getChildren().add(createRegion());
        for (int d = 0; d < 4; d++) {
            try {
                switch (field) {
                    case ("material") -> hbox.getChildren().add(newTile(AssetsController.material[(c*4) + d], (c*4 + d)));
                    case ("container") -> hbox.getChildren().add(newTile(AssetsController.containers[(c*4) + d], (c*4 + d)));
                    case ("light") -> hbox.getChildren().add(newTile(AssetsController.lights[(c*4) + d], (c*4 + d)));
                }
                hbox.getChildren().add(createRegion());
            }
            catch (Exception e) {
                System.out.println("fail ");
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

    public static void levelBehavior (Pane p) {
        p.setOnMouseClicked(Event->{
            System.out.println(Event.getX());
            System.out.println(Event.getY());
        });
    }
}
