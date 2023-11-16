package com.example.teamprojectandroid;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class QNAFragment extends Fragment {

    LinearLayout containerLayout1, containerLayout2, containerLayout3, contentLayout1, contentLayout2, contentLayout3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_q_n_a, container, false);

        initUI(v);

        containerLayout1.setOnClickListener(this::onClick);
        containerLayout2.setOnClickListener(this::onClick);
        containerLayout3.setOnClickListener(this::onClick);

        return v;
    }

    private void initUI(View v){
        containerLayout1 = (LinearLayout) v.findViewById(R.id.containerLayout1);
        containerLayout2 = (LinearLayout) v.findViewById(R.id.containerLayout2);
        containerLayout3 = (LinearLayout) v.findViewById(R.id.containerLayout3);
        contentLayout1 = (LinearLayout) v.findViewById(R.id.contentLayout1);
        contentLayout2 = (LinearLayout) v.findViewById(R.id.contentLayout2);
        contentLayout3 = (LinearLayout) v.findViewById(R.id.contentLayout3);
    }

    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.containerLayout1) {
            if (contentLayout1.getVisibility() == v.VISIBLE)
                contentLayout1.setVisibility(v.GONE);

            else {
                contentLayout1.setVisibility(v.VISIBLE);
                contentLayout2.setVisibility(v.GONE);
                contentLayout3.setVisibility(v.GONE);
            }
        } else if (id == R.id.containerLayout2) {
            if (contentLayout2.getVisibility() == v.VISIBLE)
                contentLayout2.setVisibility(v.GONE);
            else {
                contentLayout1.setVisibility(v.GONE);
                contentLayout2.setVisibility(v.VISIBLE);
                contentLayout3.setVisibility(v.GONE);
            }
        } else if (id == R.id.containerLayout3) {
            if (contentLayout3.getVisibility() == v.VISIBLE)
                contentLayout3.setVisibility(v.GONE);
            else {
                contentLayout1.setVisibility(v.GONE);
                contentLayout2.setVisibility(v.GONE);
                contentLayout3.setVisibility(v.VISIBLE);
            }
        }
    }
}