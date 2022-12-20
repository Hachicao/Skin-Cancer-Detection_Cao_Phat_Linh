package com.example.projectairetrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.projectairetrofit.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout edt_uname, edt_pwd;
    private Button btn_login;
    private TextView tv_reg;
    private ImageView img_back;
    private ConstraintLayout ctHome;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        mapping();

        // move to register side
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myintent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        // login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_uname.getEditText().getText().toString();
                String pass = edt_pwd.getEditText().getText().toString();
                if (!doValidate()) {
                    Toast.makeText(LoginActivity.this, "Please enter your email and password...", Toast.LENGTH_LONG).show();
                    return;
                }
                postDataUsingVolley(name, pass);
            }
        });

        // back to previous side
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

    // post data to server
    public void postDataUsingVolley(String name, String pass) {
        String url = "https://4274-180-148-6-78.ap.ngrok.io/loginMobile";
        loadingPB.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingPB.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                        edt_uname.getEditText().setText("");
                        edt_pwd.getEditText().setText("");

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("placement");
                            if (data.equals("1")) {
                                Toast.makeText(getApplicationContext(), " Logged in successfully", Toast.LENGTH_LONG).show();
                                moveIntent();
                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessful! Wrong Username or Password", Toast.LENGTH_LONG).show();
                                edit();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(LoginActivity.this, "Fail to get respone = " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", edt_uname.getEditText().getText().toString().trim());
                params.put("pass", edt_pwd.getEditText().getText().toString().trim());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(stringRequest);
    }

    public void moveIntent() {
        Intent myintent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(myintent);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void edit() {
        Intent myintent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(myintent);
        finish();
    }

    // do Validate the information
    public boolean doValidate() {
        boolean isValid = true;
        if (this.edt_uname.getEditText().getText().length() == 0) {
            isValid = false;
            edt_uname.setError("Enter Username");

        } else {
            edt_uname.setEnabled(false);
            edt_uname.setError(null);
        }
        if (this.edt_pwd.getEditText().getText().length() == 0) {
            isValid = false;
            edt_pwd.setError("Enter Password!");
        } else {
            edt_pwd.setEnabled(false);
            edt_pwd.setError(null);
        }
        return isValid;
    }

    private void mapping() {
        edt_uname = findViewById(R.id.edt_uname);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_login = findViewById(R.id.btn_reg);
        tv_reg = findViewById(R.id.tv_register);
        img_back = findViewById(R.id.img_back);
        ctHome = findViewById(R.id.ct_home);
        loadingPB = findViewById(R.id.progressBar);
    }

}