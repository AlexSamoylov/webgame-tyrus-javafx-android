package org.dnu.samoylov.websocket.common;

public class StaticData {
    public static final int SERVER_PORT = 4252;
    public static final String SERVER_CONTEXT_PATH = "/websocket";
    public static final String REGION_PATH = "/hello";


    public static final int MAX_WAIT_SEC = 5 * 1000;
    public static final int SCORE_FOR_WIN = 20; //100

    public static String getServerAdress(String hostname) {
        return "ws://" + hostname + ":" + SERVER_PORT + SERVER_CONTEXT_PATH;
    }
}
