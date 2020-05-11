package com.example.prac.ui.offers;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prac.Preference;
import com.example.prac.R;

import java.util.ArrayList;
import java.util.HashMap;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;

    public OffersAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray) {
        this.mContext = cxt;
        this.mArray = mArray;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,shoptitle,code,validity;
        Layout offer;

        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.logo);
            // txtTitle = (TextView) v.findViewById(R.id.title);
            title = (TextView) v.findViewById(R.id.offer_title);
            shoptitle = (TextView) v.findViewById(R.id.shop_title);
            code = (TextView) v.findViewById(R.id.offer_code);
            validity = (TextView) v.findViewById(R.id.offer_validity);



        }
    }
    @Override
    public void onBindViewHolder(final OffersAdapter.ViewHolder holder, int position) {
        final HashMap<String,String> map = mArray.get(position);

        Glide.with(mContext).load(map.get("url")).into(holder.img);


        holder.title.setText(map.get("discount"));
        holder.shoptitle.setText(map.get("shop"));
        holder.code.setText(String.format("Code : %s", map.get("code")));
        holder.validity.setText(map.get("vaild"));


        holder.code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.offer_code = map.get("code");
                Toast toast = Toast.makeText(mContext,
                        "Code Copied",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mArray.size();
    }

    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_category, parent, false);
        OffersAdapter.ViewHolder zh = new OffersAdapter.ViewHolder(mRowView);

        return zh;
    }
}