package com.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Koala Bee on 2018/12/24.
 */
@Entity
public class Order {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String productName;
    @NotNull
    private Integer quantity;
    @NotNull
    private Float price;
    @NotNull
    private Float totalPrice;
    @NotNull
    private String createdDate;
    @NotNull
    private Long BuyerId;
    @NotNull
    private Long SalerId;
    private String PicPath;
    @NotNull
    private String salerName;
    @NotNull
    private Integer status;
    @NotNull
    private Boolean checked;
    @Generated(hash = 970349951)
    public Order(Long id, @NotNull String productName, @NotNull Integer quantity,
            @NotNull Float price, @NotNull Float totalPrice,
            @NotNull String createdDate, @NotNull Long BuyerId,
            @NotNull Long SalerId, String PicPath, @NotNull String salerName,
            @NotNull Integer status, @NotNull Boolean checked) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.createdDate = createdDate;
        this.BuyerId = BuyerId;
        this.SalerId = SalerId;
        this.PicPath = PicPath;
        this.salerName = salerName;
        this.status = status;
        this.checked = checked;
    }
    @Generated(hash = 1105174599)
    public Order() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Float getPrice() {
        return this.price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Float getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public Long getBuyerId() {
        return this.BuyerId;
    }
    public void setBuyerId(Long BuyerId) {
        this.BuyerId = BuyerId;
    }
    public Long getSalerId() {
        return this.SalerId;
    }
    public void setSalerId(Long SalerId) {
        this.SalerId = SalerId;
    }
    public String getPicPath() {
        return this.PicPath;
    }
    public void setPicPath(String PicPath) {
        this.PicPath = PicPath;
    }
    public String getSalerName() {
        return this.salerName;
    }
    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Boolean getChecked() {
        return this.checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }




}
