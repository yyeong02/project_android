package com.example.teamprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tvId, tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvId = (TextView) findViewById(R.id.tvId);
        tvName = (TextView) findViewById(R.id.tvName);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");

        tvId.setText(id);
        tvName.setText(name);
    }


}