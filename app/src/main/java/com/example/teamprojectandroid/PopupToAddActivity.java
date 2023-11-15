package com.example.teamprojectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class PopupToAddActivity extends AppCompatActivity {

    EditText etAddMedicine, etAddMemo;
    TextView tvAddStartdate, tvAddFinishdate;
    ImageButton btnAddStartdate, btnAddFinishdate;
    CheckBox checkBoxAddDetail1, checkBoxAddDetail2, checkBoxAddDetail3, checkBoxAddDetail4, checkBoxAddDetail5;
    Button btnAddCancel, btnAddSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_to_add);

        initUI();

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvAddStartdate.setText(year+"-"+(month+1)+"-"+day);
            }
        }, year, month, day);

        DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvAddFinishdate.setText(year+"-"+(month+1)+"-"+day);
            }
        }, year, month, day);

        btnAddStartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();
            }
        });

        btnAddFinishdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog2.show();
            }
        });

        btnAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicine = etAddMedicine.getText().toString();
                String startdate = tvAddStartdate.getText().toString();
                String finishdate = tvAddFinishdate.getText().toString();
                int detail1 = 0;
                if(checkBoxAddDetail1.isChecked()) detail1 = 1;
                int detail2 = 0;
                if(checkBoxAddDetail2.isChecked()) detail2 = 1;
                int detail3 = 0;
                if(checkBoxAddDetail3.isChecked()) detail3 = 1;
                int detail4 = 0;
                if(checkBoxAddDetail4.isChecked()) detail4 = 1;
                int detail5 = 0;
                if(checkBoxAddDetail5.isChecked()) detail5 = 1;
                String memo = etAddMemo.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"저장되었습니다",Toast.LENGTH_SHORT).show();
                                ((HomeActivity)HomeActivity.context).refreshCal();
                                finish();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                CalAddRequest calAddRequest = new CalAddRequest(id,medicine,startdate,finishdate,detail1,detail2,detail3,detail4,detail5,memo,responseListener);
                RequestQueue queue = Volley.newRequestQueue(PopupToAddActivity.this);
                queue.add(calAddRequest);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE) return false;
        return true;
    }

    private void initUI(){
        etAddMedicine = (EditText) findViewById(R.id.etAddMedicine);
        etAddMemo = (EditText) findViewById(R.id.etAddMemo);
        tvAddStartdate = (TextView) findViewById(R.id.tvAddStartdate);
        tvAddFinishdate = (TextView) findViewById(R.id.tvAddFinishdate);
        btnAddStartdate = (ImageButton) findViewById(R.id.btnAddStartdate);
        btnAddFinishdate = (ImageButton) findViewById(R.id.btnAddFinishdate);
        checkBoxAddDetail1 = (CheckBox) findViewById(R.id.checkBoxAddDetail1);
        checkBoxAddDetail2 = (CheckBox) findViewById(R.id.checkBoxAddDetail2);
        checkBoxAddDetail3 = (CheckBox) findViewById(R.id.checkBoxAddDetail3);
        checkBoxAddDetail4 = (CheckBox) findViewById(R.id.checkBoxAddDetail4);
        checkBoxAddDetail5 = (CheckBox) findViewById(R.id.checkBoxAddDetail5);
        btnAddCancel = (Button) findViewById(R.id.btnAddCancel);
        btnAddSave = (Button) findViewById(R.id.btnAddSave);
    }
}