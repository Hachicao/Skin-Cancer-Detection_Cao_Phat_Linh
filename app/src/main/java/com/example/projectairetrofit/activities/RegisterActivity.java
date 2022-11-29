package com.example.projectairetrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectairetrofit.DemoRetrofit;
import com.example.projectairetrofit.R;
import com.example.projectairetrofit.sendimage.DemoService;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout edt_userReg, edt_email, edt_pass, edt_phone;
    private Button btn_reg;
    private TextView tv_alredy, tv_back, tv_mess;
    private ConstraintLayout ctReg;
    private EditText edt_url;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        mapping();


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_userReg.getEditText().getText().toString().trim();
                String email = edt_email.getEditText().getText().toString().trim();
                String pass = edt_pass.getEditText().getText().toString().trim();
                String phone = edt_phone.getEditText().getText().toString().trim();
                System.out.println(name + "_---------------------");
                if (name.isEmpty() && email.isEmpty() && pass.isEmpty() && phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter the values", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(ctReg, "Please enter your fields...", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(name, email, pass, phone);
                moveIntent();

            }
        });

        tv_alredy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }


    public void postDataUsingVolley(String name, String email, String pass, String phone) {
        String url = " https://4b32-180-148-6-78.ap.ngrok.io/register";
        loadingPB.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        edt_userReg.getEditText().setText("");
                        edt_email.getEditText().setText("");
                        edt_pass.getEditText().setText("");
                        edt_phone.getEditText().setText("");
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String data = jsonObject.getString("placement");
                            if (data.equals("1")) {
                                Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e + " -------------------");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Fail to get respone = " + error.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println("-----------ERROR----------" + error.getMessage());
                    }
                }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", edt_userReg.getEditText().getText().toString().trim());
                params.put("email", edt_email.getEditText().getText().toString().trim());
                params.put("pass", edt_pass.getEditText().getText().toString().trim());
                params.put("phone", edt_phone.getEditText().getText().toString().trim());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(stringRequest);
    }

    public void moveIntent() {
        Intent myintent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(myintent);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void mapping() {
        edt_userReg = findViewById(R.id.edt_uname);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pwd);
        edt_phone = findViewById(R.id.edt_phone);
        btn_reg = findViewById(R.id.btn_reg);
        tv_alredy = findViewById(R.id.tv_already_have_acc);
        tv_back = findViewById(R.id.tv_back);
//        tv_mess = findViewById(R.id.tv_mess);
        ctReg = findViewById(R.id.ct_reg);
        edt_url = findViewById(R.id.edt_url);
        loadingPB = findViewById(R.id.progress_circular);

    }
}