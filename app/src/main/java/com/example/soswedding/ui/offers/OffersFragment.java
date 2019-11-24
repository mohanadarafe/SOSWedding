package com.example.soswedding.ui.offers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soswedding.Interface.RecyclerViewClickListener;
import com.example.soswedding.model.Request;
import com.example.soswedding.ui.Request.RequestFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;


import androidx.lifecycle.ViewModelProviders;

import com.example.soswedding.R;

public class OffersFragment extends Fragment {

    private OffersViewModel offersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        offersViewModel =
                ViewModelProviders.of(this).get(OffersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_offers, container, false);
        final TextView textView = root.findViewById(R.id.text_offers);
        offersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}