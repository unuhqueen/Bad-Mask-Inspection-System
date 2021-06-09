package com.example.bad_mask_inspection_system;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

//첫번째 화면

public class FirstActivity extends AppCompatActivity {

    private long pressedTime = 0;
    @Override
    //뒤로가기 버튼 누르면 종료되는 기능
    public void onBackPressed(){
        if ( pressedTime == 0 ) {
            Toast.makeText(FirstActivity.this, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(FirstActivity.this, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
                finish(); // app 종료 시키기
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

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
