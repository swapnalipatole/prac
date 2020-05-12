package com.example.prac.Product_Pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product_Pojo implements Serializable
{

    @SerializedName("product")
    @Expose
    private List<Product> product = null;
    private final static long serialVersionUID = -1855253951467554289L;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

}