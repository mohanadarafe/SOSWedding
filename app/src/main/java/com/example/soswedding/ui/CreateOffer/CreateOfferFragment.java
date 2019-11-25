package com.example.soswedding.ui.CreateOffer;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soswedding.R;
import com.example.soswedding.model.Offer;


public class CreateOfferFragment extends Fragment {


    private CreateOfferViewModel mViewModel;
    private EditText descriptionEditText;
    private EditText budgetEditText;

    private Button postRequestBtn;

    public static CreateOfferFragment newInstance() {
        return new CreateOfferFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.create_offer_fragment, container, false);
        initComponent(root);
        return root;
    }


    private void initComponent(View root) {

        descriptionEditText = root.findViewById(R.id.descriptionEt);
        budgetEditText = root.findViewById(R.id.budgetEditText);
        postRequestBtn = root.findViewById(R.id.createRequestPageBtn);
        postRequestBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                onPostOfferClicked();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onPostOfferClicked() {

//        String title = titleEditText.getText().toString(); could be useful to display the title in the create bid page
        String description = descriptionEditText.getText().toString();
        Double budget = Double.parseDouble(budgetEditText.getText().toString());
        if(description.length() > 0 && budget > 0){
            Offer offer = new Offer ("test",description,"",  budget);
            mViewModel.createOffer(getContext(), offer);
        }
        else {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Please fill all fields and try again!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateOfferViewModel.class);
        // TODO: Use the ViewModel
    }



}
