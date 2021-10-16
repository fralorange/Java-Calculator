module com.example.javacalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javacalculator to javafx.fxml;
    exports com.example.javacalculator;
}