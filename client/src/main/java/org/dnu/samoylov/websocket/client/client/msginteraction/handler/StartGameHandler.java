package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.StartGame;

import javax.websocket.Session;

public class StartGameHandler extends MessageHandler<StartGame> {
    @Override
    public Class<StartGame> getMessageClass() {
        return StartGame.class;
    }

    @Override
    protected void handle(Session session, StartGame object) {
        ClientPresenter.getInstance().startGame();
    }
}
