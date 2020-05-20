package com.example.prac.ui.category;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prac.Preference;
import com.example.prac.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.prac.HomeActivity.cartarrayList;
import static com.example.prac.HomeActivity.totalamt;
import static com.example.prac.HomeActivity.totaldisamt;

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
        Button cart;




        public ViewHolder(View v){
            super(v);
            sareeimage = (ImageView) v.findViewById(R.id.sareeimage);
            sareetitle = (TextView) v.findViewById(R.id.sareetitle);
            sareeprice = (TextView) v.findViewById(R.id.sareeprice);
            sareediscount = (TextView) v.findViewById(R.id.sareediscountprice);
            cart = (Button) v.findViewById(R.id.cart);

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
        Glide.with(mContext).load(map.get("url")).into(holder.sareeimage);


        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    SharedPreferences s = mContext.getSharedPreferences("cart", Context.MODE_PRIVATE);
                    Gson g = new Gson();
                    String j = s.getString("cart_value",null);
                    Type t = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
                    cartarrayList = g.fromJson(j,t);

                    if (cartarrayList==null) {
                        cartarrayList = new ArrayList<>();
                    }
                    HashMap<String, String> cartmap = new HashMap<>();

                    cartmap.put("productid", map.get("productid"));
                    cartmap.put("discountedprice", map.get("discountedprice"));
                    cartmap.put("originalprice", map.get("originalprice"));
                    cartmap.put("sareename", map.get("sareename"));
                    cartmap.put("url", map.get("url"));
                    cartmap.put("quantity", map.get("quantity"));

                    cartarrayList.add(cartmap);



                    SharedPreferences sp = mContext.getSharedPreferences("carttotal", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("disamt", map.get("discountedprice"));
                    ed.putString("totamt", map.get("originalprice"));
                    ed.apply();
                    totalamt = sp.getString("totalamt", null);
                    totaldisamt = sp.getString("totaldisamt", null);
                    if (totalamt==null && totaldisamt==null){
                        totaldisamt="0";
                        totalamt="0";
                    }
                    ed.putString("totalamt", String.valueOf(Integer.parseInt(totalamt) + Integer.parseInt(sp.getString("totamt", null))));
                    ed.putString("totaldisamt", String.valueOf(Integer.parseInt(totaldisamt) + Integer.parseInt(sp.getString("disamt", null))));
                    ed.apply();


                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("cart", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(cartarrayList);
                    editor.putString("cart_value", json);
                    editor.apply();


                    holder.cart.setEnabled(false);
                    holder.cart.setBackgroundColor(Color.parseColor("#88B7B8AE"));

                    Preference.jump = 1;

                    Toast toast1 = Toast.makeText(mContext,
                            "Added to Cart Sucessfully",
                            Toast.LENGTH_SHORT);
                    toast1.show();


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
