package com.challenge.us.geolocation.app.modules.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.app.components.GoogleMapComponent;
import com.challenge.us.geolocation.app.components.MapOptionsComponent;
import com.challenge.us.geolocation.databinding.ActivityHomeBinding;
import com.google.android.gms.maps.model.LatLng;

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public GoogleMapComponent googleMapComponent() {
        return activityHomeBinding.googleMapComponent;
    }

    @Override
    public MapOptionsComponent mapOptionsComponent() {
        return activityHomeBinding.mapOptionsComponent;
    }

}
