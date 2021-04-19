package com.challenge.us.geolocation.app.modules.home;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;
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
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.challenge.us.geolocation.core.permission.location.AccessPermission.ACCESS_PERMISSION_FINE_LOCATION;
import static org.hamcrest.Matchers.not;

@LargeTest
@UninstallModules({AccessPermissionModule.class, HomeModule.class})
@HiltAndroidTest
public class HomeActivityPermissionsAlertDialogueTest extends BaseActivityTest<HomeActivity> {

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
    public void mustToRequestPermissionAlertDialogueWhenPermissionIsNotGranted() {
        onView(withText(R.string.access_device_location_msg))
                .check(matches(isDisplayed()));
    }

    @Test
    public void mustToShowAlertDialogueOfLocationOffWhenDeviceLocationIsNotAvailable() {
        onView(isRoot()).perform(ViewActions.pressBack());
        String[] permissions = {};
        int[] getResults = {PackageManager.PERMISSION_GRANTED};

        HomeActivity activity = activityTestRule.getActivity();
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> activity.onRequestPermissionsResult(ACCESS_PERMISSION_FINE_LOCATION, permissions, getResults), 50);
        SystemClock.sleep(102);
        onView(withText(R.string.device_location_not_available_msg))
                .check(matches(isDisplayed()));

    }


    private static class PermissionTest extends AccessPermissionImpl {
        @Override
        public boolean accessLocation(Activity activity) {
            return false;
        }

        @Override
        public boolean deviceGPSEnable(Activity activity) {
            return false;
        }
    }
}