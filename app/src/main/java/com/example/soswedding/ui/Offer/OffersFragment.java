package com.example.soswedding.ui.Offer;

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

import com.example.soswedding.R;
import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Singleton;


public class OffersFragment extends Fragment {

    private Offer offer;
    public TextView descriptionTv;
    public TextView providerName;
    public TextView titleTv;
    public TextView status;
    public Button acceptOfferBtn;
    public Button DenyOfferBtn;
    public TextView bidAmountEt;
    private LinearLayout coupleBidResponse;
    private OffersViewModel mViewModel;

    //We need the singleton that carries the user information
    public static OffersFragment newInstance(Offer offer)  { return new OffersFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.offer_fragment, container, false);
        Bundle bundle = getArguments();
        offer = (Offer) bundle.getSerializable("offer");
        initComponent(root);
        return root;
    }

    public void initComponent(View root){
        descriptionTv = root.findViewById(R.id.offerPageDescriptionTv);
        status = root.findViewById(R.id.statusDetailTv);
        providerName = root.findViewById(R.id.providerNameTv);
        acceptOfferBtn = root.findViewById(R.id.acceptOfferBtn);
        DenyOfferBtn = root.findViewById(R.id.DenyOfferBtn);
        bidAmountEt = root.findViewById(R.id.bidAmountTv);
        titleTv = root.findViewById(R.id.offerTitle);
        coupleBidResponse = root.findViewById(R.id.coupleBidResponseTv);
        fillComponent();
    }

    public void fillComponent(){

        descriptionTv.setText("Description: " + offer.getMessage());
        bidAmountEt.setText("Bid Amount: " +offer.getAmount());
        status.setText("Status: " + offer.getStatus());
        providerName.setText("Provider's Name: " + offer.getCompanyName());
        titleTv.setText(offer.getTitle());
        if(Singleton.getInstance().getType().equalsIgnoreCase(("PROVIDER")))
            coupleBidResponse.setVisibility(View.GONE);
        else {

            coupleBidResponse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String acceptedAmount = String.valueOf(offer.getAmount());
                    if (acceptedAmount != null)
                        mViewModel.acceptBidModel(getContext(), acceptedAmount, offer.getRequestId(),offer.getId());
                        //TODO: On success, display Message or update Bid Status on page
                    else {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "You did not enter anything", Toast.LENGTH_SHORT);
                        toast.show(); //TODO: is this method displaying the error?
                    }
                }
            });

            //TODO: Connect second button [ DenyOfferBtn ] with its corresponding method
            // from the backend, See Above
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
    }

}