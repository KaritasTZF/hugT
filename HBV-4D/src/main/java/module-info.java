module com.example.hbv4d {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.hbv4d.ui to javafx.fxml;
    exports com.example.hbv4d.ui;
    exports com.example.hbv4d.objects;
    opens com.example.hbv4d.objects to javafx.fxml;
}
