package com.example.bad_mask_inspection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MaskInspectionActivity extends AppCompatActivity {

    private static final String TAG = "MaskInspectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_inspection);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            startFirstActivity();
        }

        int selectedItemIndex = getIntent().getIntExtra("EQUIP_SPINNER_ITEM", 0);
        int selectedItemIndex2 = getIntent().getIntExtra("MASK_SPINNER_ITEM", 0);

        TextView textView = (TextView) findViewById(R.id.equipSelected);
        textView.setText(String.valueOf(selectedItemIndex)+"호기");

        TextView textView2 = (TextView) findViewById(R.id.maskSelected);
        switch(selectedItemIndex2){
            case 1:
                textView2.setText("KF-94");
                break;
            case 2:
                textView2.setText("KF-80");
                break;
            case 3:
                textView2.setText("KF-AD");
                break;
        }
    }


    private void startFirstActivity() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}