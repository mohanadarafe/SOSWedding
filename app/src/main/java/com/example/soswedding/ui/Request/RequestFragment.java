package com.example.soswedding.ui.Request;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;

public class RequestFragment extends Fragment {
    //Never used!?
    private Request request;
    private RequestViewModel mViewModel;
    //

    //We need the singleton that carries the user information
    public static RequestFragment newInstance(Request request) {
        return new RequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // TODO: Check which Type of User is it.
        return inflater.inflate(R.layout.request_provider_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
        // TODO: Use the ViewModel
    }

}
