package com.example.prac.ui.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prac.R;


import java.util.ArrayList;
import java.util.HashMap;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;


    public BasketAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray){
        this.mContext = cxt;
        this.mArray = mArray;

    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView  discountedprice,sareename;
        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.sareecartimage);
            discountedprice = (TextView) v.findViewById(R.id.sareecartprice);
            sareename = (TextView) v.findViewById(R.id.sareecarttitle);


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








    }

    @Override
    public int getItemCount()
    {
        return mArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}
