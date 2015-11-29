package org.dnu.samoylov.websocket.server.msginteraction.handler;

import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.ReceiveScore;
import org.dnu.samoylov.websocket.common.msginteraction.message.RollTheDice;
import org.dnu.samoylov.websocket.server.GameInfo;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;

import javax.websocket.Session;

public class RollTheDiceHandler extends MessageHandler<RollTheDice> {
    @Override
    public Class<RollTheDice> getMessageClass() {
        return RollTheDice.class;
    }

    @Override
    protected void handle(Session session, RollTheDice object) {
        final ReceiveScore score = new ReceiveScore();
        ServerPresenter.getInstance().sendObject(session, score);
        GameInfo.getInstance().setReceived(score);
    }
}
