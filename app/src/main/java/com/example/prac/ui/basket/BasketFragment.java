package com.example.prac.ui.basket;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prac.Preference;
import com.example.prac.R;
import com.example.prac.ui.home.HomeCategoryAdapter;

import java.util.ArrayList;

import static com.example.prac.ui.category.ProductAdapter.cartarrayList;

public class BasketFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private BasketViewModel basketViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_basket, container, false);

        mRecyclerview = root.findViewById(R.id.basket_recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        if (Preference.jump == 1){
            mAdapter = new BasketAdapter(getActivity());
            mRecyclerview.setAdapter(mAdapter);
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        basketViewModel = ViewModelProviders.of(this).get(BasketViewModel.class);
        // TODO: Use the ViewModel
    }

}
