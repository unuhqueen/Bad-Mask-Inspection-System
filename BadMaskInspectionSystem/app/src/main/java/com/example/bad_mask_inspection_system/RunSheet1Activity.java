package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;

public class RunSheet1Activity extends AppCompatActivity {

    private static final String TAG = "RunSheet1Activity";
    String currentTime = Calendar.getInstance().getTime().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runsheet_1);

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
                    updateDB();
                    myStartActivity(RunSheet2Activity.class);
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

    private void updateDB() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String todayString = formatter.format(currentTime);

        DatePicker datePicker = (DatePicker)findViewById(R.id.dataPicker);
        String product = ((EditText) findViewById(R.id.product)).getText().toString();
        String lotNo = ((EditText) findViewById(R.id.lotNo)).getText().toString();
        String rollNo = ((EditText) findViewById(R.id.rollNo)).getText().toString();
        String width = ((EditText) findViewById(R.id.width)).getText().toString();
        String length = ((EditText) findViewById(R.id.length)).getText().toString();

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = format.format(calendar.getTime());

        int intWidth = new Integer(width).intValue();
        int intLength = new Integer(length).intValue();

        Map<String, Object> lotInfo = new HashMap<>();
        lotInfo.put("작업 일자", strDate);
        lotInfo.put("제품명", product);
        lotInfo.put("LOT.NO", lotNo);
        lotInfo.put("ROLL-No", rollNo);
        lotInfo.put("폭(mm)", intWidth);
        lotInfo.put("길이(M)", intLength);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("runsheet").document(currentTime)
                .set(lotInfo)
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
