package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RunSheet3Activity extends AppCompatActivity {

    private static final String TAG = "RunSheet3Activity";
    String currentTime = getIntent().getStringExtra("CURRENT_TIME");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runsheet_3);

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
                    myStartActivity(RunSheet4Activity.class);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private int intToString(String str) {
        int intStr = new Integer(str).intValue();
        return intStr;
    }

    private void updateDB() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String todayString = formatter.format(currentTime);

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


        Map<String, Object> defectiveItem = new HashMap<>();
        defectiveItem.put("이물 양품화 가능(재활) 불량", intForeignMatterAble);
        defectiveItem.put("이물 양품화 불가(불용) 불량", intForeignMatterDisable);
        defectiveItem.put("N/W코편 짧음 양품화 가능(재활) 불량", intNWShortAble);
        defectiveItem.put("N/W코편 짧음 양품화 불가(불용) 불량", intNWShortDisable);
        defectiveItem.put("N/W코편 누락 양품화 가능(재활) 불량",  intNWOmissionAble);
        defectiveItem.put("N/W코편 누락 양품화 불가(불용) 불량", intNWOmissionDisable);
        defectiveItem.put("N/W코편 융착불량 양품화 가능(재활) 불량",  intNWFusionAble);
        defectiveItem.put("N/W코편 융착불량 양품화 불가(불용) 불량", intNWFusionDisable);
        defectiveItem.put("원단성형 융착불량 양품화 가능(재활) 불량",  intFabricFusionAble);
        defectiveItem.put("원단성형 융착불량 양품화 불가(불용) 불량", intFabricFusionDisable);
        defectiveItem.put("얼룩/오염 양품화 가능(재활) 불량",  intStainAble);
        defectiveItem.put("얼룩/오염 양품화 불가(불용) 불량", intStainDisable);
        defectiveItem.put("이어밴드 양품화 가능(재활) 불량",  intEarbandAble);
        defectiveItem.put("이어밴드 양품화 불가(불용) 불량", intEarbandDisable);
        defectiveItem.put("편심 양품화 가능(재활) 불량",  intEccentricityAble);
        defectiveItem.put("편심 양품화 불가(불용) 불량", intEccentricityDisable);
        defectiveItem.put("기타 양품화 가능(재활) 불량",  intEtcAble);
        defectiveItem.put("기타 양품화 가능(재활) 불량",  intEtcDisable);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("runsheet").document(currentTime)
                .set(defectiveItem)
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
