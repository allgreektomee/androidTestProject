package com.devcation.project.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.devcation.project.LoginActivity;
import com.devcation.project.R;
import com.devcation.project.data.ParcelData;

public class MainTabActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintab);
        Intent i = getIntent();
        if (i.getParcelableExtra("join") != null){
            userDataSetting(i.getParcelableExtra("join"));
        }

    }
    private void userDataSetting(ParcelData data )
    {

        textView = findViewById(R.id.textView);
        textView.setText("name "+ data.name +
                "\nuserName "+data.userName+
                "\nphoneNumber "+data.phoneNumber+
                "\npassword "+data.password);
    }

    private void moveLoginActivity() {
//        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
//        startActivityForResult(intent,1001);//액티비티 띄우기
//        ParcelData data = new ParcelData("코리아","신촌","01011112222","안녕하세요");

        Intent intent = new Intent(MainTabActivity.this, LoginActivity.class);
        startActivityResult.launch(intent);
    }


    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                    }
                }
            });
}