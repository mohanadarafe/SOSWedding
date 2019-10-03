package com.example.soswedding.ui.SignIn;

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
import androidx.fragment.app.FragmentManager;

import com.example.soswedding.R;
import com.example.soswedding.ui.forgotPassword.ForgotPasswordFragment;

public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBtn;
    private Button signUpBtn;
    private Button forgotPasswordBtn;


    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sign_in_fragment, container, false);
        initializeVariables(rootView);
        setupOnClickEvents();
        return rootView;

        }

    private void initializeVariables(View rootView) {
        emailInput = rootView.findViewById(R.id.emailInput);
        passwordInput = rootView.findViewById(R.id.passwordInput);
        loginBtn = rootView.findViewById(R.id.loginBtn);
        signUpBtn = rootView.findViewById(R.id.signUpBtn);
        forgotPasswordBtn = rootView.findViewById(R.id.forgotPasswordBtn);
    }

    private void setupOnClickEvents() {
        setupForgotPasswordOnClickEvent();
        setupSignUpOnClickEvent();
        setupSignInOnClickEvent();
    }

    private void setupForgotPasswordOnClickEvent() {
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onForgotPasswordClicked(view);
            }
        });
    }
    private void setupSignUpOnClickEvent(){

    }
    private void setupSignInOnClickEvent(){

    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onLoginClick(View view){
        //TODO: login verification go here
    }

    public void onSignUpClicked(View view){
        //TODO: transfer to signUp page here
    }

    public void onForgotPasswordClicked(View view){
        //TODO: transfer to forgotPassword page here
        Fragment fragment = null;
        try {
            fragment = ForgotPasswordFragment.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commit();
    }

}
