package com.example.soswedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.soswedding.ui.SignIn.SignInFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignInFragment.newInstance())
                    .commitNow();
        }
    }

}
