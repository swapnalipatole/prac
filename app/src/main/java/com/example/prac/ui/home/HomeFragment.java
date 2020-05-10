package com.example.prac.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prac.Api;
import com.example.prac.R;
import com.example.prac.ui.category.CategoryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;


public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    ArrayList<HashMap<String, String>> arrayList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    public static String caption1;


    public static void ClickedEvent(Context mContext, String categoryid, String detail) {

        CategoryFragment categoryfragment = new CategoryFragment();

    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);



        super.onCreate(savedInstanceState);





        mRecyclerview = root.findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 3);
        mRecyclerview.setLayoutManager(gridLayoutManager);


        //callAPI();
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.BASE_URL + "cats.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("=====", "=========response:" + response);
                        try {
                            JSONObject objResponse = new JSONObject(response);
                            JSONArray arrayHeadlines = objResponse.getJSONArray("cats");
                            arrayList = new ArrayList<>();

                            for (int i = 0; i < arrayHeadlines.length(); i++) {
                                JSONObject objItem = arrayHeadlines.getJSONObject(i);
                                //String title = objItem.getString("title");
                                String categoryid = objItem.getString("category_id");
                                String imgUrl = objItem.getString("location");
                                String description = objItem.getString("caption");


                                HashMap<String, String> map = new HashMap<>();
                                // map.put("title", title);
                                map.put("url", imgUrl);
                                map.put("detail", description);
                                map.put("categoryid", categoryid);


                                arrayList.add(map);
                            }

                            //set adapter
                            mAdapter = new HomeCategoryAdapter(getActivity(), arrayList);
                            mRecyclerview.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);




        return root;
    }


    private void callAPI() {//


        }

        private void parseAPIResponse(String response) {

            try {
                JSONObject jo = new JSONObject(response);
                JSONArray ja = jo.getJSONArray("cats");
                arrayList = new ArrayList<>();

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject objItem = ja.getJSONObject(i);
                    //String title = objItem.getString("title");
                    String categoryid = objItem.getString("category_id");
                    String location = objItem.getString("location");
                    String caption = objItem.getString("caption");


                    HashMap<String, String> map = new HashMap<>();
                    // map.put("title", title);
                    map.put("categoryid",categoryid);
                    map.put("url", location);
                    map.put("detail", caption);


                    arrayList.add(map);
                }

                //set adapter
                mAdapter = new HomeCategoryAdapter(getActivity(), arrayList);
                mRecyclerview.setAdapter(mAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



}