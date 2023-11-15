package com.example.teamprojectandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.security.auth.callback.Callback;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CalFragment extends Fragment {

    String id, name;
    CalendarView calendarView;
//    TextView tvDateToday;
    Calendar cal;
    FloatingActionButton floatingActionButton;

    ArrayList<CalItem> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cal, container, false);

        initUI(v);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CalItemAdapter adapter = new CalItemAdapter();

        String id = this.getArguments().getString("id");
        String name = this.getArguments().getString("name");

        HashMap<String,HashMap<String,String>> medicines = new HashMap<String, HashMap<String,String>>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("medicines");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        HashMap<String, String> medicine = new HashMap<>();
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
//                        medicine.put("medicine_id",item.getString("medicine_id"));
                        medicines.put(i+"",medicine);
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

                adapter.removeAllItem();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String d = year+"-"+(month+1)+"-"+day;
                Date selectedDate = null;
                try {
                    selectedDate = format.parse(d);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                for(String key:medicines.keySet()){
                    try {
                        Date startdate = format.parse(medicines.get(key).get("startdate"));
                        Date finishdate = format.parse(medicines.get(key).get("finishdate"));

                        if((selectedDate.compareTo(startdate)>=0)&&(selectedDate.compareTo(finishdate)<=0)){
                            CalItem item = new CalItem(medicines.get(key).get("medicine"),medicines.get(key).get("startdate"),medicines.get(key).get("finishdate"),medicines.get(key).get("detail1"),medicines.get(key).get("detail2"),medicines.get(key).get("detail3"),medicines.get(key).get("detail4"),medicines.get(key).get("detail5"),medicines.get(key).get("memo"));
                            items.add(item);
                            adapter.addItem(item);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                recyclerView.setAdapter(adapter);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                switch (direction){
                    case ItemTouchHelper.LEFT:
                        CalItem deleteItem = items.get(position);

                        items.remove(position);
                        adapter.removeItem(position);
                        adapter.notifyItemRemoved(position);

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if(success){
                                        Toast.makeText(getContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getContext(),"ERROR",Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                ((HomeActivity)HomeActivity.context).refreshCal();
                            }
                        };

                        CalDelRequest calDelRequest = new CalDelRequest(id, deleteItem.getStartdate(), deleteItem.getFinishdate(),responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        queue.add(calDelRequest);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.parseColor("#FF0000"))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();
                super .onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PopupToAddActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        return v;
    }

    private void initUI(View v){
        calendarView = (CalendarView) v.findViewById(R.id.calendarView);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.floatingBtn);
    }
}