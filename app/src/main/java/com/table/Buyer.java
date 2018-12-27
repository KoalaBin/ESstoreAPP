package com.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Koala Bee on 2018/11/8.
 */
@Entity
public class Buyer {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String password;

    private String gender;

    private String phoneNum;

    private String address;

    private String picPath;

    @Generated(hash = 1232870667)
    public Buyer(Long id, @NotNull String name, @NotNull String password,
            String gender, String phoneNum, String address, String picPath) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.address = address;
        this.picPath = picPath;
    }

    @Generated(hash = 2117874565)
    public Buyer() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicPath() {
        return this.picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

}
