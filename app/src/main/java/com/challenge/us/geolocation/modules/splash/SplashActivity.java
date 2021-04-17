package com.challenge.us.geolocation.modules.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.challenge.us.geolocation.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter.bindWith(this);
        setupSplashAnimation();
    }

    private void setupSplashAnimation() {
        TextView animatedSplashTextView = findViewById(R.id.activity_splash_text_view);
        Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_text_animation_in);
        animatedSplashTextView.setAnimation(animationFadeIn);
        animatedSplashTextView.startAnimation(animationFadeIn);
        animatedSplashTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}