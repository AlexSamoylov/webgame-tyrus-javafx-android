package org.dnu.samoylov.websocket.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dnu.samoylov.websocket.client.client.ClientService;

/**
 * FXML controller for the application.
 */
public class HelloController {


    private static final String DEFAULT_NAME = "mysterious person";

    @FXML
    private TextField login;

    @FXML
    private TextField ip;

    @FXML
    private Label messageLabel;

    ClientService clientService = new ClientService();

    /**
     * Event handler invoked when the user submits their name.
     *
     * Invokes an asynchronous task which will communicate
     * the user's name to the server and update the message
     * label with the server's response.
     *
     * If invoked again before an in progress task completes,
     * the in progress task is cancelled and a new task is issued
     * with the current value of the name fields.
     */
    @FXML
    private void connect() {
        messageLabel.setText("");
        clientService.connect(ip.getText(), login.getText(), event -> messageLabel.setText("1"), event1 -> messageLabel.setText("2"));
    }

    @FXML
    private void sayHello() {
        messageLabel.setText("");
        clientService.sendText("text text text");
    }

}
