package com.example.hbv4d.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class CancelDialog extends Alert {


    private static final String CONTINUE_BOOKING = "Continue";
    public static final ButtonType ATYPE = new ButtonType(CONTINUE_BOOKING,
            ButtonBar.ButtonData.OK_DONE);
    private static final String CANCEL_BOOKING = "Cancel";
    public static final ButtonType BTYPE = new ButtonType(CANCEL_BOOKING,
            ButtonBar.ButtonData.CANCEL_CLOSE);


    public CancelDialog(String title, String header, String question) {
        super(AlertType.NONE, question, ATYPE, BTYPE);
        setTitle(title);
        setHeaderText(header);
    }
}
