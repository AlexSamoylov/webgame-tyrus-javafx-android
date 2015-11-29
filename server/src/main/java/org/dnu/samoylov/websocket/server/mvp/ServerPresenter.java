package org.dnu.samoylov.websocket.server.mvp;

import javafx.application.Platform;
import org.dnu.samoylov.websocket.common.UserInfo;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.message.FinishGame;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginIndicatorMsg;
import org.dnu.samoylov.websocket.server.server.ServerController;
import org.dnu.samoylov.websocket.server.server.ServerEndpoint;

import javax.websocket.Session;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        Platform.runLater(() -> viewController.addInLog(row));
    }

    public void sendObject(String login, Object obj) {
        ServerController.getInstance().send(login, obj);
    }

    public void sendAll(Object obj) {
        ServerController.getInstance().sendAll(obj);
    }
    public void sendObject(Session session, Object obj) {
        ServerController.getInstance().send(session, obj);
    }


    public void addUser(String login, Session session) {
        ServerController.getInstance().addUser(login, session);
        ServerController.getInstance().send(login, new LoginIndicatorMsg(true));
    }

    public void refreshVisualizationUserInfo() {
        Platform.runLater(() ->
                viewController.refreshData(UserInfoHelper.getInstance().getUserInfoList()));
        ServerController.getInstance().sendAll(UserInfoHelper.getInstance());
    }

    public Map<String, Session> getSessionList() {
        return ServerController.getInstance().getSessionList();
    }

    public void winner(UserInfo userInfo) {
        userInfo.setWinRate(userInfo.getWinRate() + 1);
        sendAll(new FinishGame(false, userInfo.getLogin()));

        goToStopGameState(true);
    }

    public void errorGame() {
        sendAll(new FinishGame(false, ""));

        goToStopGameState(false);
    }

    private void goToStopGameState(boolean wasFullGame) {
        Platform.runLater(() -> viewController.stopGame(wasFullGame));
    }
}
