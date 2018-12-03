package com.example.neema.storyboard;

import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    Button signupButton;
    EditText username, fullName, email, password, passwordConfirm;
    String usernameString, fullNameString, emailString, passwordString, passwordConfirmString;

    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRefUserTable = mFirebaseDatabase.getReference("UserTable");
    DatabaseReference mRefCardTable = mFirebaseDatabase.getReference("CardTable");
    DatabaseReference mRefCommunityTable =  mFirebaseDatabase.getReference("CommunityTable");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupButton = findViewById(R.id.signupSubmit);
        username = findViewById(R.id.usernameInput);
        fullName = findViewById(R.id.fullNameInput);
        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        passwordConfirm = findViewById(R.id.confirmPasswordInput);
    }

    boolean isInfoEntered(String unIn, String fnIn, String eIn, String pwIn, String pwcIn) {
       return (unIn.length() > 0) && (fnIn.length() > 0) && (eIn.length() > 0) && (pwIn.length() > 0) && (pwIn.length() > 0);
    }
    public void signupPressed(View v) {
        usernameString = username.getText().toString();
        fullNameString = fullName.getText().toString();
        emailString = email.getText().toString();
        passwordString = password.getText().toString();
        passwordConfirmString = passwordConfirm.getText().toString();

        if (isInfoEntered(usernameString, fullNameString, emailString, passwordString, passwordConfirmString)) {
            if (passwordString.length() > 5) {
                if (passwordString.equals(passwordConfirmString)) {
                    if (Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                        registerRequest(usernameString, fullNameString, emailString, passwordString);
                    } else {
                        email.setError(getString(R.string.email_not_valid));
                    }
                } else {
                    passwordConfirm.setError(getString(R.string.error_password_mismatch));
                }
            }
            else {
                password.setError(getString(R.string.error_password_too_short));
            }
        }
        else {
            if (TextUtils.isEmpty(username.getText())) {
                username.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(fullName.getText())) {
                fullName.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(email.getText())) {
                email.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(password.getText())) {
                password.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(passwordConfirm.getText())) {
                passwordConfirm.setError(getString(R.string.error_field_required));
            }
        }
    }

    private void registerRequest(final String usernameString, final String fullNameString, final String emailString, String passwordString) {
        mFirebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // The user has a unique id that we will use as a primary key
                    String userId = mFirebaseAuth.getCurrentUser().getUid();
                    User newUser = new User(usernameString, fullNameString, emailString, userId);
                    mRefUserTable.child(userId).setValue(newUser);
                    mFirebaseAuth.getCurrentUser().sendEmailVerification();

                    String cardKey = mRefCardTable.child(userId).child("Cards").push().getKey();
                    mRefCardTable.child(userId).child("Cards").child(cardKey).setValue(new Card(CardType.FREEWRITE, userId, usernameString, cardKey,"Welcome","Welcome to Storyboard", false));
                    
                    Toast.makeText(getApplicationContext(), getString(R.string.registration_complete), Toast.LENGTH_LONG).show();
                }
                // Some error as occurred, e.g. email already used to create an account.
                else {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    switch (errorCode) {
                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            email.setError(getString(R.string.error_email_in_use));
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), getString(R.string.error_during_registering), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
