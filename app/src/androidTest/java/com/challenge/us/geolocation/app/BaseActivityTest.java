package com.challenge.us.geolocation.app;


import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import dagger.hilt.android.testing.HiltAndroidRule;

public abstract class BaseActivityTest<T extends Activity> {

    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    final public ActivityTestRule<T> activityTestRule =
            new ActivityTestRule<>(getActivity(), true, false);

    @Before
    public void tearsUp() {
        hiltRule.inject();
        activityTestRule.launchActivity(new Intent());
    }

    public abstract Class<T> getActivity();
}
