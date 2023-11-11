package com.example.teamprojectandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CalFragment extends Fragment {

    CalendarView calendarView;
//    TextView tvDateToday;
    Calendar cal;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cal, container, false);

        initUI(v);

        String id = this.getArguments().getString("id");
        String name = this.getArguments().getString("name");

        List medicines = new ArrayList<Object>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("medicines");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        Map<String, String> medicine = new HashMap<>();
                        medicine.put("id",item.getString("id"));
                        medicine.put("medicine",item.getString("medicine"));
                        medicine.put("startdate",item.getString("startdate"));
                        medicine.put("finishdate",item.getString("finishdate"));
                        medicine.put("detail1",item.getString("detail1"));
                        medicine.put("detail2",item.getString("detail2"));
                        medicine.put("detail3",item.getString("detail3"));
                        medicine.put("detail4",item.getString("detail4"));
                        medicine.put("detail5",item.getString("detail5"));
                        medicine.put("memo",item.getString("memo"));
                        medicines.add(medicine);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        CalRequest calRequest = new CalRequest(id,responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(calRequest);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

//                String selectedDate = year+"-"+(month+1)+"-"+day;
//
//                String text = selectedDate+"\n"+medicines.size()+"\n";
//                for(int i=0;i<medicines.size();i++){
//                    text += medicines.get(i);
//                    text += "\n";
//                }
//                tvDateToday.setText(text);
            }
        });

        return v;
    }

    private void initUI(View v){
        calendarView = (CalendarView) v.findViewById(R.id.calendarView);
//        tvDateToday = (TextView) v.findViewById(R.id.tvDateToday);
        recyclerView = v.findViewById(R.id.recyclerView);
    }
}