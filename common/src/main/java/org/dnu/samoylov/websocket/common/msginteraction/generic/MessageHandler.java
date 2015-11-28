package org.dnu.samoylov.websocket.common.msginteraction.generic;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public abstract class MessageHandler {
    private final Map<String, Handler> handleMap = new HashMap<>();

    public MessageHandler() {
        initHandleMap(handleMap);
    }

    protected abstract void initHandleMap(Map<String, Handler> handleMap);

    public final void handle(Session session, String message) {
        final Message msg = Message.fromString(message);
        handleMap.get(msg.className).run(session, msg.data);
    }

}
