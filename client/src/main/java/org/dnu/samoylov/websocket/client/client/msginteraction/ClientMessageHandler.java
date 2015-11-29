package org.dnu.samoylov.websocket.client.client.msginteraction;

import org.dnu.samoylov.websocket.client.client.msginteraction.handler.*;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.generic.MessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.generic.AllMessageHandler;
import org.dnu.samoylov.websocket.common.msginteraction.message.*;

import java.util.Map;

public class ClientMessageHandler extends AllMessageHandler {
    @Override
    protected void initHandleMap(Map<String, MessageHandler> handleMap) {
        handleMap.put(LoginIndicatorMsg.class.getName(), new LoginIndicatorHandler());
        handleMap.put(UserInfoHelper.class.getName(), new UserInfoHelperHandler());
        handleMap.put(YouStepMsg.class.getName(), new YouStepHandler());
        handleMap.put(ReceiveScore.class.getName(), new ReceiveScoreHandler());
        handleMap.put(FinishGame.class.getName(), new FinishGameHandler());
        handleMap.put(StartGame.class.getName(), new StartGameHandler());
    }
}
