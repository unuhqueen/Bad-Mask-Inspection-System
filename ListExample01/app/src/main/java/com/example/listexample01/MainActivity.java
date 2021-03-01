package com.example.listexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list); //리스트뷰
        List<String> data = new ArrayList<>(); //리스트 선언
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data); //리스트뷰와 리스트를 연결하는 다리 역할, this는 이 activity
        list.setAdapter(adapter); //어댑터로 연결

        data.add("최승원");
        data.add("안드로이드");
        data.add("사과");
        adapter.notifyDataSetChanged(); //현재 상태 저장
    }
}