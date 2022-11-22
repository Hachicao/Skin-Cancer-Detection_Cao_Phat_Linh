package com.example.projectairetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class WelcomePage extends AppCompatActivity {
//    private static int SPLASH_SCREEN = 5000;
//    ImageView splashImg;
//    LottieAnimationView lottieAnimationView_1, lottieAnimationView_2;
//    Animation topAnim, bottomAnim;
//    TextView slogan;
     private Button btn_getstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_page);

        btn_getstarted= (Button) findViewById(R.id.btn_getstarted);
        btn_getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(WelcomePage.this, AccountActivity.class);
                startActivity(myIntent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
//        setAnimationForScreen();

    }

//    public void setAnimationForScreen() {
//        // Animations
//        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
//
////        lottieAnimationView_1 = findViewById(R.id.lottie);
////        lottieAnimationView_2 = findViewById(R.id.lottie_2);
////        splashImg = findViewById(R.id.img);
////        slogan = findViewById(R.id.slogan);
//
//        splashImg.setAnimation(bottomAnim);
//        slogan.setAnimation(topAnim);
//        lottieAnimationView_1.setAnimation(topAnim);
//        lottieAnimationView_2.setAnimation(bottomAnim);
//
//
//        splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView_1.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        slogan.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView_2.animate().translationY(-1000).setDuration(1000).setStartDelay(4000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(WelcomePage.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
////                Pair[] pairs= new Pair[2];
////                pairs[0]= new Pair<View,String>(lottieAnimationView_1,"logo_image");
////                pairs[1]= new Pair<View,String>(slogan,"logo_text");
////
////                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
////                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(WelcomePage.this,pairs);
////                    startActivity(intent,options.toBundle());
////                }
//            }
//        }, SPLASH_SCREEN);
//    }
}