package com.example.neema.storyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class HomepageActivity extends AppCompatActivity {

    private FlashCardsFragment flashcards;
    private ProfileFragment profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        profile = (ProfileFragment) getFragmentManager().findFragmentById(R.id.profile);
        flashcards = (FlashCardsFragment) getFragmentManager().findFragmentById(R.id.flashCardList);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
