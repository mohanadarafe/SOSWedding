package com.example.soswedding.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.soswedding.R;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.User;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private ImageView profilePhoto;
    private TextView nameTv;
    private TextView companyNameTv;
    private TextView phoneNumberTv;
    private TextView emailTv;
    private TextView typeServiceTv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        initcomponent(root);
        setContent();
        return root;
    }



    private void initcomponent(View root) {
        nameTv = root.findViewById(R.id.profileNameTv);
        companyNameTv = root.findViewById(R.id.profileCompanyTv);
        phoneNumberTv = root.findViewById(R.id.phoneNumberTv);
        emailTv = root.findViewById(R.id.EmailTv);
        typeServiceTv = root.findViewById(R.id.providerTypeTv);
        profilePhoto = root.findViewById(R.id.profileImage);
    }
    private void setContent() {
        User user = Singleton.getInstance();
        nameTv.setText(user.getFirstName()+ " " + user.getLastName());
        if(!user.getType().equals("COUPLE")){
            typeServiceTv.setText("Services provided: "+user.getServiceProvided());
            companyNameTv.setText("Company: "+ user.getCompanyName());
            profilePhoto.setImageResource(R.drawable.joe);
        }
        else {
            typeServiceTv.setVisibility(View.GONE);
            companyNameTv.setVisibility(View.GONE);
            profilePhoto.setImageResource(R.drawable.couple_profile_two);
        }
        emailTv.setText("Email: "+ user.getEmail());
        phoneNumberTv.setText("phone: " + user.getPhoneNumber());


    }

}