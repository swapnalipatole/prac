package com.example.prac;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



    public interface Api {

        String BASE_URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_accounts/";

        @GET("cats.php")
        Call<List<Details_Pojo>> getstatus();


    }


