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
    @Generated(hash = 284644542)
    public Buyer(Long id, @NotNull String name, @NotNull String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
}
