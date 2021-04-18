package com.challenge.us.geolocation.app.modules.splash;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.databinding.ActivitySplashBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;
    private ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());
        splashPresenter.bindWith(this);
        setupSplashAnimation();
    }

    private void setupSplashAnimation() {
        Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_text_animation_in);
        splashBinding.activitySplashTextView.setAnimation(animationFadeIn);
        splashBinding.activitySplashTextView.startAnimation(animationFadeIn);
        splashBinding.activitySplashTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}