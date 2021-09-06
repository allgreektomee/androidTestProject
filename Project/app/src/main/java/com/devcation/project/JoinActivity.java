package com.devcation.project;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class JoinActivity extends AppCompatActivity {
    EditText edtPhone;
    EditText edtName;
    EditText edtUserName;
    EditText edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edtPhone = findViewById(R.id.edtphone);
        edtName = findViewById(R.id.edtname);
        edtUserName = findViewById(R.id.edtusername);
        edtPassword = findViewById(R.id.edtpassword);

        Button btnResult = findViewById(R.id.result);
        btnResult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                builder.setTitle("가입");
                builder.setMessage("회원가입을 진행합니다");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "가입 : " +
                                        edtPhone.getText() +
                                        edtName.getText()+
                                        edtUserName.getText()+
                                        edtPassword.getText()
                                , Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNeutralButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Snackbar.make(view, "회원가입 취소.", Snackbar.LENGTH_SHORT).show();
                        edtPhone.setText("");
                        edtName.setText("");
                        edtUserName.setText("");
                        edtPassword.setText("");
                    }
                });
                builder.setNegativeButton("아니오2", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Snackbar.make(view, "회원가입 취소.", Snackbar.LENGTH_SHORT).show();
                        edtPhone.setText("");
                        edtName.setText("");
                        edtUserName.setText("");
                        edtPassword.setText("");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
    }

    public void onBtn4(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("AlertDialog");
        builder.setMessage("가입");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                Snackbar.make(view, "종료.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}