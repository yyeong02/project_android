package com.example.teamprojectandroid;

import android.os.Bundle;
import android.view.ViewGroup;
import net.daum.mf.map.api.MapView;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

       // MapView mapView = new MapView(this);

       // ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        //mapViewContainer.addView(mapView);
    }
}

