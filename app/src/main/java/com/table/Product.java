package com.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Koala Bee on 2018/11/21.
 */
@Entity
public class Product {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer quantity;
    @NotNull
    private Float price;
    @NotNull
    private String description;
    @NotNull
    private Long saleId;
    @NotNull
    private String picpath;
    @Generated(hash = 608081670)
    public Product(Long id, @NotNull String name, @NotNull Integer quantity,
            @NotNull Float price, @NotNull String description, @NotNull Long saleId,
            @NotNull String picpath) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.saleId = saleId;
        this.picpath = picpath;
    }
    @Generated(hash = 1890278724)
    public Product() {
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
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getSaleId() {
        return this.saleId;
    }
    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
    public String getPicpath() {
        return this.picpath;
    }
    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
}
