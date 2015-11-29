package org.dnu.samoylov.websocket.client.mvp;

import javafx.application.Platform;
import org.dnu.samoylov.websocket.client.client.ClientEndpoint;
import org.dnu.samoylov.websocket.common.msginteraction.UserInfoHelper;
import org.dnu.samoylov.websocket.common.msginteraction.message.LoginIndicatorMsg;
import org.dnu.samoylov.websocket.common.msginteraction.message.NeedRefreshUserListMsg;

public class ClientPresenter {
    private static ClientPresenter presenter = new ClientPresenter();

    private ClientStartViewController viewController;
    private Action successLoginAction;
    private ClientMainViewController mainViewController;
    private ClientEndpoint clientEndpoint;

    public static ClientPresenter getInstance() {
        return presenter;
    }

    private ClientPresenter() { }



    public String getLogin() {
        return clientEndpoint.getLogin();
    }

    public void checkLogIn(LoginIndicatorMsg indicator) {
        if (indicator.isLogin()) {
            Platform.runLater(successLoginAction::action);
        } else {
            Platform.runLater(viewController::badLogIn);
        }
    }

    public void refreshVisualizationUserInfo() {
        if(mainViewController!=null) {
            Platform.runLater(() -> mainViewController.refreshData(UserInfoHelper.getInstance().getUserInfoList()));
        }
    }


    public void sendToServer(Object object) {
        clientEndpoint.sendObject(object);
    }











    public void setViewController(ClientStartViewController viewController) {
        this.viewController = viewController;
        if(clientEndpoint!=null) {
            sendToServer(new NeedRefreshUserListMsg());
        }
    }

    public void setSuccessLoginAction(Action successLoginAction) {
        this.successLoginAction = successLoginAction;
    }

    public void setMainViewController(ClientMainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void setClientEndpoint(ClientEndpoint clientEndpoint) {
        this.clientEndpoint = clientEndpoint;
        if(mainViewController!=null) {
            sendToServer(new NeedRefreshUserListMsg());
        }
    }

    public void myStepStart() {
        Platform.runLater(mainViewController::myStepStart);
    }

    public void receiveScore(int score) {
        StringBuilder s = new StringBuilder();
        s.append("выброшено ");
        s.append(String.valueOf(score));
        switch (score) {
            case 1: s.append(" =(");
                break;
            case 6: s.append(" -_-");
                break;
            case 5: s.append("!!!");
                break;
            default:
                s.append(".");
                break;
        }

        Platform.runLater(() -> mainViewController.addInLog(s.toString()));
    }

    public void finishGame(boolean isWinner) {
        Platform.runLater(() -> mainViewController.finishGame(isWinner));
    }

    public void startGame() {
        Platform.runLater(mainViewController::startGame);
    }


    public interface Action {
        void action();
    }
}
