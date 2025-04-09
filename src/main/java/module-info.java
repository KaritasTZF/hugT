module project {
    requires com.example.hbv4d;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires Hotel.Search;
    requires Saeti;
    opens project.Controller to javafx.fxml;
    opens project.Model to javafx.fxml;
    opens project.ui to javafx.fxml;
    exports project.Model;
    exports project.Controller;
    exports project.ui;
    exports project.util;
    opens project.util to javafx.fxml;
//    exports project;
//    opens project to javafx.fxml;
}
