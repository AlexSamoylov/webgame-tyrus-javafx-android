package org.dnu.samoylov.websocket.server.server;

import org.dnu.samoylov.websocket.common.StaticData;
import org.dnu.samoylov.websocket.server.msginteraction.ServerMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;

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
        session.getBasicRemote().sendText("onClose");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebServer encountered error for session " + session.getId(), throwable);
    }
}