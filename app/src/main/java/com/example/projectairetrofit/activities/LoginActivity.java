package com.example.projectairetrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.projectairetrofit.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout edt_uname, edt_pwd;
    private Button btn_login;
    private TextView tv_reg;
    private ImageView img_back;
    private ConstraintLayout ctHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        mapping();


        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= edt_uname.getEditText().getText().toString();
                String pass= edt_pwd.getEditText().getText().toString();
                if(TextUtils.isEmpty(name) && TextUtils.isEmpty(pass)){
//                    Toast.makeText(LoginActivity.this,"Please enter your email and password...",Toast.LENGTH_LONG).show();
                    Snackbar.make(ctHome, "Please enter your email and password...", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Intent myintent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent1 = new Intent(LoginActivity.this, AccountActivity.class);
                startActivity(myintent1);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }


    private void mapping() {
        edt_uname = findViewById(R.id.edt_uname);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_login = findViewById(R.id.btn_reg);
        tv_reg = findViewById(R.id.tv_register);
        img_back = findViewById(R.id.img_back);
        ctHome= findViewById(R.id.ct_home);
    }

}