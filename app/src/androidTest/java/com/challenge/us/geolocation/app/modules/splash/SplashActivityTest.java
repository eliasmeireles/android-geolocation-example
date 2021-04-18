package com.challenge.us.geolocation.app.modules.splash;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.challenge.us.geolocation.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
import static junit.framework.TestCase.assertTrue;

/**
 * Validation that the {@link SplashActivity} is successful started
 * with fake implementation of {@link SplashRouter} and {@link SplashPresenter}
 */

@LargeTest
@UninstallModules(SplashModule.class)
@HiltAndroidTest
public class SplashActivityTest {

    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    private final SplashRouter splashRouter = new SplashRouterFake();

    private final SplashPresenterFake presenter = new SplashPresenterFake();

    @Module
    @InstallIn(ApplicationComponent.class)
    public class SplashModuleTest {

        @Provides
        SplashPresenter presenter() {
            presenter.setRouterMock(splashRouter);
            return presenter;
        }
    }

    final public ActivityTestRule<SplashActivity> activityTestRule =
            new ActivityTestRule<>(SplashActivity.class, true, false);

    @Before
    public void tearsUp() {
        hiltRule.inject();
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void mustToInjectAPresenterAsAnInstanceOfSplashPresenterFake() {
        assertTrue(activityTestRule.getActivity().splashPresenter instanceof SplashPresenterFake);
    }
}