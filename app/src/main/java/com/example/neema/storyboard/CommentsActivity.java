package com.example.neema.storyboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";

    private ListView mListView;

    private ArrayList<Comment> mComments = new ArrayList<Comment>();
    private String cardId, cardUserId;
    String currentUsername;
    String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef;
    private DatabaseReference mCardTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Log.d(TAG, "onCreate: Started.");
        getCurrentUsername();
        cardId = getIntent().getStringExtra("cardId");
        cardUserId = getIntent().getStringExtra("cardUserId");
        mRef = mFirebaseDatabase.getReference("CommentTable").child(cardId);
        mCardTable = mFirebaseDatabase.getReference("CardTable").child(cardUserId).child("Cards").child(cardId);
        initCardContent();

        init();

    }

    private void init(){
                mListView = (ListView) findViewById(R.id.commentsListView);

                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mComments.clear();
                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Comment comment = createComment(postSnapshot);
                            mComments.add(comment);
                        }
                        CommentsListAdapter adapter = new CommentsListAdapter(CommentsActivity.this, R.layout.comments_layout, mComments);
                        mListView.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void initCardContent(){

        final TextView title = (TextView) findViewById(R.id.postTitle);
        final TextView author = (TextView) findViewById(R.id.postAuthor);
        final TextView content = (TextView) findViewById(R.id.postContent);

        mCardTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String authorText = (String) dataSnapshot.child("username").getValue();
                String titleText = (String) dataSnapshot.child("title").getValue();
                String contentText = (String) dataSnapshot.child("text").getValue();
                author.setText(authorText);
                title.setText(titleText);
                content.setText(contentText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void replyPressed(View v){

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
                    String commentId = mRef.push().getKey();
                    mRef.child(commentId).setValue(new Comment(task, currentUsername, commentId));
                    }
                })
                .setNegativeButton(R.string.cancel_string, null)
                .create();
        dialog.show();
    }

    private Comment createComment(DataSnapshot postSnapshot) {
        String comment = (String) postSnapshot.child("comment").getValue();
        String author = (String) postSnapshot.child("author").getValue();
        String id = (String) postSnapshot.child("id").getValue();

        return new Comment(comment, author, id);
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

























