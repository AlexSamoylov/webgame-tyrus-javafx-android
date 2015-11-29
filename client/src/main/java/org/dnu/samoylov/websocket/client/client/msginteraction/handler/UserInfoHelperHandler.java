package org.dnu.samoylov.websocket.client.client.msginteraction.handler;

import org.dnu.samoylov.websocket.client.mvp.ClientPresenter;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;

import javax.websocket.Session;

public class UserInfoHelperHandler extends MessageHandler<UserInfoHelper> {
    @Override
    public Class<UserInfoHelper> getMessageClass() {
        return UserInfoHelper.class;
    }

    @Override
    protected void handle(Session session, UserInfoHelper object) {
        UserInfoHelper.getInstance().getUserInfoList().clear();
        UserInfoHelper.getInstance().getUserInfoList().addAll(object.getUserInfoList());

        ClientPresenter.getInstance().refreshVisualizationUserInfo();
    }
}
