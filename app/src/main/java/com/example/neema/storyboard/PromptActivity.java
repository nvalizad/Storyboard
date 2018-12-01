package com.example.neema.storyboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PromptActivity extends AppCompatActivity {

    String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = mDatabase.getReference("CardTable");
    DatabaseReference mCommunityTable = mDatabase.getReference("CommunityTable");

    EditText draftText;
    TextView visibilityText;
    Switch privacySwitch;
    boolean isPublic, isNewCard;
    private String CardID, Uid, privacyText, currentUsername;

    //For editing title
    String titlePlaceholderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prompt);
        Intent intent = getIntent();
        titlePlaceholderText = getResources().getString(R.string.blank_string);

        draftText = findViewById(R.id.draftText);
        visibilityText = findViewById(R.id.visibilityText);

        privacySwitch = findViewById(R.id.privacySwitch);
        isPublic = privacySwitch.isChecked();
        isNewCard = true;
        getCurrentUsername();
        if (intent.getExtras()!= null){
            draftText.setText(intent.getStringExtra("Text"));

            CardID = intent.getStringExtra("CardId");
            Uid = intent.getStringExtra("uid");
            isPublic = (intent.getBooleanExtra("Pub", false));
            isNewCard = false;

            if (isPublic){
                visibilityText.setText(R.string.public_string);
                isPublic = true;
                privacySwitch.setChecked(true);
            }
            else {
                visibilityText.setText(R.string.private_string);
                isPublic = false;
                privacySwitch.setChecked(false);
            }

        }

        FloatingActionButton uploadButton = (FloatingActionButton) findViewById(R.id.uploadButton);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        privacySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    visibilityText.setText(R.string.public_string);
                }
                else {
                    visibilityText.setText(R.string.private_string);
                }
                isPublic = isChecked;
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder inputDialog = new AlertDialog.Builder(PromptActivity.this);
                if(isPublic){
                    inputDialog.setTitle(R.string.public_submission_string);

                } else {
                    inputDialog.setTitle(R.string.private_submission_string);
                }

                inputDialog.setNegativeButton(R.string.submit_string, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String cardId;
                        if (!isPublic) {
                            if (isNewCard) {
                                cardId = mRef.child(currentUser).child("Cards").push().getKey();
                                Card card = new Card(CardType.PROMPT, currentUser, currentUsername, cardId, titlePlaceholderText, draftText.getText().toString(), false);
                                mRef.child(currentUser).child("Cards").child(cardId).setValue(card);
                            }
                            else {
                                cardId = CardID;
                                Card card = new Card(CardType.PROMPT, currentUser, currentUsername, cardId, titlePlaceholderText, draftText.getText().toString(), false);
                                mRef.child(currentUser).child("Cards").child(cardId).setValue(card);
                            }
                        }
                        else {
                            if (isNewCard) {
                                cardId = mRef.child(currentUser).child("Cards").push().getKey();
                                Card card = new Card(CardType.PROMPT, currentUser, currentUsername, cardId, titlePlaceholderText, draftText.getText().toString(), true);
                                mRef.child(currentUser).child("Cards").child(cardId).setValue(card);

                                //Adds to community table
                                mCommunityTable.child(cardId).setValue(card);
                            }
                            else {
                                cardId = CardID;
                                Card card = new Card(CardType.PROMPT, currentUser, currentUsername, cardId, titlePlaceholderText, draftText.getText().toString(), true);
                                mRef.child(currentUser).child("Cards").child(cardId).setValue(card);

                                //Adds to community table
                                mCommunityTable.child(cardId).setValue(card);
                            }
                        }
                        Toast.makeText(getApplicationContext(),
                                R.string.successful_upload_string,
                                Toast.LENGTH_LONG).show();
                    }
                });

                inputDialog.setPositiveButton("Back to drafting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                inputDialog.show();
            }
        });


    }
    public void getCurrentUsername() {
        mDatabase.getReference("UserTable").child(currentUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUsername = (String) dataSnapshot.child("username").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
