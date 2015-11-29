package org.dnu.samoylov.websocket.server.msginteraction.handler;

import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.NeedRefreshUserListMsg;
import org.dnu.samoylov.websocket.server.mvp.ServerPresenter;

import javax.websocket.Session;

public class NeedRefreshUserListHandler extends MessageHandler<NeedRefreshUserListMsg> {
    @Override
    public Class<NeedRefreshUserListMsg> getMessageClass() {
        return NeedRefreshUserListMsg.class;
    }

    @Override
    protected void handle(Session session, NeedRefreshUserListMsg object) {
        ServerPresenter.getInstance().sendObject(session, UserInfoHelper.getInstance());
    }
}