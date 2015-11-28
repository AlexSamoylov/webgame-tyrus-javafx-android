package org.dnu.samoylov.websocket.server.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;

/** A WebSocket server endpoint which responds to hello requests. */
@ServerEndpoint(value = "/hello")
public class HelloServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(HelloServerEndpoint.class);

    /**
     * Service a hello request.
     *
     * @param session the current websocket session.
     * @param message a name to which the server should respond hello to.
     * @return the response string "Hello " + message;
     */
    @OnMessage
    public String onMessage(Session session, String message) {
        log.debug("HelloServer received request for: " + message + " being processed for session " + session.getId());

        return "Hello " + message;
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        URI requestURI = session.getRequestURI();
        session.getBasicRemote().sendText("onOpen");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException {
        //prepare the endpoint for closing.
        session.getBasicRemote().sendText("onClose");
    }

        /**
     * Logs an error if one is detected.
     *
     * @param session the websocket session to which is in error.
     * @param throwable the detected error to be logged.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("HelloServer encountered error for session " + session.getId(), throwable);
    }
}