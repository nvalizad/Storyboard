package com.example.neema.storyboard;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button resetButton;
    EditText emailInput;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        resetButton = findViewById(R.id.resetPasswordButton);
        emailInput = findViewById(R.id.emailInput);
    }

    public void resetPressed(View v) {
        // Display an error next to the email if it is empty
        if (TextUtils.isEmpty(emailInput.getText())) {
            emailInput.setError(getString(R.string.email_required));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches()) {
            emailInput.setError(getString(R.string.email_not_valid));
        }
        else {
            final String userEmail = emailInput.getText().toString().trim();
            mFirebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), getString(R.string.email_sent), Toast.LENGTH_LONG).show();
                    } else {
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                        switch (errorCode) {
                            case "ERROR_USER_NOT_FOUND":
                                Toast.makeText(getApplicationContext(), getString(R.string.email_does_not_exist), Toast.LENGTH_LONG).show();
                                break;
                            case "ERROR_USER_DISABLED":
                                Toast.makeText(getApplicationContext(), getString(R.string.account_disabled), Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
}
