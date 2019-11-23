package com.example.soswedding.ui.SignIn;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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

import androidx.fragment.app.FragmentManager;
import com.example.soswedding.MainMenu;
import com.example.soswedding.R;
import com.example.soswedding.ui.forgotPassword.ForgotPasswordFragment;
import com.example.soswedding.ui.register.RegisterFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBtn;
    private Button signUpBtn;
    private Button forgotPasswordBtn;
    private FirebaseAuth auth;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initializeVariables(View rootView) {
        emailInput        = rootView.findViewById(R.id.emailInput);
        passwordInput     = rootView.findViewById(R.id.passwordInput);
        loginBtn          = rootView.findViewById(R.id.loginBtn);
        signUpBtn         = rootView.findViewById(R.id.signUpBtn);
        forgotPasswordBtn = rootView.findViewById(R.id.forgotPasswordBtn);
        auth              = FirebaseAuth.getInstance();
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
        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onSignUpClicked(view);
            }
        });
    }
    private void setupSignInOnClickEvent(){
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onLoginClick(view);
            }
        });
    }

    public void onLoginClick(View view){
        if(emailInput.length()>0 && passwordInput.length()>0){
            verifyLogIn(emailInput.getText().toString(),passwordInput.getText().toString() );
        } else {
            popUp("Please Fill up the username and password fields before.");
        }
    }

    private void verifyLogIn(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity() , new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    logIn();
                } else {
                    popUp("Invalid Email or Password. Please try again.");
                }
            }
        });
    }

    public void logIn(){
        Intent i = new Intent(getContext(), MainMenu.class);
        startActivity(i);
        //TODO:  redirect to which page then ? :3 Also, remove the Toast above.
    }

    public void popUp(String message){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onSignUpClicked(View view){
        //TODO: transfer to registerFragment page here
        Fragment fragment = null;
        try {
            fragment = RegisterFragment.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }

        //Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.signUpContainer, fragment).commit();

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
        fragmentManager.beginTransaction().replace(R.id.signUpContainer, fragment).commit();
    }
}
