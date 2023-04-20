package com.example.app.leveleditor;

import com.example.app.AssetsController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LevelScene {
    static GridPane gp = new GridPane();
    static ArrayList<ImageView> iv = new ArrayList<>();
    public static GridPane scene() {
        gp.setPrefSize(350, 350);
        resizeGrid(10, 10);
        //gp.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        populateGridPane();
        //resizeGrid(11, 11);
        return gp;
    }

    private static void populateGridPane () {
        for (int d = 0; d < gp.getRowCount(); d++) {
            for (int e = 0; e < gp.getColumnCount(); e++) {
                //350/gp.getRowCount(), 350/gp.getColumnCount()
                ImageView r = new ImageView();
                if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                    r.setImage(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/redmat.png"), 350/gp.getRowCount(), 350/gp.getColumnCount(), false, false));
                }
                else {
                    r.setImage(new Image(AssetsController.class.getResourceAsStream("/tiledata/materials/blackmat.png"), 350/gp.getRowCount(), 350/gp.getColumnCount(), false, false));
                }
                iv.add(r);
                VBox.setVgrow(r, Priority.SOMETIMES);
                HBox.setHgrow(r, Priority.SOMETIMES);
                gp.add(r, d, e);
            }
        }
    }

    public static void resizeGrid (int rowAmount, int columnAmount) {
        GridPane newGp = new GridPane();
        newGp.setPrefSize(350, 350);
        double percentage = 100.0 / rowAmount;
        for (int a = 0; a < rowAmount; a++) {
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPercentHeight(percentage);
            newGp.getRowConstraints().add(rowConstraint);
        }
        for (int b = 0; b < columnAmount; b++) {
            ColumnConstraints columnConstraint = new ColumnConstraints();
            columnConstraint.setPercentWidth(percentage);
            newGp.getColumnConstraints().add(columnConstraint);
        }

        int tempx;
        int tempy;
        for (int c = 0; c < gp.getChildren().size(); c++) {
            tempx = gp.getRowIndex(gp.getChildren().get(c));
            tempy = gp.getColumnIndex(gp.getChildren().get(c));
            System.out.println(tempx + "  " + tempy);
            newGp.add(gp.getChildren().get(c), tempx, tempy);
        }
        gp = newGp;
    }

    public static void scaleGrid (int change) {
        gp.setMinSize(gp.getMinWidth() + change, gp.getMinHeight() + change);
        for (ImageView rec : iv) {
            //rec.set
        }
    }
}
