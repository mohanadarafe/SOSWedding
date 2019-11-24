package com.example.soswedding.ui.CreateRequest;

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

import com.example.soswedding.R;
import com.example.soswedding.model.Request;

public class CreateRequestFragment extends Fragment {

    private CreateRequestViewModel mViewModel;
    private Spinner typeSpinner;
    private EditText titleEditText;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private EditText budgetEditText;

    private Button postRequestBtn;

    public static CreateRequestFragment newInstance() {
        return new CreateRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.create_request_fragment, container, false);
        initComponent(root);
        return root;
    }

    private void initComponent(View root) {
        typeSpinner = root.findViewById(R.id.createRequestSpinner);
        titleEditText = root.findViewById(R.id.titleEt);
        addressEditText = root.findViewById(R.id.addressEt);
        descriptionEditText = root.findViewById(R.id.descriptionEt);
        budgetEditText = root.findViewById(R.id.budgetTv);
        postRequestBtn = root.findViewById(R.id.createRequestBtn);
        postRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPostRqClicked();
            }
        });

        String[] items = new String[]{"BAKER", "CAR", "DANCE", "RECEPTION", "ALCOHOL", "CATERING", "MUSIC", "FLORIST", "PHOTOGRAPHER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        typeSpinner.setAdapter(adapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onPostRqClicked() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String type = typeSpinner.getTransitionName();
        String address = addressEditText.getText().toString();
        Double budget = Double.parseDouble(addressEditText.getText().toString());
        Request request = new Request(title, description, type, address, budget);
        mViewModel.createRequest(request);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateRequestViewModel.class);
        // TODO: Use the ViewModel
    }

}
