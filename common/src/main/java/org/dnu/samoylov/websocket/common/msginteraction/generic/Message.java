package org.dnu.samoylov.websocket.common.msginteraction.generic;

import com.google.gson.Gson;

import java.util.regex.Pattern;

public class Message {
    private static final String SEPARATOR = "|";
    private static final String split = Pattern.quote(SEPARATOR);
    private static Gson gson = new Gson();
    public static String toStringMessage(Object object) {
        return object.getClass().getName() + SEPARATOR + gson.toJson(object);
    }

    public String className;
    public String data;

    private Message(String className, String data) {
        this.className = className;
        this.data = data;
    }

    public static Message fromString(String message) {
        String[] split = message.split(Message.split);
        if (split.length == 1) {
            return new Message(split[0], "");
        }
        return new Message(split[0], split[1]);
    }


}
