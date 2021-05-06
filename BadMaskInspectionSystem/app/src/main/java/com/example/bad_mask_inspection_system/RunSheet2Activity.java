package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RunSheet2Activity extends AppCompatActivity {

    private static final String TAG = "RunSheet2Activity";
    String currentTime = getIntent().getStringExtra("CURRENT_TIME");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runsheet_2);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startFirstActivity();
        }

        findViewById(R.id.nextButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.nextButton:
                    myStartActivity(RunSheet3Activity.class);
                    updateDB();
                    break;
            }
        }
    };

    private void startFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.putExtra("CURRENT_TIME", currentTime);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private int intToString(String str) {
        int intStr = new Integer(str).intValue();
        return intStr;
    }

    private String timePickerToString(TimePicker timePicker){
        int hour, min;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            min = timePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(hour, min);
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String strTime = format.format(calendar.getTime());
        return strTime;

    }


    private void updateDB() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String todayString = formatter.format(currentTime);

        String taskPerson = ((EditText) findViewById(R.id.taskPerson)).getText().toString();
        String taskUnit = ((EditText) findViewById(R.id.taskUnit)).getText().toString();
        String taskNumber = ((EditText) findViewById(R.id.taskNumber)).getText().toString();
        TimePicker taskStartTime = (TimePicker)findViewById(R.id.taskStartTime);
        TimePicker taskQuitTime = (TimePicker)findViewById(R.id.taskQuitTime);

        int intTaskUnit = intToString(taskUnit);
        int intTaskNumber = intToString(taskNumber);
        String strTaskStartTime = timePickerToString(taskStartTime);
        String strTaskQuitTime = timePickerToString(taskQuitTime);

        Map<String, Object> lotPerformance = new HashMap<>();
        lotPerformance.put("작업자", taskPerson);
        lotPerformance.put("작업 시작 시간", strTaskStartTime);
        lotPerformance.put("작업 종료 시간", strTaskQuitTime);
        lotPerformance.put("작업 호기", intTaskUnit);
        lotPerformance.put("작업수", intTaskNumber);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("runsheet").document(currentTime)
                .set(lotPerformance)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
}
