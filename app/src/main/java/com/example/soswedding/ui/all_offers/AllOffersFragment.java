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

import com.example.soswedding.Interface.RecyclerViewClickListener;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;
import com.example.soswedding.ui.Offer.OffersFragment;
import com.example.soswedding.ui.all_offers.AllOffersViewModel;
import com.example.soswedding.ui.all_offers.OffersAdapter;

import java.util.List;

public class AllOffersFragment extends Fragment implements RecyclerViewClickListener {

    private AllOffersViewModel allOffersViewModel;
    private RecyclerView offersRv;
    OffersAdapter mAdapter;
    private List<Offer> offersList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_offers, container, false);
        setUpViewModel();
        updateOfferList();
        initComponents();
        initRecyclerView(root);

        return root;
}

    private void initComponents() {

        offersList = allOffersViewModel.getMockupList();
    }

    private void updateOfferList() {
        //TODO: Make call to get the list of requests
    }

    private void setUpViewModel() {
        allOffersViewModel =
                ViewModelProviders.of(this).get(AllOffersViewModel.class);
    }


    private void initRecyclerView(View root){
        offersRv = root.findViewById(R.id.offersRv);
        offersRv.setHasFixedSize(true);
        offersRv.getRecycledViewPool().setMaxRecycledViews(0, 0);
        offersRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        initOfferRvAdapter();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("offer", offersList.get(position));
        Fragment fragment = new OffersFragment();
        fragment.setArguments(bundle);
        OffersFragment.newInstance(offersList.get(position));
        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

    }

        private void initOfferRvAdapter() {
            mAdapter = new OffersAdapter(getActivity().getApplicationContext(),getActivity(), offersList, this);
            offersRv.setAdapter(mAdapter);
        }

}

