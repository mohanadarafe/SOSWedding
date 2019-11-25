package com.example.soswedding.ui.Offer;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;


public class OffersFragment extends Fragment {

    private Offer offer; //never used?!
    private OffersViewModel mViewModel;

    //We need the singleton that carries the user information
    public static OffersFragment newInstance(Offer offer)  { return new OffersFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // TODO: Check which Type of User is it.
        return inflater.inflate(R.layout.offer_couple_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        // TODO: Use the ViewModel
    }

}