package com.example.neema.storyboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomepageActivity extends AppCompatActivity {

    private FlashCardsFragment flashcards;
    private ProfileFragment profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        //profile = (ProfileFragment) getFragmentManager().findFragmentById(R.id.name);
        //flashcards = (FlashCardsFragment) getFragmentManager().findFragmentById(R.id.name);

    }

    public void weeklyChallengeTap (View v) {
        startActivity(new Intent(this, WeeklyChallengeActivity.class));
    }
}
