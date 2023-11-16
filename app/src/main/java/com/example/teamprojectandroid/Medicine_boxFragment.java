package com.example.teamprojectandroid;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


public class Medicine_boxFragment extends Fragment {

    RelativeLayout mapViewContainer;
    Spinner spinner;
    String state;
    JSONObject jsonObject, object;
    JSONArray array;
    MapPOIItem marker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_medicine_box, container, false);

        spinner = v.findViewById(R.id.spinner);
        state = "중구";



        String json = "";
        try{
            InputStream is = getResources().getAssets().open("jsons/getdata.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            Log.d("--  json = ", json);

            jsonObject = new JSONObject(json);

            array = jsonObject.getJSONArray("data");
            for(int i=0;i<array.length();i++){
                object = array.getJSONObject(i);
                Log.d("--  gu : ", object.getString("gu"));
                Log.d("--  name : ", object.getString("name"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }



        MapView mapView = new MapView(getActivity());
        mapView.setZoomLevel(4,true);
        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mapView.removeAllPOIItems();
                state = adapterView.getItemAtPosition(position).toString();
                ArrayList<MapPOIItem> markerArray = new ArrayList<>();
                try{
                    for(int i=0;i<array.length();i++){
                        object = array.getJSONObject(i);
                        if(object.getString("gu").equals(state)){
                            marker = new MapPOIItem();
                            marker.setMapPoint(MapPoint.mapPointWithGeoCoord(object.getDouble("latitude"), object.getDouble("longitude")));
                            marker.setItemName(object.getString("name"));
                            markerArray.add(marker);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mapView.addPOIItems(markerArray.toArray(new MapPOIItem[markerArray.size()]));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.864706, 128.593298), true);
        mapView.setZoomLevel(4,true);

        return v;
    }
}