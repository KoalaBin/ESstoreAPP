package com.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Koala Bee on 2018/11/8.
 */
@Entity
public class Admin {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String password;

    @Generated(hash = 2022052512)
    public Admin(Long id, @NotNull String name, @NotNull String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Generated(hash = 1708792177)
    public Admin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
