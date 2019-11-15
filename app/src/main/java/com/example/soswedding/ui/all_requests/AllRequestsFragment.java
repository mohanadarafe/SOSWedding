package com.example.soswedding.ui.all_requests;

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

public class AllRequestsFragment extends Fragment {

    private AllRequestsViewModel allRequestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allRequestViewModel =
                ViewModelProviders.of(this).get(AllRequestsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_requests, container, false);
        final TextView textView = root.findViewById(R.id.text_all_requests);
        allRequestViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}