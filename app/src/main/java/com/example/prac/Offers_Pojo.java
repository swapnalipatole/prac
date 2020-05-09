package com.example.prac;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offers_Pojo implements Serializable
{

    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("filepath")
    @Expose
    private String filepath;
    @SerializedName("card")
    @Expose
    private String card;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("instant")
    @Expose
    private String instant;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("vaild")
    @Expose
    private String vaild;
    @SerializedName("shop")
    @Expose
    private String shop;
    @SerializedName("tc")
    @Expose
    private String tc;
    private final static long serialVersionUID = 4757543387375736753L;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVaild() {
        return vaild;
    }

    public void setVaild(String vaild) {
        this.vaild = vaild;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

}