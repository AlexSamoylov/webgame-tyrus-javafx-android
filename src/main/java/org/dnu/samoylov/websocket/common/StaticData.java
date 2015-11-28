package org.dnu.samoylov.websocket.common;

public class StaticData {
    public static final int SERVER_PORT = 8025;
    public static final String SERVER_CONTEXT_PATH = "/websocket";

    public static String getServerAdress(String hostname) {
        return "ws://" + hostname + ":" + SERVER_PORT + SERVER_CONTEXT_PATH;
    }
}
