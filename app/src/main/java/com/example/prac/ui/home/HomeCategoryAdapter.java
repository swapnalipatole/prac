package com.example.prac.ui.home;

import android.app.Activity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedImageDrawable;
import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.prac.Category_Pojo.Category_Pojo;
import com.example.prac.HomeActivity;
import com.example.prac.Preference;
import com.example.prac.R;

import com.example.prac.ui.category.CategoryFragment;
import com.example.prac.ui.category.CategoryViewModel;
import com.example.prac.ui.offers.OffersFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Callback;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;
    FragmentManager fragmentManager;
    Fragment f,t;
    LayoutInflater layoutInflater;
    ViewGroup viewGroup;


    public HomeCategoryAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray, FragmentManager fragmentManager, Fragment homeFragment, Fragment fragment, LayoutInflater inflater, ViewGroup container){
        this.mContext = cxt;
        this.mArray = mArray;
        this.fragmentManager = fragmentManager;
        this.f = homeFragment;
        this.t  = fragment;
        this.layoutInflater = inflater;
        this.viewGroup = container;
    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView  txtcaption;

        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.imgBanner);
            // txtTitle = (TextView) v.findViewById(R.id.title);
            txtcaption = (TextView) v.findViewById(R.id.txtTitle);


        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            imgBanner = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HashMap<String,String> map = mArray.get(position);


        Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);

        holder.txtcaption.setText(map.get("detail"));
        HomeFragment.caption1=map.get("detail");



        holder.imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.categoryid = map.get("categoryid");
                Preference.categoryname = map.get("detail");

                //fragmentManager.beginTransaction().detach(f).attach(t).commit();
                layoutInflater.inflate(R.layout.fragment_category, viewGroup, false);


            }
        });


    }


    @Override
    public int getItemCount()
    {
        return mArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}
