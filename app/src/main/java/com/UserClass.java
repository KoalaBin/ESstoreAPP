package com;

/**
 * Created by Koala Bee on 2018/11/23.
 */

public class UserClass {
    private String user_class;
    private String user_info;
    public UserClass(String user_class,String user_info){
        this.user_class = user_class;
        this.user_info = user_info;
    }
    public String getUser_class() {
        return user_class;
    }

    public String getUser_info(){
        return user_info;
    }
}
