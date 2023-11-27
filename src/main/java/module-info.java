module com.example.segaoop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.segaoop to javafx.fxml;
    exports com.example.segaoop;
}