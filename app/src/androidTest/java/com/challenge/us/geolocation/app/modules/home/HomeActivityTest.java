package com.challenge.us.geolocation.app.modules.home;

import android.app.Activity;

import androidx.test.filters.LargeTest;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.app.BaseActivityTest;
import com.challenge.us.geolocation.core.permission.location.AccessPermission;
import com.challenge.us.geolocation.core.permission.location.AccessPermissionImpl;
import com.challenge.us.geolocation.core.permission.location.AccessPermissionModule;

import org.junit.Test;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@UninstallModules({AccessPermissionModule.class, HomeModule.class})
@HiltAndroidTest
public class HomeActivityTest extends BaseActivityTest<HomeActivity> {

    @Override
    public Class<HomeActivity> getActivity() {
        return HomeActivity.class;
    }

    @Inject
    public HomeRouter homeRouter;

    @Inject
    public AccessPermission accessPermission;

    @Module
    @InstallIn(ApplicationComponent.class)
    public static class AccessPermissionModuleTest {

        @Provides
        AccessPermission permission() {
            return new PermissionTest();
        }
    }

    @Module
    @InstallIn(ApplicationComponent.class)
    public class HomeModuleTest {

        @Provides
        HomePresenter presenter() {
            return new HomePresenterImpl(homeRouter, accessPermission);
        }

        @Provides
        public HomeRouter router() {
            return new HomeRouterImpl();
        }
    }

    @Test
    public void mustToDisplayTheGoogleComponentAtHomeScreenWhenHomeScreenIsSuccessfulCreated() {
        onView(withId(R.id.activity_home_view_container))
                .check(matches(isDisplayed()));

        onView(withId(R.id.google_map_component))
                .check(matches(isDisplayed()));
    }

    @Test
    public void mustToShowErrorMessageWheUserInputANInvalidData() {
        onView(withId(R.id.text_input_geolocation))
                .perform(replaceText("invalid data"));

        onView(withId(R.id.input_action_button))
                .perform(click());

        onView(withId(R.id.input_alert_error_msg))
                .check(matches(isDisplayed()));
    }

    @Test
    public void mustToShowToastCopyClipBoardWhenClickToCopyCurrentMapTarget() {
        onView(withId(R.id.text_view_copy_map_target))
                .perform(click());

        onView(withText(R.string.copy_map_current_location))
                .check(matches(isDisplayed()));
    }

    private static class PermissionTest extends AccessPermissionImpl {
        @Override
        public boolean accessLocation(Activity activity) {
            return true;
        }

        @Override
        public boolean deviceGPSEnable(Activity activity) {
            return true;
        }
    }
}