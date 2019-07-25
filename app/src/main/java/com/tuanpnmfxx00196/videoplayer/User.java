package com.tuanpnmfxx00196.videoplayer;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String userName;
    String password;
    public User(int id, String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
    public int getId(){
        return id;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
}
