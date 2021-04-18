package com.challenge.us.geolocation.app.modules.splash;

import android.os.SystemClock;

import androidx.test.filters.LargeTest;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.app.BaseActivityTest;

import org.junit.Test;

import dagger.hilt.android.testing.HiltAndroidTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@HiltAndroidTest
public class SplashActivityFlowTest extends BaseActivityTest<SplashActivity> {

    @Override
    public Class<SplashActivity> getActivity() {
        return SplashActivity.class;
    }

    @Test
    public void mustToFollowTheScreenFlowFromStartingToTheHomeScreen() {
        onView(withId(R.id.activity_splash_text_view))
                .check(matches(isDisplayed()));

        SystemClock.sleep(SplashPresenterImpl.SPLASH_ACTIVITY_DURATION);

        onView(withId(R.id.activity_home_view_container))
                .check(matches(isDisplayed()));

    }
}