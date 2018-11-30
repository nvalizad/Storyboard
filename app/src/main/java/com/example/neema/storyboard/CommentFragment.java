package com.example.neema.storyboard;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    private CommentAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommentAdapter();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO fill in the stuff for the layout for the profile fragment
        View v = inflater.inflate(R.layout.comment_list,container,false);
        setupRecyclerView(v);
        return v;
    }
    private void setCommentAdapter() {
        List<String> comments = new ArrayList<>();
        //TODO READ FROM DATABASE AND SET UP CARD OBJECT

        for(int i = 0; i < 10; i++) {
            String s = "position " + i;
            comments.add(s);
        }
        mAdapter = new CommentAdapter(comments);
    }
    private void setupRecyclerView(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.myrecyleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
    }
}
