module com.example.shapeguessinggame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.healthmarketscience.jackcess;

    opens com.example.shapeguessinggame to javafx.fxml;
    exports com.example.shapeguessinggame;
}