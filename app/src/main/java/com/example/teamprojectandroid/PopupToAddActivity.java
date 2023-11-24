package com.example.teamprojectandroid;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class PopupToAddActivity extends AppCompatActivity {

    EditText etAddMedicine, etAddMemo;
    TextView tvAddStartdate, tvAddFinishdate;
    ImageButton btnAddStartdate, btnAddFinishdate;
    CheckBox checkBoxAddDetail1, checkBoxAddDetail2, checkBoxAddDetail3, checkBoxAddDetail4, checkBoxAddDetail5;
    Button btnAddCancel, btnAddSave;

    FloatingActionButton floatingActionButton;
    Bitmap bitmap;
    ImageView imageView;
    TextRecognizer recognizer;
    String resultText ="";

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultPicture.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        public void onActivityResult(ActivityResult result) {
            if( result.getResultCode() == RESULT_OK && result.getData() != null){
                Bundle extras = result.getData().getExtras();
                bitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bitmap);
                InputImage image = InputImage.fromBitmap(bitmap, 0);
                Task<Text> t = recognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text text) {
                                float size = 0;
                                float boxSize = 0;

                                for(Text.TextBlock block : text.getTextBlocks()){
                                    if(size==0){
                                        resultText = block.getText();
                                        Rect box = block.getBoundingBox();
                                        boxSize = box.bottom - box.top;
                                        size = boxSize;

                                        if(resultText.contains("아침")) checkBoxAddDetail1.setChecked(true);
                                        if(resultText.contains("점심")) checkBoxAddDetail2.setChecked(true);
                                        if(resultText.contains("저녁")) checkBoxAddDetail3.setChecked(true);
                                    }else{
                                        String t = block.getText();
                                        Rect box = block.getBoundingBox();
                                        boxSize = box.bottom - box.top;
                                        if(boxSize>size){
                                            size = boxSize;
                                            resultText = block.getText();
                                        }
                                        if(t.contains("아침")) checkBoxAddDetail1.setChecked(true);
                                        if(t.contains("점심")) checkBoxAddDetail2.setChecked(true);
                                        if(t.contains("저녁")) checkBoxAddDetail3.setChecked(true);
                                    }
                                }

                                etAddMedicine.setText(resultText);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });
            }
        }
    });

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
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingBtn);
        imageView = (ImageView) findViewById(R.id.imageView);
        recognizer = TextRecognition.getClient(new KoreanTextRecognizerOptions.Builder().build());
    }
}