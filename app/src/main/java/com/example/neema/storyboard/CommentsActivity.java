package com.example.neema.storyboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";

    private ListView mListView;

    private ArrayList<Comment> mComments;

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
                for(int i = 0; i < 20; i++) {
                    Comment c = new Comment("comment" + i, "comment" + i, "comment" + i, "comment" + i);
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
        //TODO SET A LISTENER FOR BUTTON IF WE DECIDE TO USE REPLY BUTTON
        //Button btnReply = (Button) findViewById(R.id.btnPostReply);

        title.setText("CARD");
        author.setText("CARD");
        updated.setText("CARD");

    }
    protected void replyPressed(View v){

        final EditText commentText = new EditText(this);
        commentText.setMaxLines(5);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add New Comment")
                .setView(commentText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(commentText.getText());
                        //TODO: add comment into database and maybe into view
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}

























