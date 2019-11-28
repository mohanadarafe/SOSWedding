package com.example.soswedding.ui.all_offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soswedding.Interface.OnOfferItemClickListener;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.User;
import com.example.soswedding.service.RequestsService;
import com.example.soswedding.service.BidService;
import com.example.soswedding.ui.CreateRequest.CreateRequestFragment;
import com.example.soswedding.ui.Offer.OffersFragment;
import com.example.soswedding.ui.Request.RequestFragment;
import com.example.soswedding.ui.all_offers.AllOffersViewModel;
import com.example.soswedding.ui.all_offers.OffersAdapter;

import java.util.List;

public class AllOffersFragment extends Fragment implements OnOfferItemClickListener {

    private AllOffersViewModel allOffersViewModel;
    private RecyclerView offersRv;
    OffersAdapter mAdapter;
    private List<Offer> offersList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_offers, container, false);
        setUpViewModel();
        initComponents(root);
        return root;
    }

    private void initComponents(View root) {
        offersRv = root.findViewById(R.id.offersRv);
        if(Singleton.getInstance().getType().equalsIgnoreCase(("PROVIDER"))){
            BidService.getOffersOfProvider(getContext(), Singleton.getInstance().getUuid(),
            new VolleyCallback(){
                @Override
                public void onSuccess(String result){
                    onSuccessReceivedList(result);
                }
            });
        }
        else{
            BidService.getAllBidsForCouples(getContext(),Singleton.getInstance().getUuid(),new VolleyCallback(){
                @Override
                public void onSuccess(String result){
                    onSuccessReceivedList(result);
                }
            });
        }
    }

    private void onSuccessReceivedList(String result) {
        String userType = Singleton.getInstance().getType();
        if(userType.equalsIgnoreCase("COUPLE")) {
            offersList = allOffersViewModel.getOffersObjectForCouple(result);
        }
        else {
            offersList = allOffersViewModel.getListOfAllOffers(result);
        }
        initRecyclerView();
    }

    private void setUpViewModel() {
        allOffersViewModel =
                ViewModelProviders.of(this).get(AllOffersViewModel.class);
    }


    private void initRecyclerView(){
        offersRv.setHasFixedSize(true);
        offersRv.getRecycledViewPool().setMaxRecycledViews(0, 0);
        offersRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        initOfferRvAdapter();
    }

    private void initOfferRvAdapter() {
        mAdapter = new OffersAdapter(getActivity().getApplicationContext(),getActivity(), offersList, this);
        offersRv.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(Offer item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("offer", item);
        Fragment fragment = new OffersFragment();
        fragment.setArguments(bundle);
        OffersFragment.newInstance(item);
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }



}

