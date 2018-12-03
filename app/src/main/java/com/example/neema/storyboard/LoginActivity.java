package com.example.neema.storyboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    Button loginButton, forgotPasswordButton;
    EditText emailInput, passwordInput;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = findViewById(R.id.loginSubmit);
        forgotPasswordButton = findViewById(R.id.forgotPasswordSubmit);
        emailInput = findViewById(R.id.emailLoginInput);
        passwordInput = findViewById(R.id.passwordLoginInput);
    }
    public void loginPressed(View v) {
        String emailString = emailInput.getText().toString();
        if (TextUtils.isEmpty(emailInput.getText())) {
            emailInput.setError(getString(R.string.error_field_required));
        }
        else if (TextUtils.isEmpty(passwordInput.getText())) {
            passwordInput.setError(getString(R.string.error_field_required));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            emailInput.setError(getString(R.string.email_not_valid));
        }
        else {
            loginRequest(emailInput.getText().toString(), passwordInput.getText().toString());
        }
    }
    public void forgotPasswordPressed(View v) {
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }

    private void loginRequest(String email, String password){
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, SideMenu.class));
                }
                else {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    switch (errorCode) {
                        case "ERROR_USER_NOT_FOUND":
                            Toast.makeText(getApplicationContext(), getString(R.string.email_does_not_exist), Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_USER_DISABLED":
                            Toast.makeText(getApplicationContext(), getString(R.string.account_disabled), Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_WRONG_PASSWORD":
                            Toast.makeText(getApplicationContext(), getString(R.string.invalid_login_credentials), Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });
    }
}
