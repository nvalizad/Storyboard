package com.example.neema.storyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StartupActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    Button loginButton, signupButton;


    //update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {
            // start activity
            startActivity(new Intent(StartupActivity.this, SideMenu.class));
        }else {
            setContentView(R.layout.startup);

            loginButton = findViewById(R.id.loginSubmit);
            signupButton = findViewById(R.id.signupSubmit);
        }

    }

    public void loginPressed(View v) {
        startActivity(new Intent(StartupActivity.this, LoginActivity.class));

    }
    public void signupButtonPressed(View v) {
        startActivity(new Intent(StartupActivity.this, SignupActivity.class));

    }
}
