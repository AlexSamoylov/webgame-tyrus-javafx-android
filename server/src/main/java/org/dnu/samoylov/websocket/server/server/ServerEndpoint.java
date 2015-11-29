package org.dnu.samoylov.websocket.server.server;

import org.dnu.samoylov.websocket.common.StaticData;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.generic.Message;
import org.dnu.samoylov.websocket.server.msginteraction.ServerMessageHandler;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@javax.websocket.server.ServerEndpoint(value = StaticData.REGION_PATH)
public class ServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ServerEndpoint.class);
    ServerMessageHandler serverMessageHandler = new ServerMessageHandler();

    @OnMessage
    public String onMessage(Session session, String message) {
        log.debug("WebServer received request for: " + message + " being processed for session " + session.getId());
        serverMessageHandler.handle(session, message);
        return "Hello " + message;
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("onOpen");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException {
        String login = null;
        for (Map.Entry<String, Session> sessionEntry : ServerPresenter.getInstance().getSessionList().entrySet()) {
            if (session.equals(sessionEntry.getValue())) {
                login = sessionEntry.getKey();
            }
        }
        ServerPresenter.getInstance().getSessionList().values().remove(session);
        if(login != null) {
            final UserInfoHelper userInfoHelper = UserInfoHelper.getInstance();
            userInfoHelper.getUserInfoByLogin(login).setIsOnline(false);
            ServerPresenter.getInstance().refreshVisualizationUserInfo();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebServer encountered error for session " + session.getId(), throwable);
    }
}