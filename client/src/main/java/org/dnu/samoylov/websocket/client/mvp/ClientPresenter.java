package org.dnu.samoylov.websocket.client.mvp;

import org.dnu.samoylov.websocket.client.client.ClientService;

public class ClientPresenter {
    private static ClientPresenter presenter = new ClientPresenter();

    private ClientViewController viewController;
    private ClientService clientService;

    public static ClientPresenter getInstance() {
        return presenter;
    }

    private ClientPresenter() { }


    public void setViewController(ClientViewController viewController) {
        this.viewController = viewController;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
