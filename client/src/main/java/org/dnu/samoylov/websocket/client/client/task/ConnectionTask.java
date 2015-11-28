package org.dnu.samoylov.websocket.client.client.task;


import javafx.concurrent.Task;
import org.dnu.samoylov.websocket.client.client.HelloClientEndpoint;
import org.dnu.samoylov.websocket.common.StaticData;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

public class ConnectionTask extends Task<HelloClientEndpoint> {
    private final String SERVER_ENDPOINT_ADDRESS;

    private final String username;

    public ConnectionTask(String serverHost, String username) {
        this.username = username;
        SERVER_ENDPOINT_ADDRESS = StaticData.getServerAdress(serverHost) + StaticData.REGION_PATH;
    }


    @Override
    protected HelloClientEndpoint call() throws IOException, TimeoutException, DeploymentException {
        HelloClientEndpoint clientEndpoint = new HelloClientEndpoint(username);
            ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    clientEndpoint,
                    URI.create(SERVER_ENDPOINT_ADDRESS)
            );
            return clientEndpoint;
    }
}
