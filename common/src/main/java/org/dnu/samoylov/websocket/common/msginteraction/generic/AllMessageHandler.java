package org.dnu.samoylov.websocket.common.msginteraction.generic;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public abstract class AllMessageHandler {
    private final Map<String, MessageHandler> handleMap = new HashMap<>();

    public AllMessageHandler() {
        initHandleMap(handleMap);
    }

    protected abstract void initHandleMap(Map<String, MessageHandler> handleMap);

    public final void handle(Session session, String message) {
        final Message msg = Message.fromString(message);
        final MessageHandler messageHandler = handleMap.get(msg.className);
        if (messageHandler!=null) {
            messageHandler.run(session, msg.data);
        }
    }
}
