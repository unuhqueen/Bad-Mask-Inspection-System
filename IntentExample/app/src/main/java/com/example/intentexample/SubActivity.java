package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tv_sub = findViewById(R.id.tv_sub);


        Intent intent = getIntent(); //mainactivity에서 쏜 intent를 받음
        String str = intent.getStringExtra("str"); //String 변수이므로 String 형태로 받음

        tv_sub.setText(str);
    }
}