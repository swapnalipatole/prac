package com.example.prac.ui.basket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prac.Preference;
import com.example.prac.R;
import com.example.prac.ui.search.SearchFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.prac.HomeActivity.cartarrayList;
import static com.example.prac.HomeActivity.ta;
import static com.example.prac.HomeActivity.tda;
import static com.example.prac.HomeActivity.totalamt;
import static com.example.prac.HomeActivity.totaldisamt;
import static com.example.prac.ui.basket.BasketFragment.cartlist;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    Context mContext;
    FragmentManager fragmentManager;
    Fragment f;



    public BasketAdapter(Context cxt, FragmentManager targetFragment, Fragment fragment){
        this.mContext = cxt;
        this.fragmentManager = targetFragment;
        this.f = fragment;


    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView  discountedprice,sareename,sareecartprice,quantity;
        Button remove,add,rem;


        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.sareecartimage);
            discountedprice = (TextView) v.findViewById(R.id.sareediscountcartprice);
            sareename = (TextView) v.findViewById(R.id.sareecarttitle);
            sareecartprice = (TextView) v.findViewById(R.id.sareecartprice);
            remove = (Button) v.findViewById(R.id.removec);
            add = (Button) v.findViewById(R.id.addp);
            rem = (Button) v.findViewById(R.id.removep);
            quantity = (TextView) v.findViewById(R.id.quantityp);




        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            imgBanner = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final HashMap<String,String> map = cartlist.get(position);
        if (cartlist.contains(cartarrayList)){
            cartlist.indexOf(map.get("productid"));
            Toast.makeText(mContext,"Alreasy Exists",Toast.LENGTH_SHORT).show();
        }else {

            Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
            holder.sareename.setText(map.get("sareename"));
            holder.sareecartprice.setText(map.get("originalprice"));
            holder.sareecartprice.setPaintFlags(holder.sareecartprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.discountedprice.setText(map.get("discountedprice"));
            holder.quantity.setText(map.get("quantity"));
        }

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartlist.remove(position);
                applycart();
                ta = String.valueOf(Integer.parseInt(ta) - (Integer.parseInt(map.get("originalprice")) * Integer.parseInt(map.get("quantity"))));
                tda = String.valueOf(Integer.parseInt(tda) - (Integer.parseInt(map.get("discountedprice")) * Integer.parseInt(map.get("quantity"))));
                totalamt = ta;
                totaldisamt = tda;
                applycarttotal();


                fragmentManager.beginTransaction().detach(f).attach(f).commit();
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("quantity",String.valueOf(Integer.parseInt(map.get("quantity")) + Integer.parseInt("1")));
                holder.quantity.setText(map.get("quantity"));
                applycart();

                ta = String.valueOf(Integer.parseInt(ta) + Integer.parseInt(map.get("originalprice")));
                tda = String.valueOf(Integer.parseInt(tda) + Integer.parseInt(map.get("discountedprice")));

                applycarttotal();

                //Toast toast = Toast.makeText(mContext,map.get("quantity"),Toast.LENGTH_SHORT);
                //toast.show();

                fragmentManager.beginTransaction().detach(f).attach(f).commit();
            }
        });

        holder.rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(map.get("quantity")) > 1) {
                    map.put("quantity", String.valueOf(Integer.parseInt(map.get("quantity")) - Integer.parseInt("1")));
                    holder.quantity.setText(map.get("quantity"));
                    applycart();
                    ta = String.valueOf(Integer.parseInt(ta) - Integer.parseInt(map.get("originalprice")));
                    tda = String.valueOf(Integer.parseInt(tda) - Integer.parseInt(map.get("discountedprice")));

                    applycarttotal();


                    fragmentManager.beginTransaction().detach(f).attach(f).commit();
                }
                else
                {
                    Toast t = Toast.makeText(mContext,"Quantity Cannot be 0",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    private void applycarttotal() {
        SharedPreferences sp = mContext.getSharedPreferences("carttotal", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("totaldisamt",tda);
        ed.putString("totalamt",ta);
        ed.apply();
    }

    private void applycart() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartlist);
        editor.putString("cart_value",json);
        editor.apply();
    }

    @Override
    public int getItemCount()
    {
        return cartlist.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}
