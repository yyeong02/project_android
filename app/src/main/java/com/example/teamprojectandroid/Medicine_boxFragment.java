package com.example.teamprojectandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.RelativeLayout;

import net.daum.mf.map.api.MapView;

import androidx.fragment.app.Fragment;


public class Medicine_boxFragment extends Fragment {

    RelativeLayout mapViewContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_medicine_box, container, false);

        MapView mapView = new MapView(requireContext());

        mapViewContainer = v.findViewById(R.id.map_view);
        if(mapViewContainer != null){
            mapViewContainer.addView(mapView);
        } else{
            Log.e("Medicine_boxFragment", "mapViewContainer is null"); //로그 에러 메세지
        }
        mapViewContainer.addView(mapView);
        return v;
    }
}