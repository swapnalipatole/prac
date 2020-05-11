package com.example.prac.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prac.Preference;
import com.example.prac.R;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_category, container, false);

        textView = root.findViewById(R.id.product_category);

        //String name = getActivity().getIntent().getStringExtra("caption");
        textView.setText(new StringBuilder().append(Preference.categoryname).append(" Saree Category").toString());



        return root;
    }
}
