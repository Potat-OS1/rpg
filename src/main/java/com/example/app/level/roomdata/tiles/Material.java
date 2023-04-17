package com.example.app.level.roomdata.tiles;

import com.example.app.AssetsController;
import javafx.scene.image.Image;

public enum Material {
    GRAYSTONE(AssetsController.material[1]),
    BLACKSTONE(AssetsController.material[2]),
    CLOTH(AssetsController.material[0]);

    protected Image mat;

    Material (Image mat) {
        this.mat = mat;
    }

    public Image getMat() {
        return mat;
    }
}
