package com.devcation.project;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.devcation.project.data.ParcelData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLoginActivity();
            }

        });

    }

    private void moveLoginActivity() {
//        Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
//        startActivityForResult(intent,1001);//액티비티 띄우기
//        ParcelData data = new ParcelData("코리아","신촌","01011112222","안녕하세요");

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityResult.launch(intent);
        overridePendingTransition(R.anim.move, R.anim.exit);
//        overridePendingTransition(보여지는 액티비티, 없어지는 액티비티);
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