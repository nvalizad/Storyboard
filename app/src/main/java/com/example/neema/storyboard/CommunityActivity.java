package com.example.neema.storyboard;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    private CommunityCardAdapter mAdapter;

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mFirebaseDatabase.getReference("CommunityTable");
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private String currentUser = mFirebaseAuth.getCurrentUser().getUid();
    List<Card> communityCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_page_layout);
        setCommunityCardAdapter();

    }

    private void setCommunityCardAdapter() {
        //TODO GET COMMUNITY CARDS

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Card card = createCard(postSnapshot);
                        communityCards.add(card);
                }
                mAdapter = new CommunityCardAdapter(communityCards);
                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    private Card createCard(DataSnapshot postSnapshot) {
        String cardTypeString = (String) postSnapshot.child("cardType").getValue();
        // Need to convert the database string of card type to CardType enum
        CardType cardType = getCardType(cardTypeString);

        String uid = currentUser;
        String cardId = (String) postSnapshot.child("cardId").getValue();
        String title = (String) postSnapshot.child("title").getValue();
        String text = (String) postSnapshot.child("text").getValue();
        boolean isPublic = (boolean) postSnapshot.child("public").getValue();
        String weekly = (String) postSnapshot.child("weeklyText").getValue();
        String userId = (String) postSnapshot.child("uid").getValue();
        String userName = (String) postSnapshot.child("username").getValue();

        Card card;
        switch (cardType) {
            case FREEWRITE:
                card = new Card(CardType.FREEWRITE, userId, userName, cardId, title, text, isPublic);
                break;
            case WEEKLY:
                card = new Card(CardType.WEEKLY, userId, cardId, title, text, isPublic, weekly);
                break;
            case PROMPT:
                card = new Card(CardType.PROMPT, userId, userName, cardId, "", text, isPublic);
                break;
            default:
                card = new Card(CardType.FREEWRITE, userId, userName, cardId, title, text, isPublic);
        }
        return card;

    }
    private CardType getCardType(String cardType) {
        switch (cardType) {
            case "FREEWRITE":
                return CardType.FREEWRITE;
            case "PROMPT":
                return CardType.PROMPT;
            case "WEEKLY":
                return CardType.WEEKLY;
        }
        return CardType.FREEWRITE;
    }
}
