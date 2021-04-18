package com.challenge.us.geolocation.app.modules.home;

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
public class HomeActivityTest extends BaseActivityTest<HomeActivity> {

    @Override
    public Class<HomeActivity> getActivity() {
        return HomeActivity.class;
    }

    @Test
    public void mustToDisplayTheGoogleComponentAtHomeScreenWhenHomeScreenIsSuccessfulCreated() {
        onView(withId(R.id.activity_home_view_container))
                .check(matches(isDisplayed()));

        onView(withId(R.id.google_map_component))
                .check(matches(isDisplayed()));
    }
}