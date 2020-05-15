package com.example.prac;

import com.example.prac.Category_Pojo.Category_Pojo;
import com.example.prac.Offer_Pojo.Offers_Pojo;
import com.example.prac.Product_Pojo.Product_Pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

        String BASE_URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_accounts/";

        @GET("cats.php")
        Call<Category_Pojo> getcategory();

        @GET("offers.php")
        Call<Offers_Pojo> getoffers();

        @GET("product.php")
        Call<Product_Pojo> getproducts(@Query("category_id") Integer category_id);


    }


