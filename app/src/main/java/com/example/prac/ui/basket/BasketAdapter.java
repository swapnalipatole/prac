package com.example.prac.ui.basket;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prac.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.HashMap;

import static com.example.prac.ui.category.ProductAdapter.cartarrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    Context mContext;



    public BasketAdapter(Context cxt){
        this.mContext = cxt;


    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView  discountedprice,sareename,sareecartprice;
        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.sareecartimage);
            discountedprice = (TextView) v.findViewById(R.id.sareediscountcartprice);
            sareename = (TextView) v.findViewById(R.id.sareecarttitle);
            sareecartprice = (TextView) v.findViewById(R.id.sareecartprice);


        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            imgBanner = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HashMap<String,String> map = cartarrayList.get(position);


        Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
        holder.sareename.setText(map.get("sareename"));
        holder.sareecartprice.setText(map.get("originalprice"));
        holder.sareecartprice.setPaintFlags(holder.sareecartprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discountedprice.setText(map.get("discountedprice"));





    }

    @Override
    public int getItemCount()
    {
        return cartarrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}
