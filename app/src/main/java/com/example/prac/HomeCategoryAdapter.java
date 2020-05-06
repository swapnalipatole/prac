package com.example.prac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.prac.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;

    public HomeCategoryAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray){
        this.mContext = cxt;
        this.mArray = mArray;
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBanner;
        TextView  txtcaption;
        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.imgBanner);
            // txtTitle = (TextView) v.findViewById(R.id.title);
            txtcaption = (TextView) v.findViewById(R.id.txtTitle);


        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String,String> map = mArray.get(position);
        //Glid.with(mContext).load(map.get("url")).into(holder.imgBanner);
        Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
        //holder.txtTitle.setText(map.get("title"));
        holder.txtcaption.setText(map.get("detail"));
        HomeFragment.caption1=map.get("detail");


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
