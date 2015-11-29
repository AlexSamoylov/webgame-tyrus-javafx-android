package org.dnu.samoylov.websocket.client.client;

import org.dnu.samoylov.websocket.client.client.msginteraction.ClientMessageHandler;
import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.Message;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;

/**
 * WebSocket client endpoint to the hello service.
 *
 * Submits a login to the hello service on opening a connection.
 *
 * Expects a single response from the server which the server is to
 * send immediately upon receiving the request login.
 *
 * `getResponse()` is a blocking method which can be used to retrieve the response to the request.
 *
 * This endpoint is structured to only be used once per client connection.
 * (i.e. create a new endpoint instance for every client connection).
 */
@javax.websocket.ClientEndpoint
public class ClientEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ClientEndpoint.class);

    private final String login;

    ClientMessageHandler messageHandler = new ClientMessageHandler();

    Session session;
    public ClientEndpoint(String login) {
        this.login = login;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        ClientPresenter.getInstance().setClientEndpoint(this);

        log.debug("Sending request: '" + login + "' with session " + session.getId());

        sendObject(new LoginMessage(login));
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        messageHandler.handle(session, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Communication error, saying hello to '" + login + "' with session " + session.getId(), throwable);

        this.session = null;
    }

    @OnClose
    public void onClose(Session session) {
            this.session = null;
    }

    public void sendObject(Object obj) {
        try {
            this.session.getBasicRemote().sendText(Message.toStringMessage(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendText(String text) {
        try {
            this.session.getBasicRemote().sendText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }
}