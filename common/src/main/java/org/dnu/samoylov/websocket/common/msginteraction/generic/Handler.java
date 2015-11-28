package org.dnu.samoylov.websocket.common.msginteraction.generic;

import com.google.gson.Gson;

import javax.websocket.Session;

public abstract class Handler<T> {
    private Gson gson = new Gson();

    public final void run(Session session, String message) {
        final T object = gson.fromJson(message, getMessageClass());
        handle(session, object);
    }

    public abstract Class<T> getMessageClass();
    protected abstract void handle(Session session, T object);
}