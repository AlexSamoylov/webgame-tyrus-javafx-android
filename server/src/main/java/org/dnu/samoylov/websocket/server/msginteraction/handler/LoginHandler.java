package org.dnu.samoylov.websocket.server.msginteraction.handler;

import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginIndicatorMsg;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;
import org.dnu.samoylov.websocket.server.server.ServerController;

import javax.websocket.Session;
import java.io.IOException;

public class LoginHandler extends MessageHandler<LoginMessage> {
    @Override
    public Class<LoginMessage> getMessageClass() {
        return LoginMessage.class;
    }

    @Override
    protected void handle(Session session, LoginMessage object) {
        final boolean isAddUser = UserInfoHelper.getInstance().createNewUser(object.getLogin());
        if (isAddUser) {
            ServerPresenter.getInstance().addInLog(object.getLogin() + " welcome!");
            ServerPresenter.getInstance().addUser(object.getLogin(), session);
            ServerPresenter.getInstance().refreshVisualizationUserInfo();
        } else {
            try {
                ServerController.getInstance().send(session, new LoginIndicatorMsg(false));
                session.close();
            } catch (IOException ignored) {
            }
        }
    }
}
