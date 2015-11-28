package org.dnu.samoylov.websocket.client.client;

import javafx.concurrent.WorkerStateEvent;
import org.dnu.samoylov.websocket.client.client.task.ConnectionTask;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;

public class ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    HelloClientEndpoint clientEndpoint;

    public void connect(String host, String login,
            EventHandler<WorkerStateEvent> onSucceeded, EventHandler<WorkerStateEvent> onFailed) {
        ConnectionTask connectionTask = new ConnectionTask(host, login);

        connectionTask.setOnSucceeded(event -> {
            clientEndpoint = connectionTask.getValue();
            onSucceeded.handle(event);
        });

        connectionTask.setOnFailed(event -> {
                    onFailed.handle(event);
                    log.error(
                            "Unable to connect ",
                            connectionTask.getException()
                    );
                }
        );

        connectionTask.run();
    }

    public void sendText(String text) {
        clientEndpoint.sendText(text);
    }

    public void sendObject(Object object) {
        clientEndpoint.sendObject(object);
    }


}
