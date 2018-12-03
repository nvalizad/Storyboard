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
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartupActivity.this, LoginActivity.class));

                }
            });
            signupButton = findViewById(R.id.signupSubmit);
        }

    }


    protected void signupButtonPressed(View v) {
        startActivity(new Intent(StartupActivity.this, SignupActivity.class));

    }
}
