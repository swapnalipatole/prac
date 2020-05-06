package com.example.prac;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.prac.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class FetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    public static String paithaniurl;
    Integer i;


    String[] imgurl =new String[10];


    @Override
    protected Void doInBackground(Void... voids) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL) // Specify your api here
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        Call<List<Details_Pojo>> call = api.getstatus();

        call.enqueue(new Callback<List<Details_Pojo>>() {
            @Override
            public void onResponse(Call<List<Details_Pojo>> call, Response<List<Details_Pojo>> response) {
                List<Details_Pojo> adslist = response.body();
                String count = adslist.get(0).getCategoryId();

                String[] caption=new String[Integer.parseInt(count)];
                String[] location= new String[Integer.parseInt(count)];


                for (i=0;i<Integer.parseInt(count);i++) {
                    location[i] = adslist.get(i).getLocation();
                    caption[i] = adslist.get(i).getCaption();
                }



                }

            @Override
            public void onFailure(Call<List<Details_Pojo>> call, Throwable t) {

                // Display your failure message here
            }
        });


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);



    }

}
