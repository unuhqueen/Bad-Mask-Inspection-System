package com.example.bad_mask_inspection_system;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("마스크 불량 검사 시스템");
//
//        actionBar.setDisplayHomeAsUpEnabled(true); //뒤로가기버튼
//        actionBar.setDisplayShowHomeEnabled(true); //홈 아이콘

        findViewById(R.id.goToLoginButton).setOnClickListener(onClickListener);
        findViewById(R.id.goToSignUpButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.goToLoginButton:
                    myStartActivity(LoginActivity.class);
                    break;
                case R.id.goToSignUpButton:
                    myStartActivity(SignUpActivity.class);
                    break;
            }
        }
    };

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
