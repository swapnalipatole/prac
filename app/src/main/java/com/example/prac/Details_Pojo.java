package com.example.prac;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details_Pojo {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("caption")
    @Expose
    private String caption;
    private final static long serialVersionUID = 31803100531228392L;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
