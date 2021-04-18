package com.challenge.us.geolocation.app.modules.home;

import androidx.test.filters.LargeTest;

import com.challenge.us.geolocation.app.BaseActivityTest;

import org.junit.Test;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;

import static junit.framework.TestCase.assertTrue;

@LargeTest
@UninstallModules(HomeModule.class)
@HiltAndroidTest
public class HomeActivityDependenciesInjectionTest extends BaseActivityTest<HomeActivity> {

    private final HomePresenterFake presenter = new HomePresenterFake();

    @Override
    public Class<HomeActivity> getActivity() {
        return HomeActivity.class;
    }

    @Module
    @InstallIn(ApplicationComponent.class)
    public class HomeModuleTest {

        @Provides
        HomePresenter presenter() {
            return presenter;
        }
    }

    @Test
    public void mustToInjectHomePresenterAsAnInstanceOfHomePresenterFake() {
        assertTrue(activityTestRule.getActivity().presenter instanceof HomePresenterFake);
    }
}