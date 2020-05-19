package com.example.prac.ui.category;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prac.Api;
import com.example.prac.Preference;
import com.example.prac.Product_Pojo.Product;
import com.example.prac.Product_Pojo.Product_Pojo;
import com.example.prac.R;
import com.example.prac.ui.home.HomeCategoryAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryFragment extends Fragment {

    ArrayList<HashMap<String, String>> arrayList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private CategoryViewModel categoryViewModel;
    TextView textView,productcount;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_category, container, false);

        textView = root.findViewById(R.id.product_category);
        productcount = root.findViewById(R.id.totalproduct);


        //String name = getActivity().getIntent().getStringExtra("caption");
        textView.setText(new StringBuilder().append(Preference.categoryname).append(" Saree Category").toString());

        mRecyclerview = root.findViewById(R.id.product_recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(linearLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);
        Call<Product_Pojo> call = service.getproducts(Integer.parseInt(Preference.categoryid));
        call.enqueue(new Callback<Product_Pojo>() {
            @Override
            public void onResponse(Call<Product_Pojo> call, Response<Product_Pojo> response) {
                List<Product> products = response.body().getProduct();

                arrayList = new ArrayList<>();

                for(int i=0 ; i<products.size() ; i++){

                    String imgUrl = products.get(i).getProductPath();
                    String categoryid = products.get(i).getCategoryId();
                    String productid = products.get(i).getProductId();
                    String discountedprice = products.get(i).getDiscountedPrice();
                    String originalprice = products.get(i).getOriginalPrice();
                    String sareename = products.get(i).getProductName();

                    HashMap<String, String> map = new HashMap<>();

                    map.put("productid", productid);
                    map.put("categoryid", categoryid);
                    map.put("discountedprice",discountedprice);
                    map.put("originalprice",originalprice);
                    map.put("sareename",sareename);
                    map.put("url", imgUrl);
                    map.put("quantity","1");

                    arrayList.add(map);

                }
                Preference.productcount = products.size();
                productcount.setText(new StringBuilder().append(Preference.productcount).append(" Items").toString());

                mAdapter = new ProductAdapter(getActivity(), arrayList);
                mRecyclerview.setAdapter(mAdapter);
                savecart();

            }

            @Override
            public void onFailure(Call<Product_Pojo> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(),
                        "Failed",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        return root;
    }

    private void savecart() {

    }

    public Fragment MyFragment() {
        Fragment fragment;
        fragment = CategoryFragment.this;

        return fragment;
    }
}
