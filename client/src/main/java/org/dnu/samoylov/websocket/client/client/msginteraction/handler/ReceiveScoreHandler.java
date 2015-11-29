package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.ReceiveScore;

import javax.websocket.Session;

public class ReceiveScoreHandler extends MessageHandler<ReceiveScore> {
    @Override
    public Class<ReceiveScore> getMessageClass() {
        return ReceiveScore.class;
    }

    @Override
    protected void handle(Session session, ReceiveScore object) {
        ClientPresenter.getInstance().receiveScore(object.getScore());
    }
}
