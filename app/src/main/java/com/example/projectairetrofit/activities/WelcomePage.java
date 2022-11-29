package com.example.projectairetrofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectairetrofit.R;

public class WelcomePage extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    ImageView logo;
    //    LottieAnimationView lottieAnimationView_1, lottieAnimationView_2;
    Animation topAnim, bottomAnim;
    TextView slogan, sologan2;
    private Button btn_getstarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_page);

        btn_getstarted = (Button) findViewById(R.id.btn_getstarted);
        btn_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WelcomePage.this, AccountActivity.class);
                startActivity(myIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        setAnimationForScreen();

    }

    public void setAnimationForScreen() {
        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

//        lottieAnimationView_1 = findViewById(R.id.lottie);
//        lottieAnimationView_2 = findViewById(R.id.lottie_2);
        logo = findViewById(R.id.img_logo);
        slogan = findViewById(R.id.tv_slogan);
        sologan2 = findViewById(R.id.tv_sologan2);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(topAnim);
        sologan2.setAnimation(topAnim);
        btn_getstarted.setAnimation(topAnim);


//        splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView_1.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        slogan.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView_2.animate().translationY(-1000).setDuration(1000).setStartDelay(4000);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(WelcomePage.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//                Pair[] pairs= new Pair[2];
//                pairs[0]= new Pair<View,String>(logo,"logo_image");
//                pairs[1]= new Pair<View,String>(slogan,"logo_text");
//
//                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(WelcomePage.this,pairs);
//                    startActivity(intent,options.toBundle());
//                }
//            }
//        }, SPLASH_SCREEN);
    }
}