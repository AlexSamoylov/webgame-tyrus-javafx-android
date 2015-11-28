package org.dnu.samoylov.websocket.server.msginteraction.handler;

import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;
import org.dnu.samoylov.websocket.common.msginteraction.generic.Handler;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;

import javax.websocket.Session;

public class LoginHandler extends Handler<LoginMessage> {
    @Override
    public Class<LoginMessage> getMessageClass() {
        return LoginMessage.class;
    }

    @Override
    protected void handle(Session session, LoginMessage object) {
        ServerPresenter.getInstance().addInLog(object.getLogin() + "welcome!");
    }
}
