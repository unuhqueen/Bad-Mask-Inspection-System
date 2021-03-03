package com.example.bad_mask_inspection_system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("회원가입");
//
//        actionBar.setDisplayHomeAsUpEnabled(true); //뒤로가기버튼
//        actionBar.setDisplayShowHomeEnabled(true); //홈 아이콘

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signUpButton:
                    signUp();
                    break;
            }
        }
    };

    private void signUp() {
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
        String name = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
        String ownNumber = ((EditText) findViewById(R.id.editTextTextOwnNumber)).getText().toString();

        if (name.length() > 0 && ownNumber.length() > 0 && email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입에 성공하였습니다.");
                                    String email = user.getEmail();
                                    String uid = user.getUid();

                                    //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                    HashMap<Object, String> hashMap = new HashMap<>();

                                    hashMap.put("uid", uid);
                                    hashMap.put("email", email);
                                    hashMap.put("name", name);
                                    hashMap.put("ownNumber", ownNumber);

                                    startLoginActivity();

                                } else {
                                    if (task.getException() != null) {
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }
        } else {
            startToast("입력하지 않은 항목이 있습니다.");
        }


    }

    private void startToast(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
