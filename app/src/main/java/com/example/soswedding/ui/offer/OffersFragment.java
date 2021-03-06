package com.example.soswedding.ui.offer;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.VolleyError;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.service.RequestsService;

import org.json.JSONException;


public class OffersFragment extends Fragment {

    private Offer offer;
    public TextView descriptionTv;
    public TextView providerName;
    public TextView titleTv;
    public TextView status;
    public Button acceptOfferBtn;
    public Button declineOfferBtn;
    public TextView bidAmountEt;
    private LinearLayout coupleBidResponse;
    private OffersViewModel mViewModel;
    private ImageView offerServiceImage;
    private TextView statusDetailTv;


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
        declineOfferBtn = root.findViewById(R.id.DenyOfferBtn);
        bidAmountEt = root.findViewById(R.id.bidAmountTv);
        titleTv = root.findViewById(R.id.offerTitle);
        coupleBidResponse = root.findViewById(R.id.coupleBidResponseTv);
        offerServiceImage = root.findViewById(R.id.offerServiceImage);
        statusDetailTv = root.findViewById(R.id.statusDetailTv);
        fillComponent();

    }

    public void fillComponent(){

        descriptionTv.setText("Description: " + offer.getMessage());
        bidAmountEt.setText("Bid Amount: " +offer.getAmount());
        status.setText("Status: " + offer.getStatus());
        providerName.setText("Provider's Name: "+offer.getCompanyName());
        titleTv.setText("Bid for "+offer.getRequestTitle());


        if(Singleton.getInstance().getType().equalsIgnoreCase(("PROVIDER")))
            coupleBidResponse.setVisibility(View.GONE);
        else {

            if(!offer.getStatus().equalsIgnoreCase("PENDING"))
                coupleBidResponse.setVisibility(View.GONE);

            acceptOfferBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        coupleBidResponse.setVisibility(View.GONE);
                        mViewModel.acceptBidModel(getContext(), offer.getRequestId(),offer.getId());
                        offer.setStatus("ACCEPTED");
                        setImageDetailView(offer.getStatus());
                        GetInfoByrequestId(offer.getRequestId());
                        popUp(offer.getStatus());
                        RequestsService.getRequestById(getContext(),offer.getRequestId(),
                            new VolleyCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    onSuccessBidPosting(result);
                                }
                            });
                }
            });

            declineOfferBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        coupleBidResponse.setVisibility(View.GONE);
                        mViewModel.declineBidModel(getContext(), offer.getId());
                        setImageDetailView(offer.getStatus());
                        offer.setStatus("DECLINED");
                        popUp(offer.getStatus());
                        getFragmentManager().popBackStackImmediate();
                }
            });

        }
        setImageDetailView(offer.getStatus());


    }

    public void GetInfoByrequestId(long requestId){
        RequestsService.getRequestById(getContext(),requestId,new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("My App", "SUCCESS!");
                try{
                    Request request = mViewModel.getRequestObjectFromString(result);
                    updateRequestStatus(request);

                }catch( JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateRequestStatus(Request rq){
        RequestsService.editRequestStatus(getContext(),rq);
    }

    public void setImageDetailView(String statusLabel){
        switch(statusLabel) {
            case "DECLINED":
                offerServiceImage.setImageResource(R.drawable.cancel);
                statusDetailTv.setTextAppearance(R.style.statusDeclined);
                break;
            case "ACCEPTED":
                offerServiceImage.setImageResource(R.drawable.accept);
                statusDetailTv.setTextAppearance(R.style.statusAccepted);
                break;
            case "PENDING":
                if(Singleton.getInstance().getType().equalsIgnoreCase("COUPLE")) {
                    offerServiceImage.setImageResource(R.drawable.speech_bubble);
                }
                else{
                    offerServiceImage.setImageResource(R.drawable.wall_clock);
                }
                statusDetailTv.setTextAppearance(R.style.statusPending);
                break;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
    }

    public void popUp(String message) {
        if (message.equalsIgnoreCase("ACCEPTED")) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "You have successfully accepted the bid", Toast.LENGTH_SHORT);
            toast.show();

        }
        else if (message.equalsIgnoreCase("DECLINED")){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),"You have successfully declined the bid",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
        public void onSuccessBidPosting(String result){
            OffersViewModel viewModel = new OffersViewModel();
            try{
                Request rq = viewModel.getRequestObjectFromString(result);
                RequestsService.editRequestStatus(getContext(), rq);

            }

            catch(JSONException e){
                e.printStackTrace();
            }
        }


}