package org.dnu.samoylov.websocket.client.mvp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dnu.samoylov.websocket.client.client.ClientService;

/**
 * FXML controller for the application.
 */
public class ClientStartViewController {
    @FXML
    private TextField login;

    @FXML
    private TextField ip;

    @FXML
    private Label messageLabel;

    ClientService clientService = new ClientService();

    public ClientStartViewController() {
        ClientPresenter.getInstance().setViewController(this);


    }

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
        clientService.connect(ip.getText(), login.getText(), event -> {}, event1 -> {});
    }

    @FXML
    private void sayHello() {
        messageLabel.setText("");
        clientService.sendText("text text text");
    }

    public void badLogIn() {
        messageLabel.setText("bad login");
    }
}
