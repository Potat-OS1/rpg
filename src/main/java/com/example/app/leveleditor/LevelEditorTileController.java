package com.example.app.leveleditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LevelEditorTileController {
    private String labelString;
    private Image im;

    @FXML
    private Label label;
    @FXML
    private Rectangle pic;
    @FXML
    private Pane basePane;

    public void initialize () {
        label.setText(labelString);
        pic.setFill(new ImagePattern(im));
    }

    public void setLabelString (String input) {
        labelString = input;
    }

    public void setImage (Image im) {
        this.im = im;
    }
}
