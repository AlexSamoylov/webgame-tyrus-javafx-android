package org.dnu.samoylov.websocket.common;

public class UserInfo {
        private String login;
        private int score;
        private int winRate;

        public UserInfo() {
        }

        public UserInfo(String login, int score, int winRate) {
            this.login = login;
            this.score = score;
            this.winRate = winRate;
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
    }