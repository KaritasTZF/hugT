package com.example.hbv4d.utils;

import javafx.scene.control.Alert;

public class InfoDialog extends Alert {
    public InfoDialog(String title, String header ){
        super(AlertType.INFORMATION);
        setTitle(title);
        setHeaderText(header);
    }
}
