package com.example.soswedding.ui.all_requests;

import android.graphics.Rect;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soswedding.R;
import com.example.soswedding.model.Request;

import java.util.ArrayList;
import java.util.List;

public class AllRequestsFragment extends Fragment {

    private AllRequestsViewModel allRequestViewModel;
    private RecyclerView requestsRv;
    RequestAdapter mAdapter;
//    private List<Request> requestsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_requests, container, false);
        setUpViewModel();
        updateRequestList();
        initRecyclerView(root);
        return root;
    }

    private void updateRequestList() {
        //TODO: Make call to get the list of requests
    }

    private void setUpViewModel() {
        allRequestViewModel =
                ViewModelProviders.of(this).get(AllRequestsViewModel.class);
    }


    private void initRecyclerView(View root) {
        requestsRv = root.findViewById(R.id.requestsRv);
        requestsRv.setHasFixedSize(true);
        requestsRv.getRecycledViewPool().setMaxRecycledViews(0, 0);
        requestsRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        initRequestRvAdapter();
    }

    private void initRequestRvAdapter() {
        mAdapter = new RequestAdapter(getActivity().getApplicationContext(),getActivity(), allRequestViewModel.getMockupList());
        requestsRv.setAdapter(mAdapter);
    }

}