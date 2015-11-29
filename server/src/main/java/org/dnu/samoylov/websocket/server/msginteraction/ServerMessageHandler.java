package org.dnu.samoylov.websocket.server.msginteraction;

import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.generic.AllMessageHandler;
import org.dnu.samoylov.websocket.server.msginteraction.handler.LoginHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginMessage;

import java.util.Map;

public class ServerMessageHandler extends AllMessageHandler {
    @Override
    protected void initHandleMap(Map<String, MessageHandler> handleMap) {
        handleMap.put(LoginMessage.class.getName(), new  LoginHandler());
    }
}
