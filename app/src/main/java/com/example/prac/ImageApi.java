package com.example.prac;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageApi {
    String URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_images/";

    @GET("paithani.jpg")
    Call<ResponseBody> getImageDetails();
}
