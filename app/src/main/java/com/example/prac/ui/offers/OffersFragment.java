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
import com.example.prac.Category_Pojo.Cat;
import com.example.prac.Offer_Pojo.Offer;
import com.example.prac.Offer_Pojo.Offers_Pojo;
import com.example.prac.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);
        Call<Offers_Pojo> call = service.getoffers();
        call.enqueue(new Callback<Offers_Pojo>() {
            @Override
            public void onResponse(Call<Offers_Pojo> call, retrofit2.Response<Offers_Pojo> response) {

                List<Offer> offers = response.body().getOffers();

                arrayList = new ArrayList<>();

                for(int i=0 ; i<offers.size() ; i++){

                    String offerid = offers.get(i).getOfferId();
                    String url = offers.get(i).getFilepath();
                    String discount = offers.get(i).getDiscount();
                    String code = offers.get(i).getCode();
                    String vaild = offers.get(i).getVaild();
                    String shop = offers.get(i).getShop();


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
                mAdapter = new OffersAdapter(getActivity(), arrayList);
                mRecyclerview.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Offers_Pojo> call, Throwable t) {

            }
        });

       return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   offersViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);

    }

}
