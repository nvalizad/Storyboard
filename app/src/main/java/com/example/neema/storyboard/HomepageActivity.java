package com.example.neema.storyboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class HomepageActivity extends AppCompatActivity {

    private FlashCardsFragment flashcards;
    private ProfileFragment profile;
    private Button settingsButton, newPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        profile = (ProfileFragment) getFragmentManager().findFragmentById(R.id.profile);
        //flashcards = (FlashCardsFragment) getFragmentManager().findFragmentById(R.id.cardList);

    }

    protected void newPostButtonPressed() {
    }

    public void weeklyChallengeTap (View v) {
        startActivity(new Intent(this, WeeklyChallengeActivity.class));
    }
}
