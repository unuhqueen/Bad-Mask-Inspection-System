package com.example.bad_mask_inspection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        findViewById(R.id.loadButton).setOnClickListener(onClickListener);

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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loadButton:
                    downloadDirect();
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void downloadDirect(){
        int selectedItemIndex = getIntent().getIntExtra("EQUIP_SPINNER_ITEM", 0);
        int selectedItemIndex2 = getIntent().getIntExtra("MASK_SPINNER_ITEM", 0);
        String str = "";

        switch(selectedItemIndex2){
            case 1:
                str = "-KF-94";
                break;
            case 2:
                str = "-KF-80";
                break;
            case 3:
                str = "-KF-AD";
                break;
        }

        // ImageView in your Activity
        ImageView imageView = findViewById(R.id.badMaskImg1);
        StorageReference ref = FirebaseStorage.getInstance().getReference().child( Integer.toString(selectedItemIndex) + str + "/1");
        // Download directly from StorageReference using Glide // (See MyAppGlideModule for Loader registration)
        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // Glide 이용하여 이미지뷰에 로딩
                    Glide.with(MaskInspectionActivity.this)
                            .load(task.getResult())
                            .into(imageView);
                } else {
                    // URL을 가져오지 못하면 토스트 메세지
                    Toast.makeText(MaskInspectionActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}