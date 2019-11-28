package com.example.soswedding.ui.Request;

import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.FragmentManager;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.User;
import com.example.soswedding.service.RequestsService;
import com.example.soswedding.ui.all_requests.AllRequestsFragment;

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
    private ImageView requestServiceImage;
    private LinearLayout providerBid;

    private RequestViewModel mViewModel;

    public static RequestFragment newInstance(Request rq) {
        return new RequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root     = inflater.inflate(R.layout.request_fragment, container, false);
        Bundle bundle = getArguments();
        request       = (Request) bundle.getSerializable("request");
        initComponent(root);
        return root;
    }

    public void initComponent(View root){
        typeTv              = root.findViewById(R.id.requestPageTypeTv);
        descriptionTv       = root.findViewById(R.id.requestPageDescriptionTv);
        budgetTv            = root.findViewById(R.id.budgetTv);
        addressTv           = root.findViewById(R.id.addressTv);
        coupleNameTv        = root.findViewById(R.id.coupleNameTv);
        bidBtn              = root.findViewById(R.id.bidBtn);
        bidAmountEt         = root.findViewById(R.id.bidAmountEt);
        titleTv             = root.findViewById(R.id.requestTitle);
        providerBid         = root.findViewById(R.id.providerBid);
        requestServiceImage = root.findViewById(R.id.requestServiceImage);
        fillComponent();
    }
    public void fillComponent(){
        Log.e("My App", "Request: " + request.getAddress() + "\"");

        typeTv.setText("Type: "+request.getType());
        descriptionTv.setText("Description: " + request.getDescription());
        budgetTv.setText("Budget: " +request.getBudget());
        addressTv.setText("Address:  "+request.getAddress());
        titleTv.setText(request.getTitle());
        if(Singleton.getInstance().getType().equalsIgnoreCase("COUPLE"))
            providerBid.setVisibility(View.GONE);
        else
        bidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = bidAmountEt.getText().toString();
                if(str.length()>0)
                    postBid(str);
                else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),"You did not enter anything",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        chooseImage();
    }

    public void chooseImage() {
        switch (request.getType()) {
            case "DANCE":
                requestServiceImage.setImageResource(R.drawable.dance_service);
                break;
            case "BAKER":
                requestServiceImage.setImageResource(R.drawable.cake_service);
                break;
            case "CAR":
                requestServiceImage.setImageResource(R.drawable.car_service);
                break;
            case "RECEPTION":
                requestServiceImage.setImageResource(R.drawable.reception_service);
                break;
            case "ALCOHOL":
                requestServiceImage.setImageResource(R.drawable.alcohol_service);
                break;
            case "CATERING":
                requestServiceImage.setImageResource(R.drawable.catering_service);
                break;
            case "MUSIC":
                requestServiceImage.setImageResource(R.drawable.music_service);
                break;
            case "FLORIST":
                requestServiceImage.setImageResource(R.drawable.flower_service);
                break;
            case "PHOTOGRAPHER":
                requestServiceImage.setImageResource(R.drawable.photographer_service);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RequestViewModel.class);
    }

    public void postBid(String str){
        mViewModel.postBidModel(getContext(),str, request,Singleton.getInstance());
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Your bid has successfully been posted",Toast.LENGTH_SHORT);
        toast.show();
        getFragmentManager().popBackStackImmediate();
    }
}
