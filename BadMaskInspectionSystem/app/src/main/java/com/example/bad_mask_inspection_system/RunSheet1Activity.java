package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

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
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runsheet_1);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startFirstActivity();
        }

        next_button();
    }

    public void next_button() {

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                updateDB();
            }

        });

    }

    private void startFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

//    private void myStartActivity(Class c) {
//        Intent intent = new Intent(this, c);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);
//    }

    private int intToString(String str) {
        int intStr = new Integer(str).intValue();
        return intStr;
    }

    private String timePickerToString(TimePicker timePicker){
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String strTime = format.format(calendar.getTime());
        return strTime;

    }

    private String datePickerToString(DatePicker datePicker){
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = format.format(calendar.getTime());

        return strDate;
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
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

        String taskPerson = ((EditText) findViewById(R.id.taskPerson)).getText().toString();
        String taskUnit = ((EditText) findViewById(R.id.taskUnit)).getText().toString();
        String taskNumber = ((EditText) findViewById(R.id.taskNumber)).getText().toString();
        TimePicker taskStartTime = (TimePicker)findViewById(R.id.taskStartTime);
        TimePicker taskQuitTime = (TimePicker)findViewById(R.id.taskQuitTime);

        String foreignMatterAble = ((EditText) findViewById(R.id.foreignMatterAble)).getText().toString();
        String foreignMatterDisable = ((EditText) findViewById(R.id.foreignMatterDisable)).getText().toString();
        String nwShortAble = ((EditText) findViewById(R.id.nwShortAble)).getText().toString();
        String nwShortDisable = ((EditText) findViewById(R.id.nwShortDisable)).getText().toString();
        String nwOmissionAble = ((EditText) findViewById(R.id.nwOmissionAble)).getText().toString();
        String nwOmissionDisable = ((EditText) findViewById(R.id.nwOmissionDisable)).getText().toString();
        String nwFusionAble = ((EditText) findViewById(R.id.nwFusionAble)).getText().toString();
        String nwFusionDisable = ((EditText) findViewById(R.id.nwFusionDisable)).getText().toString();
        String fabricFusionAble = ((EditText) findViewById(R.id.fabricFusionAble)).getText().toString();
        String fabricFusionDisable = ((EditText) findViewById(R.id.fabricFusionDisable)).getText().toString();
        String stainAble = ((EditText) findViewById(R.id.stainAble)).getText().toString();
        String stainDisable = ((EditText) findViewById(R.id.stainDisable)).getText().toString();
        String earbandAble = ((EditText) findViewById(R.id.earbandAble)).getText().toString();
        String earbandDisable = ((EditText) findViewById(R.id.earbandDisable)).getText().toString();
        String eccentricityAble = ((EditText) findViewById(R.id.eccentricityAble)).getText().toString();
        String eccentricityDisable = ((EditText) findViewById(R.id.eccentricityDisable)).getText().toString();
        String etcAble = ((EditText) findViewById(R.id.etcAble)).getText().toString();
        String etcDisable = ((EditText) findViewById(R.id.etcDisable)).getText().toString();

        String fabricFold = ((EditText) findViewById(R.id.fabricFold)).getText().toString();
        String earband = ((EditText) findViewById(R.id.earband)).getText().toString();
        String nwNose = ((EditText) findViewById(R.id.nwNose)).getText().toString();
        String rotatingBundle = ((EditText) findViewById(R.id.rotatingBundle)).getText().toString();
        String furision = ((EditText) findViewById(R.id.furision)).getText().toString();
        String sensor = ((EditText) findViewById(R.id.sensor)).getText().toString();
        String switchFabric = ((EditText) findViewById(R.id.switchFabric)).getText().toString();
        String restTime = ((EditText) findViewById(R.id.restTime)).getText().toString();

        String strDate = datePickerToString(datePicker);
        int intWidth = intToString(width);
        int intLength = intToString(length);

        int intTaskUnit = intToString(taskUnit);
        int intTaskNumber = intToString(taskNumber);
        String strTaskStartTime = timePickerToString(taskStartTime);
        String strTaskQuitTime = timePickerToString(taskQuitTime);

        int intForeignMatterAble = intToString(foreignMatterAble);
        int intForeignMatterDisable = intToString(foreignMatterDisable);
        int intNWShortAble = intToString(nwShortAble);
        int intNWShortDisable = intToString(nwShortDisable);
        int intNWOmissionAble = intToString(nwOmissionAble);
        int intNWOmissionDisable = intToString(nwOmissionDisable);
        int intNWFusionAble = intToString(nwFusionAble);
        int intNWFusionDisable = intToString(nwFusionDisable);
        int intFabricFusionAble = intToString(fabricFusionAble);
        int intFabricFusionDisable = intToString(fabricFusionDisable);
        int intStainAble = intToString(stainAble);
        int intStainDisable = intToString(stainDisable);
        int intEarbandAble = intToString(earbandAble);
        int intEarbandDisable = intToString(earbandDisable);
        int intEccentricityAble = intToString(eccentricityAble);
        int intEccentricityDisable = intToString(eccentricityDisable);
        int intEtcAble = intToString(etcAble);
        int intEtcDisable = intToString(etcDisable);

        int intFabricFold = intToString(fabricFold);
        int intEarband = intToString(earband);
        int intNwNose = intToString(nwNose);
        int intRotatingBundle = intToString(rotatingBundle);
        int intFurision = intToString(furision);
        int intSensor = intToString(sensor);
        int intSwitchFabric = intToString(switchFabric);
        int intRestTime = intToString(restTime);

        Map<String, Object> lotInfo = new HashMap<>();
        lotInfo.put("작업 일자", strDate);
        lotInfo.put("제품명", product);
        lotInfo.put("LOT.NO", lotNo);
        lotInfo.put("ROLL-No", rollNo);
        lotInfo.put("폭(mm)", intWidth);
        lotInfo.put("길이(M)", intLength);

        lotInfo.put("작업자", taskPerson);
        lotInfo.put("작업 시작 시간", strTaskStartTime);
        lotInfo.put("작업 종료 시간", strTaskQuitTime);
        lotInfo.put("작업 호기", intTaskUnit);
        lotInfo.put("작업수", intTaskNumber);

        lotInfo.put("이물 양품화 가능(재활) 불량", intForeignMatterAble);
        lotInfo.put("이물 양품화 불가(불용) 불량", intForeignMatterDisable);
        lotInfo.put("N/W코편 짧음 양품화 가능(재활) 불량", intNWShortAble);
        lotInfo.put("N/W코편 짧음 양품화 불가(불용) 불량", intNWShortDisable);
        lotInfo.put("N/W코편 누락 양품화 가능(재활) 불량",  intNWOmissionAble);
        lotInfo.put("N/W코편 누락 양품화 불가(불용) 불량", intNWOmissionDisable);
        lotInfo.put("N/W코편 융착불량 양품화 가능(재활) 불량",  intNWFusionAble);
        lotInfo.put("N/W코편 융착불량 양품화 불가(불용) 불량", intNWFusionDisable);
        lotInfo.put("원단성형 융착불량 양품화 가능(재활) 불량",  intFabricFusionAble);
        lotInfo.put("원단성형 융착불량 양품화 불가(불용) 불량", intFabricFusionDisable);
        lotInfo.put("얼룩/오염 양품화 가능(재활) 불량",  intStainAble);
        lotInfo.put("얼룩/오염 양품화 불가(불용) 불량", intStainDisable);
        lotInfo.put("이어밴드 양품화 가능(재활) 불량",  intEarbandAble);
        lotInfo.put("이어밴드 양품화 불가(불용) 불량", intEarbandDisable);
        lotInfo.put("편심 양품화 가능(재활) 불량",  intEccentricityAble);
        lotInfo.put("편심 양품화 불가(불용) 불량", intEccentricityDisable);
        lotInfo.put("기타 양품화 가능(재활) 불량",  intEtcAble);
        lotInfo.put("기타 양품화 불가(불응) 불량",  intEtcDisable);

        lotInfo.put("원단 접힙/말림",  intFabricFold);
        lotInfo.put("이어밴드",  intEarband);
        lotInfo.put("N/W코편",  intNwNose);
        lotInfo.put("회전뭉치",  intRotatingBundle);
        lotInfo.put("융착",  intFurision);
        lotInfo.put("센서",  intSensor);
        lotInfo.put("부품교체",  intSwitchFabric);
        lotInfo.put("휴식/식사",  intRestTime);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("runsheet").document(currentTime)
                .set(lotInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("업로드되었습니다.");
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