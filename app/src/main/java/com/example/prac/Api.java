package com.example.prac;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

        String BASE_URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_accounts/";

        @GET("cats.php")
        Call<List<Details_Pojo>> getstatus();

        @GET("product.php")
        Call<List<Offers_Pojo>> getproducts(@Query("category_id") Integer category_id);


    }


