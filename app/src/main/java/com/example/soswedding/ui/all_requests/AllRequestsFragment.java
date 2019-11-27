package com.example.soswedding.ui.all_requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soswedding.Interface.OnItemClickListener;
import com.example.soswedding.Interface.RecyclerViewClickListener;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.service.RequestsService;
import com.example.soswedding.ui.CreateRequest.CreateRequestFragment;
import com.example.soswedding.ui.Request.RequestFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AllRequestsFragment extends Fragment implements OnItemClickListener {

    private AllRequestsViewModel allRequestViewModel;
    private RecyclerView requestsRv;
    RequestAdapter mAdapter;
    private List<Request> requestsList;
    private FloatingActionButton createRequestBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_requests, container, false);
        setUpViewModel();
        initComponents(root);


        return root;
    }

    private void initComponents(View root) {
        requestsRv = root.findViewById(R.id.requestsRv);
        createRequestBtn = root.findViewById(R.id.createRequestBtn);
        if(Singleton.getInstance().getType().equalsIgnoreCase(("COUPLE"))){
            createRequestBtn.setVisibility(View.VISIBLE);
            RequestsService.getRequestsOfUser(getContext(),Singleton.getInstance().getId(),
                    new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            onSuccessReceivedList(result);
                        }
            });
        }else {
            RequestsService.getAllRequests(getContext(), new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    onSuccessReceivedList(result);
                }
            });
        }

        createRequestBtn = root.findViewById(R.id.createRequestBtn);
        createRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CreateRequestFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
            }
        });
    }

    private void onSuccessReceivedList(String result) {
        requestsList = allRequestViewModel.getDisplayableRequests(result);
        initRecyclerView();
    }

    private void setUpViewModel() {
        allRequestViewModel =
                ViewModelProviders.of(this).get(AllRequestsViewModel.class);
    }


    private void initRecyclerView() {
        requestsRv.setHasFixedSize(true);
        requestsRv.getRecycledViewPool().setMaxRecycledViews(0, 0);
        requestsRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        initRequestRvAdapter();
    }

    private void initRequestRvAdapter() {
        mAdapter = new RequestAdapter(getActivity().getApplicationContext(),getActivity(), requestsList, this);
        requestsRv.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(Request item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("request", item);
        Fragment fragment = new RequestFragment();
        fragment.setArguments(bundle);
        RequestFragment.newInstance(item);
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }
}