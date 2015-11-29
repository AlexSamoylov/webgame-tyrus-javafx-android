package org.dnu.samoylov.websocket.client.mvp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.dnu.samoylov.websocket.common.StaticData;
import org.dnu.samoylov.websocket.common.UserInfo;
import org.dnu.samoylov.websocket.common.msginteraction.message.NeedRefreshUserListMsg;
import org.dnu.samoylov.websocket.common.msginteraction.message.RollTheDice;


import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * FXML controller for the application.
 */
public class ClientMainViewController implements Initializable {

    @FXML
    Label gameResult;
    @FXML
    Button rollTheDice;
    @FXML
    Label myScoreIndicator;
    @FXML
    ProgressIndicator timeProgress;
    @FXML
    ListView<String> loglist;
    @FXML
    private TableView<TableRatingRow> tableRating;


    public ClientMainViewController() {
        ClientPresenter.getInstance().setMainViewController(this);
    }


    @FXML
    public void roll() {
        ClientPresenter.getInstance().sendToServer(new RollTheDice());
        endRollAbility();
    }

    public void refreshData(Collection<UserInfo> userInfoCollection) {
        data.clear();
        final UserInfo myInfo = userInfoCollection.stream().filter(userInfo -> userInfo.getLogin().equals(ClientPresenter.getInstance().getLogin())).findFirst().get();
        setMyScore(myInfo.getScore());
        data.addAll(userInfoCollection.stream().map(TableRatingRow::new).collect(Collectors.toList()));
    }

    private void setMyScore(int score) {
        myScoreIndicator.setText(String.valueOf(score) + "/" + StaticData.SCORE_FOR_WIN);
    }

    public void addInLog(String row) {
        loglist.getItems().add(row);
        final int index = loglist.getItems().size() - 1;
        loglist.scrollTo(index);
        loglist.getSelectionModel().select(index);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();

        final int MAX_WAIT = StaticData.MAX_WAIT_SEC - 100;

        currTime.set(0);
        fiveSecondsWonder.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> {
            timeProgress.setProgress(currTime.getValue() / MAX_WAIT);
            if (currTime.getValue() >= MAX_WAIT) {
                endRollAbility();
            }
            currTime.set(currTime.getValue() + 100);
        }));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
    }

    final Timeline fiveSecondsWonder = new Timeline();
    final SimpleFloatProperty currTime = new SimpleFloatProperty();

    public void myStepStart() {
        gameResult.setVisible(false);
        addInLog("мой ход");
        rollTheDice.setVisible(true);
        fiveSecondsWonder.stop();
        currTime.set(0);
        fiveSecondsWonder.play();
    }

    public void finishGame(boolean isWinner) {
        gameResult.setVisible(true);
        gameResult.setText(isWinner ? "ТЫ ВЫИГРАЛ!!!" : "СТРАТИЛ =(");
    }

    public void startGame() {
        gameResult.setVisible(true);
        gameResult.setText("НОВАЯ ИГРА");
    }

    private void endRollAbility() {
        rollTheDice.setVisible(false);
        timeProgress.setProgress(0);
        fiveSecondsWonder.stop();
        currTime.set(0);
    }


    @SuppressWarnings("unchecked")
    private void initTable() {
        tableRating.setEditable(false);

        TableColumn<TableRatingRow, String> loginColumn = new TableColumn<> ("login");
        TableColumn<TableRatingRow, Integer> scoreColumn = new TableColumn<>("score");
        TableColumn<TableRatingRow, Integer> winRateColumn = new TableColumn<>("win rate");

        loginColumn.setCellValueFactory(
                new PropertyValueFactory<>("login"));
        scoreColumn.setCellValueFactory(
                new PropertyValueFactory<>("score"));
        winRateColumn.setCellValueFactory(
                new PropertyValueFactory<>("winRate"));

        loginColumn.setPrefWidth(198);
        scoreColumn.setPrefWidth(50);
        winRateColumn.setPrefWidth(50);

        tableRating.setItems(data);
        tableRating.getColumns().addAll(loginColumn, scoreColumn, winRateColumn);
    }



    private final ObservableList<TableRatingRow> data = FXCollections.observableArrayList();

    public static class TableRatingRow {
        private final SimpleStringProperty login = new SimpleStringProperty();
        private final SimpleIntegerProperty score = new SimpleIntegerProperty();
        private final SimpleIntegerProperty winRate = new SimpleIntegerProperty();

        public TableRatingRow(String login, int score, int winRate) {
            this.login.set(login);
            this.score.set(score);
            this.winRate.set(winRate);
        }

        public TableRatingRow(UserInfo userInfo) {
            this.login.set(userInfo.getLogin());
            this.score.set(userInfo.getScore());
            this.winRate.set(userInfo.getWinRate());
        }

        public String getLogin() {
            return login.get();
        }

        public SimpleStringProperty loginProperty() {
            return login;
        }

        public void setLogin(String login) {
            this.login.set(login);
        }

        public int getScore() {
            return score.get();
        }

        public SimpleIntegerProperty scoreProperty() {
            return score;
        }

        public void setScore(int score) {
            this.score.set(score);
        }

        public int getWinRate() {
            return winRate.get();
        }

        public SimpleIntegerProperty winRateProperty() {
            return winRate;
        }

        public void setWinRate(int winRate) {
            this.winRate.set(winRate);
        }
    }
}
