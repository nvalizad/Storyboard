package com.example.neema.storyboard;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WeeklyChallengeActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mFirebaseDatabase.getReference("Weekly");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_challenge_activity);

        mRef.child("Challenge").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String weeklyChallenge = (String) dataSnapshot.child("text").getValue();
                ((TextView) findViewById(R.id.challengeText)).setText(weeklyChallenge);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void saveChallengeToCard (View v) {
        String userText = ((EditText) findViewById(R.id.editText)).getText().toString();
        String weeklyText = ((TextView) findViewById(R.id.challengeText)).getText().toString();
        String uid = mFirebaseAuth.getUid();

        Card card = new Card(CardType.WEEKLY, uid, "", userText, false, weeklyText);

        DatabaseReference cardRef = mFirebaseDatabase.getReference("CardTable");
        String cardId = cardRef.child(uid).child("Cards").push().getKey();
        cardRef.child(uid).child("Cards").child(cardId).setValue(card);
    }
}
