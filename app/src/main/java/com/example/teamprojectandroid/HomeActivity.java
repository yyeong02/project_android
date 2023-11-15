package com.example.teamprojectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;


public class HomeActivity extends AppCompatActivity {

    CalFragment calFragment;
    Medicine_boxFragment medicine_boxFragment;
    PharFragment pharFragment;
    QNAFragment qnaFragment;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);

        calFragment = new CalFragment();
        medicine_boxFragment = new Medicine_boxFragment();
        pharFragment = new PharFragment();
        qnaFragment = new QNAFragment();

        calFragment.setArguments(bundle);
        medicine_boxFragment.setArguments(bundle);
        pharFragment.setArguments(bundle);
        qnaFragment.setArguments(bundle);

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

    public void refreshCal(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.detach(calFragment).attach(calFragment).commit();
    }

}