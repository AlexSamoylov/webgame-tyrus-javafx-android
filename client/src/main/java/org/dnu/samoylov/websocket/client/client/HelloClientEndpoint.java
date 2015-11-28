package org.dnu.samoylov.websocket.client.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

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
@ClientEndpoint
public class HelloClientEndpoint {
    private static final Logger log = LoggerFactory.getLogger(HelloClientEndpoint.class);

    private final String login;
    private       String response;
    private       Throwable exception;

    private final CountDownLatch messageLatch = new CountDownLatch(1);

    private static final int REQUEST_TIMEOUT_SECS = 10;
    Session session;
    public HelloClientEndpoint(String login) {
        this.login = login;
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            this.session = session;
            log.debug("Sending request: '" + login + "' with session " + session.getId());

            session.getBasicRemote().sendText(login);
        } catch (IOException e) {
            log.error("Unable to connect to hello server: ", e);
        }
    }

    @OnMessage
    public void processResponse(Session session, String message) {
        log.debug("Received response: '" + message + "' for request: '" + login + "' with session " + session.getId());

        if(this.session != session) {
            this.session = session;
        }
        response = message;
        messageLatch.countDown();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Communication error, saying hello to '" + login + "' with session " + session.getId(), throwable);
        exception = throwable;
        messageLatch.countDown();

        this.session = null;
    }

    @OnClose
    public void onClose(Session session) {
            this.session = null;
    }

    public void sendText(String text) {
        try {
            this.session.getBasicRemote().sendText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}