package com.example.app.leveleditor;

import com.example.app.AssetsController;
import com.example.app.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LevelEditorController {
    @FXML
    private VBox tileVbox;
    @FXML
    private VBox objectVbox;
    @FXML
    private VBox entityVbox;
    @FXML
    private Pane base;

    @FXML
    public void initialize () {
        //something to do with the tiles
        populateTileVbox();
        //something to do with the objects
        //something to do with entities
        //when i get around to adding it to the editor, something to do with the rooms
    }

    public void populateTileVbox () {
        // calculate the number of rows needed:
        int b = (int) Math.ceil(AssetsController.material.length / 4.0);
        for (int c = 0; c < b; c++) {
            for (int d = 0; d < 4; d++) {
                try {
                    tileVbox.getChildren().add(newTile(AssetsController.material[(c * 4) + d], ((c * 4) + d)).load());
                }
                catch (Exception e) {
                    //e.getStackTrace();
                    System.out.println("fail ");
                }
            }
        }
    }

    public void makeEditorTile () {

    }

    public FXMLLoader newTile (Image im, int a) {
        FXMLLoader tileLayout = new FXMLLoader(LevelEditorController.class.getResource("scrollpane_resource_display.fxml"));
        //LevelEditorTileController controller = tileLayout.getController();
        //controller.setImage(im);
        //controller.setLabelString(String.valueOf(a));
        return tileLayout;
    }
}
