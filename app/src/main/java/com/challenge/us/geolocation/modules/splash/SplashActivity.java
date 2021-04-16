package com.challenge.us.geolocation.modules.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.modules.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_ACTIVITY_DURATION = 1500L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSplashAnimation();
        homeNavigator();
    }

    /**
     * Após o tempo em millisecond definidos em {@link #SPLASH_ACTIVITY_DURATION},
     * {@link SplashActivity} será finalizada e vagando para {@link com.challenge.us.geolocation.modules.home.HomeActivity}
     */
    private void homeNavigator() {
        new Handler(getMainLooper())
                .postDelayed(() -> {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }, SPLASH_ACTIVITY_DURATION);
    }

    private void setupSplashAnimation() {
        TextView animatedSplashTextView = findViewById(R.id.activity_splash_text_view);
        Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_text_animation_in);
        animatedSplashTextView.setAnimation(animationFadeIn);
        animatedSplashTextView.startAnimation(animationFadeIn);
        animatedSplashTextView.setVisibility(View.VISIBLE);
    }
}