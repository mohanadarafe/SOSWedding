package com.example.soswedding.ui.Request;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.service.RequestsService;

public class RequestFragment extends Fragment {
    public Request request;
    public TextView typeTv;
    public TextView budgetTv;
    public TextView descriptionTv;
    public TextView addressTv;
    public TextView coupleNameTv;
    public TextView titleTv;
    public Button bidBtn;
    public EditText bidAmountEt;
    private LinearLayout providerBid;
    private RequestViewModel mViewModel;

    public static RequestFragment newInstance(Request rq) {
        return new RequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.request_fragment, container, false);
        Bundle bundle = getArguments();
        request = (Request) bundle.getSerializable("request");
        initComponent(root);
        return root;
    }

    public void initComponent(View root){
        typeTv = root.findViewById(R.id.requestPageTypeTv);
        descriptionTv = root.findViewById(R.id.requestPageDescriptionTv);
        budgetTv = root.findViewById(R.id.budgetTv);
        addressTv = root.findViewById(R.id.addressTv);
        coupleNameTv = root.findViewById(R.id.coupleNameTv);
        bidBtn = root.findViewById(R.id.bidBtn);
        bidAmountEt = root.findViewById(R.id.bidAmountEt);
        titleTv = root.findViewById(R.id.requestTitle);
        providerBid = root.findViewById(R.id.providerBid);
        fillComponent();
    }
    public void fillComponent(){
        Log.e("My App", "Request: " + request.getAddress() + "\"");

        typeTv.setText("Type: "+request.getType());
        descriptionTv.setText("Description: " + request.getDescription());
        budgetTv.setText("Budget: " +request.getBudget());
        addressTv.setText("Address:  "+request.getAddress());
        titleTv.setText(request.getTitle());
        if(Singleton.getInstance().getType().equalsIgnoreCase(("COUPLE")))
            providerBid.setVisibility(View.GONE);
        else
        bidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = bidAmountEt.getText().toString();
                if(str.length()>0)
                mViewModel.postBidModel(getContext(),str, request.getId());
                else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),"You did not enter anything",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
        // TODO: Use the ViewModel
    }

}
