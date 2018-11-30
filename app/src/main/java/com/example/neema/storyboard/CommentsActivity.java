package com.example.neema.storyboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/1/2017.
 */

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";

    private static String postURL;
    private static String postThumbnailURL;
    private static String postTitle;
    private static String postAuthor;
    private static String postUpdated;

    private int defaultImage;

    private String currentFeed;
    private ListView mListView;

    private ArrayList<Comment> mComments;
    private ProgressBar mProgressBar;
    private TextView progressText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Log.d(TAG, "onCreate: Started.");


        initCardContent();

        init();

    }

    private void init(){
                mListView = (ListView) findViewById(R.id.commentsListView);
                mComments = new ArrayList<Comment>();
                for(int i = 0; i < 10; i++) {
                    Comment c = new Comment("test" + i, "test" + i, "test" + i, "test" + i);
                    mComments.add(c);
                }
                //TODO GET COMMENTS FROM DB
                CommentsListAdapter adapter = new CommentsListAdapter(CommentsActivity.this, R.layout.comments_layout, mComments);
                mListView.setAdapter(adapter);
    }

    private void initCardContent(){
        //TODO FILL IN POST FROM DB (THE CARD OF WHAT USERS ARE COMMENTING ON)

        TextView title = (TextView) findViewById(R.id.postTitle);
        TextView author = (TextView) findViewById(R.id.postAuthor);
        TextView updated = (TextView) findViewById(R.id.postUpdated);
        Button btnReply = (Button) findViewById(R.id.btnPostReply);

        title.setText("test");
        author.setText("test");
        updated.setText("Test");

    }

}

























