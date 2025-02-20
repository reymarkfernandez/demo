module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.base;
    requires com.opencsv;

    opens com.example.app to javafx.fxml;
    

    exports com.example.app;
}
