package org.dnu.samoylov.websocket.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration application for using WebSockets from JavaFX.
 *
 * Creates a scene where the user can input their name, then submit
 * a request to a WebSocket server, which will respond with "Hello <name>",
 * the output of which is recorded in a label on the scene.
 *
 * As this is built to be a self-contained demonstration application,
 * a local WebSocket server is started when the application starts up and
 * the local server is shutdown when the application is stopped.
 */
public class JavaFXWebsocketDemoApp extends Application {
    private static final Logger log = LoggerFactory.getLogger(JavaFXWebsocketDemoApp.class);

    private static final String MAIN_FXML_FILE = "/fxml/hello.fxml";
    private static final String APPLICATION_STYLE_SHEET = "/styles/styles.css";



    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        launch(args);
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
        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add(APPLICATION_STYLE_SHEET);

        stage.setTitle("client");
        stage.setScene(scene);
        stage.show();
    }
}
