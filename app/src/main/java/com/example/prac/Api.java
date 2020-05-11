package com.example.prac;

import com.example.prac.Category_Pojo.Category_Pojo;
import com.example.prac.Offer_Pojo.Offers_Pojo;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

        String BASE_URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_accounts/";

        @GET("cats.php")
        Call<Category_Pojo> getcategory();

        @GET("offers.php")
        Call<Offers_Pojo> getoffers();


    }


