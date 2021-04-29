package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
}
