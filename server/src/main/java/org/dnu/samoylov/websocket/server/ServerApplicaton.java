package org.dnu.samoylov.websocket.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dnu.samoylov.websocket.server.server.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;

/**
 * Demonstration application for using WebSockets from JavaFX.
 *
 * Creates a scene where the user can input their name, then submit
 * a request to a WebSocket webServer, which will respond with "Hello <name>",
 * the output of which is recorded in a label on the scene.
 *
 * As this is built to be a self-contained demonstration application,
 * a local WebSocket webServer is started when the application starts up and
 * the local webServer is shutdown when the application is stopped.
 */
public class ServerApplicaton extends Application {
    private static final Logger log = LoggerFactory.getLogger(ServerApplicaton.class);

    private static final String MAIN_FXML_FILE = "/fxml/hello.fxml";
    private static final String APPLICATION_STYLE_SHEET = "/styles/styles.css";

    private WebServer webServer;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /** Starts a local websocket webServer for testing purposes */
    public void init() throws DeploymentException {
        webServer = new WebServer();
        webServer.start();
    }

    /** Stops the local websocket webServer. */
    public void stop() throws DeploymentException {
        webServer.stop();
    }

    /** Shows the main application scene. */
    public void start(Stage stage) throws Exception {
        log.info("Starting Hello JavaFX WebSocket demonstration application");

        log.debug("Loading FXML for main view from: {}", MAIN_FXML_FILE);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(
                getClass().getResourceAsStream(
                        MAIN_FXML_FILE
                )
        );

        log.debug("Showing JavaFX scene");
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add(APPLICATION_STYLE_SHEET);

        stage.setTitle("WebServer");
        stage.setScene(scene);
        stage.show();
    }
}
