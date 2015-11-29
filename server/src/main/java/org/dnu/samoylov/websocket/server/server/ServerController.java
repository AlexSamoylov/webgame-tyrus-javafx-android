package org.dnu.samoylov.websocket.server.server;

import org.dnu.samoylov.websocket.common.msginteraction.generic.Message;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerController {
    private static ServerController serverController = new ServerController();

    public static ServerController getInstance() {
        return serverController;
    }

    private ServerController() {
    }

    Map<String,Session> sessionList = new HashMap<>();


    public Map<String, Session> getSessionList() {
        return sessionList;
    }

    public void send(String login, Object object) {
        try {
            ServerPresenter.getInstance().getSessionList().get(login).getBasicRemote().sendText(Message.toStringMessage(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Session session, Object object) {
        try {
            session.getBasicRemote().sendText(Message.toStringMessage(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAll(Object object) {
        for (Session session : ServerPresenter.getInstance().getSessionList().values()) {
            try {
                session.getBasicRemote().sendText(Message.toStringMessage(object));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUser(String login, Session session) {
        ServerPresenter.getInstance().getSessionList().put(login, session);
    }
}
