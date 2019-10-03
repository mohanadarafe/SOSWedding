package com.example.soswedding.ui.forgotPassword;

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
import com.example.soswedding.ui.SignIn.SignInViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordFragment extends Fragment {

    private ForgotPasswordViewModel mViewModel;
    private EditText emailInput;
    private Button resetBtn;
    private Button cancelBtn;
    private FirebaseAuth auth;

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.forgot_password_fragment, container, false);
        initializeVariables(rootView);
        setupOnClickEvents();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);
        // TODO: Use the ViewModel
    }


    private void initializeVariables(View rootView) {
        emailInput = rootView.findViewById(R.id.emailForgotPasswordInput);
        resetBtn   = rootView.findViewById(R.id.resetBtn);
        cancelBtn  = rootView.findViewById(R.id.cancelBtn);
        auth       = FirebaseAuth.getInstance();
    }

    private void setupOnClickEvents() {
        setupCancelOnClickEvent();
        setupResetOnClickEvent();
    }

    private void setupCancelOnClickEvent() {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClicked();
            }
        });
    }

    private void setupResetOnClickEvent(){
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetClicked(v);
            }
        });
    }


    public void onCancelClicked(){
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

    public void onResetClicked(View view){
        if(emailInput.length()>0){
            verifyEmail(emailInput.getText().toString());
        }else {
            popUp("Fail to send reset password email!");
        }
    }

    private void popUp(String message){
        Toast t = Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_SHORT);
        t.show();
    }

    private void verifyEmail(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            popUp("Check email to reset your password!");
                        } else {
                            popUp("Fail to send reset password email!");
                        }
                    }
                });
    }
}
