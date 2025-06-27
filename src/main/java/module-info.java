module com.randroulette.randomroulette {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.randroulette.randomroulette to javafx.fxml;
    exports com.randroulette.randomroulette;
}
