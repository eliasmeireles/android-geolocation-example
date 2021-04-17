package com.challenge.us.geolocation.app.modules.splash;

import android.os.Handler;

import com.challenge.us.geolocation.app.modules.home.HomeActivity;

public class SplashPresenterImpl implements SplashPresenter {

    public static final long SPLASH_ACTIVITY_DURATION = 1500L;

    private final SplashRouter splashRouter;
    private SplashView splashView;

    public SplashPresenterImpl(SplashRouter splashRouter) {
        this.splashRouter = splashRouter;
    }

    @Override
    public void bindWith(SplashView splashView) {
        this.splashRouter.bindWith(splashView);
        this.splashView = splashView;
        navigateHome();
    }

    /**
     * Após o tempo em millisecond definido em {@link #SPLASH_ACTIVITY_DURATION},
     * {@link SplashActivity} será finalizada e navegando para {@link HomeActivity}
     */
    private void navigateHome() {
        new Handler(splashView.getActivity().getMainLooper())
                .postDelayed(splashRouter::navigateToHome, SPLASH_ACTIVITY_DURATION);
    }
}
