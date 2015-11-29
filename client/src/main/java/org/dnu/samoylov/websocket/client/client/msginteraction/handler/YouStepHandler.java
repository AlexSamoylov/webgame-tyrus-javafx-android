package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;

import javax.websocket.Session;

public class YouStepHandler extends MessageHandler<YouStepHandler> {
    @Override
    public Class<YouStepHandler> getMessageClass() {
        return YouStepHandler.class;
    }

    @Override
    protected void handle(Session session, YouStepHandler object) {
        ClientPresenter.getInstance().myStepStart();
    }
}
