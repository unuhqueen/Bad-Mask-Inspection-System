package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_id;
    Button btn_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id); //xml에서 선언한 et_id와 연결
        btn_test = findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener() { //bin_test 버튼을 클릭했을 때 취하는 액션
            @Override
            public void onClick(View v) {
                et_id.setText("최승원"); //버튼을 클릭하면 et_id에 "최승원"이라는 글씨를 써줘라
            }
        });
    }
}