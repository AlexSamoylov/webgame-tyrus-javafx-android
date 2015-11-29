package org.dnu.samoylov.websocket.client.client.task;


import javafx.concurrent.Task;
import org.dnu.samoylov.websocket.client.client.ClientEndpoint;
import org.dnu.samoylov.websocket.common.StaticData;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

public class ConnectionTask extends Task<ClientEndpoint> {
    private final String SERVER_ENDPOINT_ADDRESS;

    private final String username;

    public ConnectionTask(String serverHost, String username) {
        this.username = username;
        SERVER_ENDPOINT_ADDRESS = StaticData.getServerAdress(serverHost) + StaticData.REGION_PATH;
    }


    @Override
    protected ClientEndpoint call() throws IOException, TimeoutException, DeploymentException {
        ClientEndpoint clientEndpoint = new ClientEndpoint(username);
            ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    clientEndpoint,
                    URI.create(SERVER_ENDPOINT_ADDRESS)
            );
            return clientEndpoint;
    }
}
