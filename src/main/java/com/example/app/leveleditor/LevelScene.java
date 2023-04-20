package com.example.app.leveleditor;

import com.example.app.AssetsController;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelScene {
    public static GridPane gp = new GridPane();
    static ArrayList<Rectangle> iv = new ArrayList<>();
    static StackPane[][] temp;
    public static GridPane scene() {
        gp.setMinSize(350, 350);
        gp.setStyle(
                "-fx-border-color: black;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n"
        );
        gp.setBackground(new Background(new BackgroundFill(new Color(0.0, 0.0, 0.0, 0.2), null, null)));
        setConstraints(10);
        populateGridPane();
        return gp;
    }

    public static void resizeGridPane(int num) {
        storeValues();
        gp.getChildren().clear();
        gp.setMinSize(350, 350);
        iv.clear();
        gp.getRowConstraints().remove(0, gp.getRowCount());
        gp.getColumnConstraints().remove(0, gp.getColumnCount());
        setConstraints(num);
        populateGridPaneWithValues();
    }

    private static void storeValues () {
        temp = new StackPane[gp.getRowCount()][gp.getColumnCount()];
        for (int a = 0; a < gp.getColumnCount(); a++) {
            for (int b = 0; b < gp.getRowCount(); b++) {
                temp[a][b] = (StackPane) getNodeByRowColumnIndex(b, a, gp);
            }
        }
    }

    public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        for (Node sp : gridPane.getChildren()) {
            if (gridPane.getRowIndex(sp) == row && gridPane.getColumnIndex(sp) == column) {
                return sp;
            }
        }
        return new StackPane();
    }

    private static void populateGridPaneWithValues () {
        for (int d = 0; d < gp.getRowCount(); d++) {
            for (int e = 0; e < gp.getColumnCount(); e++) {
                StackPane p;
                Rectangle r;
                try {
                    p = temp[d][e];
                    r = (Rectangle) temp[d][e].getChildren().get(0);
                    r.setHeight(gp.getMinHeight()/gp.getColumnCount());
                    r.setWidth(gp.getMinWidth()/gp.getRowCount());
                }
                catch (Exception ex) {
                    p = new StackPane();
                    r = new Rectangle(gp.getMinWidth()/gp.getRowCount(), gp.getMinWidth()/gp.getColumnCount());
                    //p.setBorder(new Border(new BorderStroke(new Color(0.2, 0.2, 0.2, 1.0), BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2))));
                    p.getChildren().add(r);
                    r.setStrokeWidth(3);
                }
                iv.add(r);
                gp.add(p, d, e);
            }
        }
    }

    private static void populateGridPane () {
        for (int d = 0; d < gp.getRowCount(); d++) {
            for (int e = 0; e < gp.getColumnCount(); e++) {
                Rectangle r = new Rectangle(gp.getMinWidth()/gp.getRowCount(), gp.getMinWidth()/gp.getColumnCount());
                if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                    r.setFill(new ImagePattern(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/redmat.png"))));
                }
                else {
                    r.setFill(new ImagePattern(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/blackmat.png"))));

                }
                StackPane p = new StackPane();
                //p.setBorder(new Border(new BorderStroke(new Color(0.2, 0.2, 0.2, 1.0), BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(2))));
                p.getChildren().add(r);
                r.setStrokeWidth(3);
                iv.add(r);
                gp.add(p, d, e);
            }
        }
    }

    public static void setConstraints (int scale) {
        double percentage = gp.getMinWidth() / scale;
        for (int a = 0; a < scale; a++) {
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPercentHeight(percentage);
            rowConstraint.setValignment(VPos.CENTER);
            gp.getRowConstraints().add(rowConstraint);
        }
        for (int b = 0; b < scale; b++) {
            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(percentage);
            columnConstraint.setHalignment(HPos.CENTER);
            gp.getColumnConstraints().add(columnConstraint);
        }
    }


    public static void scaleGrid (int change) {
        gp.setMinSize(gp.getMinWidth() + change, gp.getMinHeight() + change);
        for (Rectangle i : iv) {
            i.setHeight(gp.getMinHeight() / gp.getColumnCount());
            i.setWidth(gp.getMinWidth() / gp.getRowCount());
        }
    }
}
