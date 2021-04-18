package com.challenge.us.geolocation.app.modules.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.app.components.GoogleMapComponent;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements HomeView {

    private GoogleMapComponent mapComponent;

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findView();
        presenter.bindWith(this);
    }

    private void findView() {
        mapComponent = findViewById(R.id.activity_home_google_map_component);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void setMapListener(GoogleMapComponent.MapListener listener) {
        mapComponent.setListener(listener);
        mapComponent.init(getSupportFragmentManager());
    }

    @Override
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    public void setMyLocationEnabled(boolean enabled) {
        mapComponent.setMyLocationEnabled(enabled);
    }
}
