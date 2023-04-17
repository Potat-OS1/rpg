module com.example.app {
    requires javafx.controls;
    requires java.desktop;

    exports com.example.app;
    exports com.example.app.level;
    exports com.example.app.level.roomdata;
    exports com.example.app.level.roomdata.objecttypes;
    exports com.example.app.level.roomdata.objecttypes.container;
    exports com.example.app.level.roomdata.objecttypes.light;
    exports com.example.app.level.roomdata.tiles;
}