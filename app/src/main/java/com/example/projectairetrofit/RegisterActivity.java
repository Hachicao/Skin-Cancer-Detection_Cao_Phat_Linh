package com.example.projectairetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout edt_userReg, edt_email, edt_pass, edt_phone;
    private Button btn_reg;
    private TextView tv_alredy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        tv_alredy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });



    }

    public void mapping() {
        edt_userReg = findViewById(R.id.edt_uname);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pwd);
        edt_phone = findViewById(R.id.edt_phone);
        btn_reg = findViewById(R.id.btn_reg);
        tv_alredy = findViewById(R.id.tv_already_have_acc);

    }
}