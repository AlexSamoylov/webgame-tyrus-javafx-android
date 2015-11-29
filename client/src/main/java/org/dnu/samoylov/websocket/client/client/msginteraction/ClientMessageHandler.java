package org.dnu.samoylov.websocket.client.client.msginteraction;

import org.dnu.samoylov.websocket.client.client.msginteraction.handler.LoginIndicatorHandler;
import org.dnu.samoylov.websocket.client.client.msginteraction.handler.UserInfoHelperHandler;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.generic.AllMessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginIndicatorMsg;

import java.util.Map;

public class ClientMessageHandler extends AllMessageHandler {
    @Override
    protected void initHandleMap(Map<String, MessageHandler> handleMap) {
        handleMap.put(LoginIndicatorMsg.class.getName(), new LoginIndicatorHandler());
        handleMap.put(UserInfoHelper.class.getName(), new UserInfoHelperHandler());
    }
}
