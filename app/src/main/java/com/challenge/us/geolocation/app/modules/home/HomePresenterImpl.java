package com.challenge.us.geolocation.app.modules.home;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.core.permission.location.AccessPermission;
import com.challenge.us.geolocation.core.util.DefaultAlertDialogueUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import static com.challenge.us.geolocation.core.permission.location.AccessPermission.ACCESS_PERMISSION_FINE_LOCATION;

public class HomePresenterImpl implements HomePresenter {

    private final HomeRouter homeRouter;
    private final AccessPermission accessPermission;

    private HomeView homeView;

    @Inject
    public HomePresenterImpl(HomeRouter homeRouter, AccessPermission accessPermission) {
        this.homeRouter = homeRouter;
        this.accessPermission = accessPermission;
    }

    @Override
    public void bindWith(HomeView homeView) {
        this.homeView = homeView;
        homeRouter.bindWith(homeView);
        homeView.getMapComponent().setListener(this);
        homeView.getMapComponent().init(getActivity().getSupportFragmentManager());
    }

    private AppCompatActivity getActivity() {
        return homeView.getActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_PERMISSION_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (accessPermission.deviceGPSEnable(getActivity())) {
                    getDeviceLocation();
                } else {
                    DefaultAlertDialogueUtil.buildAlertMessageNoGps(getActivity());
                }
            }
        }
    }


    @Override
    public void deviceLocation(LatLng latLng) {
        System.out.println(latLng.toString());
    }

    @Override
    public void mapIsReady() {
        if (accessPermission.accessLocation(getActivity())) {
            getDeviceLocation();
        } else {
            DefaultAlertDialogueUtil.requestLocationDialogue(getActivity(), accessPermission);
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        if (accessPermission.deviceGPSEnable(getActivity())) {
            homeView.getMapComponent().setMyLocationEnabled(true);
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), location -> {
                        if (location != null) {
                            homeView.getMapComponent()
                                    .setMarkerOption(new LatLng(location.getLatitude(), location.getLongitude()),
                                            getActivity().getString(R.string.device_location_label));
                        } else {
                            homeView.getMapComponent()
                                    .setMarkerOption(new LatLng(-19.9243, -43.9351),
                                            getActivity().getString(R.string.device_location_label));
                        }
                    });
        } else {
            DefaultAlertDialogueUtil.buildAlertMessageNoGps(getActivity());
        }
    }
}
