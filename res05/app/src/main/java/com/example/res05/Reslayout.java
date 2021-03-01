package com.example.res05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Reslayout extends AppCompatActivity {
    int now = 1;
    int score = 0;
    String name = "res";
    Integer checked[];
    TextView rescon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reslayout);

        checked = new Integer[20];
        Button pb = (Button)findViewById(R.id.preButton);
        Button nb = (Button)findViewById(R.id.nextButton);
        Button cb = (Button)findViewById(R.id.closeB);
        rescon = (TextView)findViewById(R.id.rescon) ;
        final RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        final TextView pNum = (TextView)findViewById(R.id.pagenum);

        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = rg.getCheckedRadioButtonId();
                if(id!=-1) {
                    RadioButton rb = (RadioButton) findViewById(id);
                    System.out.println("now : " + now);
                    System.out.println("rb.toString :" + rb.getText().toString());
                    int score = rb.getText().toString().charAt(0) - '1';
                    System.out.println("score : " + score);
                    checked[now - 1] = score;
                    now++;
                    rg.clearCheck();
                    pNum.setText(now + "/20");
                    setresSet(now);
                }
                else {
                    Toast.makeText(Reslayout.this, "설문이 눌러지지 않았습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                if(id!=-1) {
                    RadioButton rb = (RadioButton) findViewById(id);
                    System.out.println("now : " + now);
                    System.out.println("rb.toString :" + rb.getText().toString());
                    int score = rb.getText().toString().charAt(0) - '0';
                    checked[now] = score;

                    now--;
                    rg.check(checked[now]);

                    pNum.setText(now + "/20");
                    setresSet(now);
                }
                else {
                    Toast.makeText(Reslayout.this, "설문이 눌러지지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }

    private void setresSet(int now) {
        switch(now){
            case 0 : now++;
            case 1 : rescon.setText(R.string.res1);
                break;
            case 2 : rescon.setText(R.string.res2);
                break;
            case 3 :  rescon.setText(R.string.res3);
                break;
            case 4 : rescon.setText(R.string.res4);
                break;
            case 5 :  rescon.setText(R.string.res5);
                break;
            case 6 : rescon.setText(R.string.res6);
                break;
            case 7 :  rescon.setText(R.string.res7);
                break;
            case 8 : rescon.setText(R.string.res8);
                break;
            case 9 :  rescon.setText(R.string.res9);
                break;
            case 10 : rescon.setText(R.string.res10);
                break;
            case 11 :  rescon.setText(R.string.res11);
                break;
            case 12 : rescon.setText(R.string.res12);
                break;
            case 13 :  rescon.setText(R.string.res13);
                break;
            case 14 : rescon.setText(R.string.res14);
                break;
            case 15 :  rescon.setText(R.string.res15);
                break;
            case 16 : rescon.setText(R.string.res16);
                break;
            case 17 :  rescon.setText(R.string.res17);
                break;
            case 18 : rescon.setText(R.string.res18);
                break;
            case 19 :  rescon.setText(R.string.res19);
                break;
            case 20 : rescon.setText(R.string.res20);
                break;
            case 21 :
                Intent intent = new Intent(Reslayout.this, Resresult.class);
                intent.putExtra("key1",checked[0].toString());
                intent.putExtra("key2",checked[1].toString());
                intent.putExtra("key3",checked[2].toString());
                intent.putExtra("key4",checked[3].toString());
                intent.putExtra("key5",checked[4].toString());
                intent.putExtra("key6",checked[5].toString());
                intent.putExtra("key7",checked[6].toString());
                intent.putExtra("key8",checked[7].toString());
                intent.putExtra("key9",checked[8].toString());
                intent.putExtra("key10",checked[9].toString());
                intent.putExtra("key11",checked[10].toString());
                intent.putExtra("key12",checked[11].toString());
                intent.putExtra("key13",checked[12].toString());
                intent.putExtra("key14",checked[13].toString());
                intent.putExtra("key15",checked[14].toString());
                intent.putExtra("key16",checked[15].toString());
                intent.putExtra("key17",checked[16].toString());
                intent.putExtra("key18",checked[17].toString());
                intent.putExtra("key19",checked[18].toString());
                intent.putExtra("key20",checked[19].toString());
                startActivityForResult(intent, 1);
                break;
        }

    }
}
