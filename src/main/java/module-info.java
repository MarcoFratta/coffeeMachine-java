module org.voitu {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;

    opens org.voitu to javafx.fxml;
    exports org.voitu;
}
