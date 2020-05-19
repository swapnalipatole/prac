package com.example.prac.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prac.Api;
import com.example.prac.Category_Pojo.Cat;
import com.example.prac.Category_Pojo.Category_Pojo;
import com.example.prac.HomeActivity;
import com.example.prac.Preference;
import com.example.prac.R;
import com.example.prac.ui.category.CategoryFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static ArrayList<HashMap<String, String>> arrayList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    public static String caption1;










    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {




        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);



        super.onCreate(savedInstanceState);


        final Fragment fragment = new CategoryFragment().MyFragment();
        final Fragment frag = this;

        mRecyclerview = root.findViewById(R.id.recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(root.getContext(), 3);
        mRecyclerview.setLayoutManager(gridLayoutManager);





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api service = retrofit.create(Api.class);
        Call<Category_Pojo> call = service.getcategory();
        call.enqueue(new Callback<Category_Pojo>() {
            @Override
            public void onResponse(Call<Category_Pojo> call, retrofit2.Response<Category_Pojo> response) {
                List<Cat> category = response.body().getCats();
                arrayList = new ArrayList<>();

                for(int i=0 ; i<category.size() ; i++){

                    String imgUrl = category.get(i).getLocation();
                    Preference.categoryid = category.get(0).getCategoryId();
                    Preference.categoryname = category.get(0).getCaption();
                    String categoryid = category.get(i).getCategoryId();
                    String description = category.get(i).getCaption();

                    HashMap<String, String> map = new HashMap<>();
                    // map.put("title", title);
                    map.put("url", imgUrl);
                    map.put("detail", description);
                    map.put("categoryid", categoryid);

                    arrayList.add(map);

                }

                mAdapter = new HomeCategoryAdapter(getActivity(), arrayList,getFragmentManager(), frag,fragment,inflater,container);
                mRecyclerview.setAdapter(mAdapter);



            }


            @Override
            public void onFailure(Call<Category_Pojo> call, Throwable t) {

            }
        });




        return root;
    }

}