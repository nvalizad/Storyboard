package com.example.neema.storyboard;
import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Canvas;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    private CommentAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.comment_layout,container,false);
        setCardsDataAdapter(v);
        return v;
    }
    private void setCardsDataAdapter(final View v) {
        //TODO GET COMMENTS FROM DB
        List<String> comments = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            String s = "test" + i;
            comments.add(s);
        }
        setupRecyclerView(v);
        mAdapter = new CommentAdapter(comments);




    }
    private void setupRecyclerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
    }
}
