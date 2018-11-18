package com.example.neema.storyboard;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    //TODO ADD THINGS NEEDED FOR THE DATABASE SUCH AS NAME


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO fill in the stuff for the layout for the profile fragment
        return inflater.inflate(R.layout.home_screen,container,false);
    }
}
