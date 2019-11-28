package com.example.soswedding.ui.register;

import androidx.fragment.app.FragmentManager;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.soswedding.MainMenu;
import com.example.soswedding.R;
import com.example.soswedding.model.User;
import com.example.soswedding.ui.SignIn.SignInFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment
{
    private EditText name;
    private EditText email;
    private EditText password;
    private RadioButton coupleRadioBtn;
    private RadioButton providerRadioBtn;
    private Button signUpBtn;
    private Button signInBtn;
    private FirebaseAuth mAuth;


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
        mAuth            = FirebaseAuth.getInstance();
        name             = rootView.findViewById(R.id.nameInput);
        email            = rootView.findViewById(R.id.emailInput);
        password         = rootView.findViewById(R.id.passwordInput);
        coupleRadioBtn   = rootView.findViewById(R.id.radioButton);
        providerRadioBtn = rootView.findViewById(R.id.radioButton2);
        signInBtn        = rootView.findViewById(R.id.signInBtn);
        signUpBtn        = rootView.findViewById(R.id.signUpBtn);

    }

    private void setupOnClickEvents() {
        setupSignUpOnClickEvent();
        setupSignInOnClickEvent();
    }

    private void setupSignUpOnClickEvent() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUpClicked();
            }
        });
    }
    private void onSignUpClicked(){
        //TODO: store the name and last name

        if(email.length()>0 && password.length()>0){
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(getContext(), MainMenu.class);
                        startActivity(i);
                    }
                    else {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
        else {
            Toast toast_2 = Toast.makeText(getActivity().getApplicationContext(), "Please Fill up the username and password fields before.",Toast.LENGTH_SHORT);
            toast_2.show();
        }
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
            fragmentManager.beginTransaction().replace(R.id.signUpContainer, fragment)
                    .commit();

        }


}
