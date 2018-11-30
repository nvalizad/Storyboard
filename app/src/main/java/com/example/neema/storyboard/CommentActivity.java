package com.example.neema.storyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Comment;

public class CommentActivity  extends AppCompatActivity {

    private CommentFragment comments;
    private ViewCardContent cardContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card2);
        comments = (CommentFragment) getFragmentManager().findFragmentById(R.id.cardContent);
        //cardContent = (ViewCardContent) getFragmentManager().findFragmentById(R.id.cardContent);
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    protected void newPostButtonPressed() {

    }
}
