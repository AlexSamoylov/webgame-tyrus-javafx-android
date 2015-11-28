package org.dnu.samoylov.websocket.server.mvp;

import javafx.application.Platform;

public class ServerPresenter {
    private static ServerPresenter presenter = new ServerPresenter();

    private ServerViewController viewController;

    public static ServerPresenter getInstance() {
        return presenter;
    }

    private ServerPresenter() { }


    public void setViewController(ServerViewController viewController) {
        this.viewController = viewController;
    }


    public void addInLog(String row) {
        Platform.runLater(()-> viewController.addInLog(row));
    }
}
