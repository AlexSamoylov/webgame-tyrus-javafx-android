package org.dnu.samoylov.websocket.server.mvp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dnu.samoylov.websocket.common.UserInfo;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ServerViewController implements Initializable {

    @FXML
    Label serverIp;
    @FXML
    ListView<String> loglist;

    @FXML
    private TableView<TableRatingRow> tableRating;

    public ServerViewController() {
        ServerPresenter.getInstance().setViewController(this);
    }

    public void addInLog(String row) {
        loglist.getItems().add(row);
    }

    public void refreshData(Collection<UserInfo> userInfoCollection) {
        data.clear();
        data.addAll(userInfoCollection.stream().map(TableRatingRow::new).collect(Collectors.toList()));
    }

    @FXML
    public void btn() {
        addInLog("btn");
    }



    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addInLog("start success");
        initTable();

        try {
            InetAddress iAddress = InetAddress.getLocalHost();
            String currentIp = iAddress.getHostAddress();
            serverIp.setText("SERVER: " + iAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }






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
