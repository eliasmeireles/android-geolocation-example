package com.challenge.us.geolocation.app.modules.home;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.core.permission.location.AccessPermission;
import com.challenge.us.geolocation.core.util.DefaultAlertDialogueUtil;
import com.challenge.us.geolocation.core.util.KeyBoardUtil;
import com.challenge.us.geolocation.data.model.MarkerData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.regex.Pattern;

import javax.inject.Inject;

import static com.challenge.us.geolocation.core.permission.location.AccessPermission.ACCESS_PERMISSION_FINE_LOCATION;

public class HomePresenterImpl implements HomePresenter {

    private final static String PLACE_GEOLOCATION_SEPARATOR_KEY = ":";
    private final static String LAT_LNG_SEPARATOR_KEY = ",";

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
        homeView.homeBinding().googleMapComponent.setListener(this);
        homeView.homeBinding().googleMapComponent.init(getActivity().getSupportFragmentManager());
        homeView.homeBinding().mapOptionsComponent.setDelegate(this);
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

    @Override
    public void onMarkerClick(MarkerData markerData) {
        homeRouter.openMapWith(markerData);
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        if (accessPermission.deviceGPSEnable(getActivity())) {
            homeView.homeBinding().googleMapComponent.setMyLocationEnabled(true);
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), location -> {
                        if (location != null) {
                            homeView.homeBinding().googleMapComponent.setMarkerOption(new MarkerData(getActivity().getString(R.string.device_location_label), location.getLatitude(), location.getLongitude()));
                        } else {
                            homeView.homeBinding().googleMapComponent.setMarkerOption(new MarkerData(getActivity().getString(R.string.device_location_label), 40.71527999104979, -74.01358634844325));
                        }
                    });
        } else {
            DefaultAlertDialogueUtil.buildAlertMessageNoGps(getActivity());
        }
    }

    @Override
    public void searByGeolocation(String value) {
        if (value.trim().isEmpty()) {
            return;
        }

        KeyBoardUtil.hideKeyBoard(homeView.getActivity());

        if (!value.contains(PLACE_GEOLOCATION_SEPARATOR_KEY) || !value.contains(LAT_LNG_SEPARATOR_KEY)) {
            homeView.homeBinding().mapOptionsComponent.showError(true);
        } else {
            extractInputValues(value);
        }
    }

    private void extractInputValues(String value) {
        try {
            String[] split = value.split("[,:]");
            String locationName = split[0].trim();
            double latitude = Double.parseDouble(split[1].trim());
            double longitude = Double.parseDouble(split[2].trim());

            homeView.homeBinding().googleMapComponent.setMarkerOption(new MarkerData(locationName, latitude, longitude));
            homeView.homeBinding().mapOptionsComponent.showError(false);
        } catch (Exception e) {
            e.printStackTrace();
            homeView.homeBinding().mapOptionsComponent.showError(true);
        }
    }
}
