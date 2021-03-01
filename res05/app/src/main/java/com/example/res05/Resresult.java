package com.example.res05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Resresult extends AppCompatActivity {
    int score;
    int rcause1;
    int rcause2;
    int rcause3;
    int rcause4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resresult);

        Intent intent = getIntent();
        String sc = intent.getExtras().getString("key1");
        System.out.println(sc);

        for(int i=1; i<21; i++) {
            sc = intent.getExtras().getString("key" + i);
            System.out.println(sc);
            int sco = Integer.parseInt(sc);
            switch(i) {
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case 7:
                case 13:
                case 20:
                    rcause1 += sco;
                    break;
                case 4 :
                case 8 :
                case 12 :
                case 16 :
                    rcause2 += 4 - sco;
                    break;
                case 9:
                case 14 :
                case 15:
                case 19:
                    rcause3 += sco;
                case 10:
                case 11:
                case 17:
                case 18:
                    rcause4 += sco;
            }
        }

        TextView sc0  = (TextView)findViewById(R.id.result);
        sc0.setText("총점수 " + (rcause1 + rcause2 + rcause3 + rcause4));

        TextView sc1 = (TextView)findViewById(R.id.result1);
        sc1.setText("신체저하 :" + rcause1);
        TextView sc2 = (TextView)findViewById(R.id.result2);
        sc2.setText("긍정정서 :"+rcause2);
        TextView sc3 = (TextView)findViewById(R.id.result3);
        sc3.setText("대인관계" +rcause3);
        TextView sc4 = (TextView)findViewById(R.id.result4);
        sc4.setText("우울정서" +rcause4);

    }
}
