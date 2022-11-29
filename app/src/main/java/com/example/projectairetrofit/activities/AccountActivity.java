package com.example.projectairetrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectairetrofit.R;

public class AccountActivity extends AppCompatActivity {
    private ImageView img_patient, img_doc;
    private TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);
        mapping();
        addListener();
    }

    public void addListener(){
        img_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        img_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(AccountActivity.this, WelcomePage.class);
                startActivity(myIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }
    public void mapping(){
        img_patient=  findViewById(R.id.img_patient);
        img_doc=  findViewById(R.id.img_doctor);
        tv_back= findViewById(R.id.tv_back);
    }
}