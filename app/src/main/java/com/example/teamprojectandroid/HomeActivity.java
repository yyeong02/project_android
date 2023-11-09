package com.example.teamprojectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class HomeActivity extends AppCompatActivity {

    CalFragment calFragment;
    Medicine_boxFragment medicine_boxFragment;
    PharFragment pharFragment;
    QNAFragment qnaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        calFragment = new CalFragment();
        medicine_boxFragment = new Medicine_boxFragment();
        pharFragment = new PharFragment();
        qnaFragment = new QNAFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, calFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.cal) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, calFragment).commit();
                    return true;
                } else if (itemId == R.id.medicine_box) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, medicine_boxFragment).commit();
                    return true;
                } else if (itemId == R.id.phar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, pharFragment).commit();
                    return true;
                } else if (itemId == R.id.qna) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, qnaFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

}