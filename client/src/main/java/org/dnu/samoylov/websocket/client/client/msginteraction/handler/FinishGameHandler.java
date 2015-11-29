package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.FinishGame;

import javax.websocket.Session;

public class FinishGameHandler extends MessageHandler<FinishGame> {
    @Override
    public Class<FinishGame> getMessageClass() {
        return FinishGame.class;
    }

    @Override
    protected void handle(Session session, FinishGame object) {
        final boolean equals = ClientPresenter.getInstance().getLogin().equals(object.getWinnerLogin());

        ClientPresenter.getInstance().finishGame(equals);
    }
}
