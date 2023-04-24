package com.example.app.leveleditor;

import com.example.app.AssetsController;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class LevelScene {
    public static GridPane gp = new GridPane();
    static ArrayList<Rectangle> iv = new ArrayList<>();
    static StackPane[][] temp;
    public static int[][] imageList;
    private static int[][] tempImageList;

    public static GridPane scene() {
        gp.setMinSize(350, 350);
        gp.setStyle(
                "-fx-border-color: black;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n"
        );
        gp.setBackground(new Background(new BackgroundFill(new Color(0.0, 0.0, 0.0, 0.2), null, null)));
        setConstraints(10);
        imageList = new int[10][10];
        populateGridPane();
        return gp;
    }

    public static void resizeGridPane(int num) {
        //resizes the grid pane by storing the values, emptying the grid, then replacing the constraints, then repopulating.
        storePaneValues();
        gp.getChildren().clear();
        gp.setMinSize(350, 350);
        iv.clear();
        gp.getRowConstraints().remove(0, gp.getRowCount());
        gp.getColumnConstraints().remove(0, gp.getColumnCount());
        setConstraints(num);
        storeImageValues();
        populateGridPaneWithValues();
    }

    private static void storePaneValues() {
        //this stores the temporary values of the grid.
        temp = new StackPane[gp.getRowCount()][gp.getColumnCount()];
        tempImageList = new int[gp.getRowCount()][gp.getColumnCount()];
        for (int a = 0; a < gp.getColumnCount(); a++) {
            for (int b = 0; b < gp.getRowCount(); b++) {
                temp[a][b] = (StackPane) getNodeByRowColumnIndex(b, a, gp);
            }
        }
    }

    private static void storeImageValues () {
        //this stores the values of the grid in a way to save to file.
        tempImageList = new int[gp.getRowCount()][gp.getColumnCount()];
        for (int a = 0; a < gp.getColumnCount(); a++) {
            for (int b = 0; b < gp.getRowCount(); b++) {
                try {
                    tempImageList[a][b] = imageList[a][b];
                }
                catch (Exception ignored) {

                }
            }
        }
        imageList = tempImageList;

        System.out.println("printing list");
        for (int a = 0; a < tempImageList.length; a++) {
            for (int b = 0; b < tempImageList[a].length; b++) {
                System.out.print(tempImageList[b][a] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        for (Node sp : gridPane.getChildren()) {
            if (gridPane.getRowIndex(sp) == row && gridPane.getColumnIndex(sp) == column) {
                return sp;
            }
        }
        return new StackPane();
    }

    private static void turnCellToBrush (Rectangle r, int x, int y) {
        r.setOnMouseClicked(Event-> {
            r.setFill(new ImagePattern(LevelEditorController.getBrushImage()));
            imageList[x][y] = LevelEditorController.brushIndex;
        });
    }

    private static void populateGridPaneWithValues () {
        //when the grid grows, it needs to add cells to accomodate. the grid is also empty as this point.
        //so it loops through, and if it fails to make something it makes a default tile.
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
                    p.setBorder(new Border(new BorderStroke(new Color(0.2, 0.2, 0.2, 1.0), BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(1))));
                    p.getChildren().add(r);
                    r.setFill(new ImagePattern(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/redmat.png"))));
                    r.setStrokeWidth(3);
                    turnCellToBrush(r, d, e);
                }
                iv.add(r);
                gp.add(p, d, e);
            }
        }
    }

    private static void populateGridPane () {
        // for initial population of the grid pane, its only called once so i think i could probably get away with using the other method and just tweaking it and deleting this.
        // that'll be for later
        for (int d = 0; d < gp.getRowCount(); d++) {
            for (int e = 0; e < gp.getColumnCount(); e++) {
                Rectangle r = new Rectangle(gp.getMinWidth()/gp.getRowCount(), gp.getMinWidth()/gp.getColumnCount());
                r.setFill(new ImagePattern(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/redmat.png"))));
                StackPane p = new StackPane();
                p.setBorder(new Border(new BorderStroke(new Color(0.2, 0.2, 0.2, 1.0), BorderStrokeStyle.DASHED, CornerRadii.EMPTY, new BorderWidths(1))));
                p.getChildren().add(r);
                r.setStrokeWidth(3);
                turnCellToBrush(r, d, e);
                iv.add(r);
                gp.add(p, d, e);
            }
        }
    }

    public static void setConstraints (int scale) {
        //enforces the grid size.
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
        // makes the images in the grid the size they should be.
        gp.setMinSize(gp.getMinWidth() + change, gp.getMinHeight() + change);
        for (Rectangle i : iv) {
            i.setHeight(gp.getMinHeight() / gp.getColumnCount());
            i.setWidth(gp.getMinWidth() / gp.getRowCount());
        }
    }
}
