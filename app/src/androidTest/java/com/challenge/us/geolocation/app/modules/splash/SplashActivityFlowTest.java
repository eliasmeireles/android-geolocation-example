package com.challenge.us.geolocation.app.modules.splash;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.challenge.us.geolocation.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@HiltAndroidTest
public class SplashActivityFlowTest {

    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    final public ActivityTestRule<SplashActivity> activityTestRule =
            new ActivityTestRule<>(SplashActivity.class, true, false);

    @Before
    public void tearsUp() {
        hiltRule.inject();
        activityTestRule.launchActivity(new Intent());
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