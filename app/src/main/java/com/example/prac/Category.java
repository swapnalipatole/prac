package com.example.prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Category extends AppCompatActivity {

    TextView textView;
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<HashMap<String, String>> arrayListNews;
    String description1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String name = getIntent().getExtras().getString("text1");
        textView = findViewById(R.id.textView2);




        mRecyclerview = findViewById(R.id.RecyclerView);
        mLayoutManager = new LinearLayoutManager(Category.this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        callAPI();
    }


        private void callAPI() {//
            RequestQueue queue = Volley.newRequestQueue(this);
            //String url = "https://api.myjson.com/bins/1eits2";
            String url = "https://alkakasbe2020.000webhostapp.com/Saree_img/newpaithani.json";
            //String url = "https://chandansatyendraprasad.000webhostapp.com/tests";
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.d("=====", "=========response:" + response);
                            parseAPIResponse(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mTextView.setText("That didn't work!");
                }
            });
            queue.add(stringRequest);
        }

        private void parseAPIResponse(String response) {

            try {
                JSONObject objResponse = new JSONObject(response);
                JSONArray arrayHeadlines = objResponse.getJSONArray("headlines");
                ArrayList arrayListNews = new ArrayList<>();

                for (int i = 0; i < arrayHeadlines.length(); i++) {
                    JSONObject objItem = arrayHeadlines.getJSONObject(i);
                    String title = objItem.getString("title");
                    String imgUrl = objItem.getString("imgUrl");
                    String description = objItem.getString("description");

                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", title);
                    map.put("url", imgUrl);
                    map.put("detail", description);
                    arrayListNews.add(map);
                }

                //set adapter
             //   mAdapter = new HomeListAdapter(Category.this, arrayListNews);
             //   mRecyclerview.setAdapter(mAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

