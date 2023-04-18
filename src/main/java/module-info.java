module com.example.aim_cw_test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aim_cw_test to javafx.fxml;
    exports com.example.aim_cw_test;
}