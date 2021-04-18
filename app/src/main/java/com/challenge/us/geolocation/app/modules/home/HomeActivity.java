package com.challenge.us.geolocation.app.modules.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.app.components.GoogleMapComponent;
import com.challenge.us.geolocation.databinding.ActivityHomeBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements HomeView {

    @Inject
    HomePresenter presenter;
    private ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());
        presenter.bindWith(this);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void setMapListener(GoogleMapComponent.MapListener listener) {
        activityHomeBinding.googleMapComponent.setListener(listener);
        activityHomeBinding.googleMapComponent.init(getSupportFragmentManager());
    }

    @Override
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    public void setMyLocationEnabled(boolean enabled) {
        activityHomeBinding.googleMapComponent.setMyLocationEnabled(enabled);
    }
}
