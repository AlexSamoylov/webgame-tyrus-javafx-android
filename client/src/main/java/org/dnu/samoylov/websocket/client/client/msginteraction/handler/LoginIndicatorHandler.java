package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginIndicatorMsg;

import javax.websocket.Session;

public class LoginIndicatorHandler extends MessageHandler<LoginIndicatorMsg> {
    @Override
    public Class<LoginIndicatorMsg> getMessageClass() {
        return LoginIndicatorMsg.class;
    }

    @Override
    protected void handle(Session session, LoginIndicatorMsg object) {
        ClientPresenter.getInstance().checkLogIn(object);
    }
}
