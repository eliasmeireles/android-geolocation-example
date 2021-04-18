package com.challenge.us.geolocation.app.modules.home;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;
import com.challenge.us.geolocation.app.components.MapOptionsComponent;
import com.challenge.us.geolocation.core.permission.location.AccessPermission;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterImplTest {

    public HomeViewImplTest homeView = spy(new HomeViewImplTest());

    public HomePresenterImpl homePresenter;

    @Mock
    public AppCompatActivity activity;

    @Mock
    public AccessPermission accessPermission;

    @Mock
    public HomeRouter homeRouter;

    @Mock
    public GoogleMapComponent googleMapComponent;

    @Mock
    public MapOptionsComponent mapOptionsComponent;

    @Before
    public void tearsUp() {
        homePresenter = spy(new HomePresenterImpl(homeRouter, accessPermission));
    }

    @Test
    public void mustToCallAlertErrorMethodOfMapOptionsComponentWithTrueValueWhenInputtedDataIsNotValid() {
        homePresenter.bindWith(homeView);
        homePresenter.searByGeolocation("invalid value");
        verify(homeView.mapOptionsComponent()).showError(true);
    }

    @Test
    public void mustToCallAlertErrorMethodOfMapOptionsComponentWithFalseValueWhenInputtedDataIsNotValid() {
        homePresenter.bindWith(homeView);
        homePresenter.searByGeolocation("Valid value: -45, 586");
        verify(homeView.mapOptionsComponent()).showError(false);
    }

    private class HomeViewImplTest implements HomeView {

        @Override
        public AppCompatActivity getActivity() {
            return activity;
        }

        @Override
        public GoogleMapComponent googleMapComponent() {
            return googleMapComponent;
        }

        @Override
        public MapOptionsComponent mapOptionsComponent() {
            return mapOptionsComponent;
        }
    }
}