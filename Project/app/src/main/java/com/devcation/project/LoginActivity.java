package com.devcation.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.devcation.project.data.ParcelData;
import com.devcation.project.main.MainBottomActivity;
import com.devcation.project.main.MainTabActivity;

public class LoginActivity extends AppCompatActivity {
    ParcelData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnJoin = findViewById(R.id.btnjoin);
        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveJoinActivity();
            }
        });

        Button btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                moveMainTabActivity();
            }
        });
    }

    private void moveJoinActivity() {
//        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
//        startActivityForResult(intent,1001);//액티비티 띄우기
//        ParcelData data = new ParcelData("코리아","신촌","01011112222","안녕하세요");

        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
        startActivityResult.launch(intent);
    }

    private void moveMainTabActivity() {
//        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
//        startActivityForResult(intent,1001);//액티비티 띄우기
//        ParcelData data = new ParcelData("코리아","신촌","01011112222","안녕하세요");

//        Intent intent = new Intent(LoginActivity.this, MainBottomActivity.class);

        Intent intent = new Intent(LoginActivity.this, MainTabActivity.class);
        intent.putExtra("join", data);
        startActivityResult.launch(intent);
    }


    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle bundle = result.getData().getExtras();
                        data  = (ParcelData) bundle.get("join");
                    }
                }
            });
}