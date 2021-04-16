package com.challenge.us.geolocator.modules.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.challenge.us.geolocator.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textView = findViewById(R.id.activity_splash_text_view);
        Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_text_animation_in);
        textView.setAnimation(animationFadeIn);
        textView.startAnimation(animationFadeIn);
        textView.setVisibility(View.VISIBLE);
    }
}