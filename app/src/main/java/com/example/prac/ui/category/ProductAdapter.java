package com.example.prac.ui.category;


import android.content.Context;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prac.Preference;
import com.example.prac.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;


    public ProductAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray){
        this.mContext = cxt;
        this.mArray = mArray;

    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView sareeimage;
        TextView  sareetitle,sareeprice,sareediscount;
        TextView productquantity;
        Button add,remove,cart;


        public ViewHolder(View v){
            super(v);
            sareeimage = (ImageView) v.findViewById(R.id.imgBanner);
            sareetitle = (TextView) v.findViewById(R.id.sareetitle);
            sareeprice = (TextView) v.findViewById(R.id.sareeprice);
            sareediscount = (TextView) v.findViewById(R.id.sareediscountprice);
            cart = (Button) v.findViewById(R.id.cart);
            add = (Button) v.findViewById(R.id.addp);
            remove = (Button) v.findViewById(R.id.removep);
            productquantity = (TextView) v.findViewById(R.id.quantityp);

        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            sareeimage = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final HashMap<String,String> map = mArray.get(position);


        holder.sareetitle.setText(map.get("sareename"));
        holder.sareeprice.setText(map.get("originalprice"));
        holder.sareeprice.setPaintFlags(holder.sareeprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.sareediscount.setText(map.get("discountedprice"));
        //Picasso.get().load(map.get("url")).into(holder.sareeimage);
        //Glide.with(mContext).load(map.get("url")).into(holder.sareeimage);

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cart.setVisibility(View.INVISIBLE);
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.productquantity += 1;
                String count = Preference.productquantity.toString();
                holder.productquantity.setText(count);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.productquantity -= 1;
                String count = Preference.productquantity.toString();
                holder.productquantity.setText(count);
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
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}
