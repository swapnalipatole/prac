package com.example.prac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.example.prac.ui.category.CategoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    NavController navController;

    public static String totalamt ="0",totaldisamt ="0";
    public static String ta,tda;


    public static ArrayList<HashMap<String, String>> cartarrayList;
    public static HashMap<String, String> cartmap = new HashMap<>();


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jump = getIntent().getStringExtra("categoryid");
        String caption = getIntent().getStringExtra("caption");
        if (jump != null){
            Intent intent = new Intent(HomeActivity.this, CategoryFragment.class);
            intent.putExtra("categoryid",jump);
            intent.putExtra("caption",caption);

            //startActivity(intent);
        }



        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.sort);
        actionBar.setDisplayHomeAsUpEnabled(true);

        cartarrayList = new ArrayList<>();


        BottomNavigationView navView = findViewById(R.id.nav_view1);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }



   /* @Override
    public boolean onSupportNavigateUp() {
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

            NavController navController = Navigation.findNavController(this,R.id.navigation_home);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                ||(super.onSupportNavigateUp());

    }*/


}

