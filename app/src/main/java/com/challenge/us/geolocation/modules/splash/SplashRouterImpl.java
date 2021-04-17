package com.challenge.us.geolocation.modules.splash;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.modules.home.HomeActivity;

public class SplashRouterImpl implements SplashRouter {

    private SplashView splashView;

    @Override
    public void bindWith(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void navigateToHome() {
        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    private AppCompatActivity getActivity() {
        return splashView.getActivity();
    }
}
