package com.example.prac.Offer_Pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offers_Pojo implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Offers_count")
    @Expose
    private Integer offersCount;
    @SerializedName("Offers")
    @Expose
    private List<Offer> offers = null;
    private final static long serialVersionUID = -5171804204048048235L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(Integer offersCount) {
        this.offersCount = offersCount;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}