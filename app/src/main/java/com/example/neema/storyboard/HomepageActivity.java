package com.example.neema.storyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
