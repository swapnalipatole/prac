package com.example.prac.ui.offers;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.prac.Api;
import com.example.prac.HomeActivity;
import com.example.prac.OffersAdapter;
import com.example.prac.Offers_Pojo;
import com.example.prac.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

public class OffersFragment extends Fragment {

    private OffersViewModel offersViewModel;
    private RecyclerView mRecyclerview;
    ArrayList<HashMap<String, String>> arrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    public static OffersFragment newInstance() {
        return new OffersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_offers, container, false);



        super.onCreate(savedInstanceState);
        mRecyclerview = root.findViewById(R.id.offer_recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 2);
        mRecyclerview.setLayoutManager(gridLayoutManager);


        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.BASE_URL + "offers.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("=====", "=========response:" + response);
                        try {
                            JSONObject objResponse = new JSONObject(response);
                            JSONArray arrayHeadlines = objResponse.getJSONArray("Offers");
                            arrayList = new ArrayList<>();

                            for (int i = 0; i < arrayHeadlines.length(); i++) {
                                JSONObject objItem = arrayHeadlines.getJSONObject(i);
                                //String title = objItem.getString("title");
                                String offerid = objItem.getString("offer_id");
                                String url = objItem.getString("filepath");
                                String discount = objItem.getString("discount");
                                String code = objItem.getString("code");
                                String vaild = objItem.getString("vaild");
                                String shop = objItem.getString("shop");


                                HashMap<String, String> map = new HashMap<>();
                                // map.put("title", title);
                                map.put("url", url);
                                map.put("offer_id" , offerid);
                                map.put("discount",discount);
                                map.put("code",code);
                                map.put("vaild",vaild);
                                map.put("shop",shop);


                                arrayList.add(map);
                            }

                            //set adapter
                            mAdapter = new OffersAdapter(getActivity(), arrayList);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        // TODO: Use the ViewModel



    }

}
