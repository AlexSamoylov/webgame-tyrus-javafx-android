package org.dnu.samoylov.websocket.server.msginteraction;

import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.generic.AllMessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.NeedRefreshUserListMsg;
import org.dnu.samoylov.websocket.common.msginteraction.message.RollTheDice;
import org.dnu.samoylov.websocket.server.msginteraction.handler.LoginHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;
import org.dnu.samoylov.websocket.server.msginteraction.handler.NeedRefreshUserListHandler;
import org.dnu.samoylov.websocket.server.msginteraction.handler.RollTheDiceHandler;

import java.util.Map;

public class ServerMessageHandler extends AllMessageHandler {
    @Override
    protected void initHandleMap(Map<String, MessageHandler> handleMap) {
        handleMap.put(LoginMessage.class.getName(), new  LoginHandler());
        handleMap.put(RollTheDice.class.getName(), new RollTheDiceHandler());
        handleMap.put(NeedRefreshUserListMsg.class.getName(), new NeedRefreshUserListHandler());
    }
}
