package com.example.soswedding.ui.register;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soswedding.R;
import com.example.soswedding.ui.SignIn.SignInFragment;

public class RegisterFragment extends Fragment
{


    private Button signUpBtn;
    private Button signInBtn;


    public static RegisterFragment newInstance (){
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.register_fragment, container, false);
        initializeVariables(rootView);
        setupOnClickEvents();
        return rootView;
    }

    private void initializeVariables(View rootView) {
        signInBtn   = rootView.findViewById(R.id.signInBtn);
        signUpBtn = rootView.findViewById(R.id.signUpBtn);
    }

    private void setupOnClickEvents() {
        setupSignUpOnClickEvent();
        setupSignInOnClickEvent();
    }

    private void setupSignUpOnClickEvent() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onSignUpClicked();
            }
        });
    }

    private void setupSignInOnClickEvent() {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInOnClicked();
            }
        });
    }

        private void signInOnClicked(){
            Fragment fragment = null;
            try {
                fragment = SignInFragment.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment)
                    .commit();

        }


}
