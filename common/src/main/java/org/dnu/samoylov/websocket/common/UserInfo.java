package org.dnu.samoylov.websocket.common;

public class UserInfo {
    private String login;
    private int score;
    private int winRate;
    private boolean isActive;

    public UserInfo() {
    }

    public UserInfo(String login) {
        this.login = login;
        this.score = 0;
        this.winRate = 0;
        this.isActive = true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}