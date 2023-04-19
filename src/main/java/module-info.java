module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.level;
    exports com.example.app.level.roomdata;
    exports com.example.app.level.roomdata.objecttypes;
    exports com.example.app.level.roomdata.objecttypes.container;
    exports com.example.app.level.roomdata.objecttypes.light;
    exports com.example.app.level.roomdata.tiles;
    exports com.example.app.leveleditor;
    opens com.example.app.leveleditor to javafx.fxml;
}