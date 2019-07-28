package com.tuanpnmfxx00196.videoplayer;

public class PositionVideo {
    String user;
    int position;

    public PositionVideo(String user, int position) {
        this.user = user;
        this.position = position;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
