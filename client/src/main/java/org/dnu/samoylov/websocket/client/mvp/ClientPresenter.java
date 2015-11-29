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




    public void checkLogIn(LoginIndicatorMsg indicator) {
        if (indicator.isLogin()) {
            Platform.runLater(successLoginAction::action);
        } else {
            Platform.runLater(viewController::badLogIn);
        }
    }

    public void refreshVisualizationUserInfo() {
        if(mainViewController!=null) {
            mainViewController.refreshData(UserInfoHelper.getInstance().getUserInfoList());
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


    public interface Action {
        void action();
    }
}
