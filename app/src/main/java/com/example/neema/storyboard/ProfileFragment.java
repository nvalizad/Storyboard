package com.example.neema.storyboard;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment{
    TextView bio, username;
    Button composeButton;
    PopupMenu popupMenu;

    String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mRef = mFirebaseDatabase.getReference("UserTable").child(currentUser);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO fill in the stuff for the layout for the profile fragment
        return inflater.inflate(R.layout.profile,container,false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        bio = view.findViewById(R.id.bio);
        username = view.findViewById(R.id.usernameText);
        composeButton = view.findViewById(R.id.composeSubmit);
        setText();
        popupMenu = new PopupMenu(getActivity(), composeButton);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.compose_actions, popupMenu.getMenu());

        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPostButtonPressed();
            }
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.freeWriteOption:
                        Toast.makeText(getActivity(), R.string.freewrite_selected_string, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), FreeWriteActivity.class));
                        return true;
                    case R.id.promptOption:
                        Toast.makeText(getActivity(), R.string.prompt_selected_string, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), PromptActivity.class));
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    protected void settingFrag(View v) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }


    public void newPostButtonPressed() {
        popupMenu.show();
    }

    public void setText() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bio.setText((String) dataSnapshot.child("bio").getValue());
                username.setText((String) dataSnapshot.child("username").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
