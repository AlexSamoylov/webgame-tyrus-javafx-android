package org.dnu.samoylov.websocket.common.msginteraction;

import org.dnu.samoylov.websocket.common.UserInfo;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserInfoHelper {
    static UserInfoHelper userInfoHelper = new UserInfoHelper();
    public static UserInfoHelper getInstance() {
        return userInfoHelper;
    }
    private UserInfoHelper() {
    }

    ArrayList<UserInfo> userInfoList = new ArrayList<>();

    public void createNewUser(String login) {
            final UserInfo userInfo = getUserInfoByLogin(login);
            if (userInfo != null) {
                userInfo.setIsActive(false);
                return;
            }
        userInfoList.add(new UserInfo(login));
    }

    public void addScore(String login, int scoreDifference) {
        final UserInfo userInfo = getUserInfoByLogin(login);
        userInfo.setScore(userInfo.getScore() + scoreDifference);
    }

    public void addWin(String login) {
        final UserInfo userInfo = getUserInfoByLogin(login);
        userInfo.setWinRate(userInfo.getWinRate() + 1);
    }

    public UserInfo getUserInfoByLogin(String login) {
        if(userInfoList.size()==0) {
            return null;
        }
        try {
            return userInfoList.stream().findFirst().filter(userInfo -> userInfo.getLogin().equals(login)).get();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public ArrayList<UserInfo> getUserInfoList() {
        return userInfoList;
    }

}
