package org.dnu.samoylov.websocket.server.mvp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerViewController implements Initializable {

    @FXML
    ListView<String> loglist;

    public ServerViewController() {
        ServerPresenter.getInstance().setViewController(this);
    }

    public void addInLog(String row) {
        loglist.getItems().add(row);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addInLog("start success");
    }


    @FXML
    public void btn() {
        addInLog("btn");
    }
}
