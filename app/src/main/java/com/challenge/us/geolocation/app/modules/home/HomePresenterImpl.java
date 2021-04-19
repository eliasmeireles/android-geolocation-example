package com.challenge.us.geolocation.app.modules.home;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

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

    private final static int TUTORIAL_CURRENT_VERSION = 1;
    private final static String TUTORIAL_VERSION_KEY = "tutorialVersionKey";
    private final static String TUTORIAL_SHARED_PREFS = "tutorialSharedPrefs";
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
        homeView.googleMapComponent().setListener(this);
        homeView.googleMapComponent().init(getActivity().getSupportFragmentManager());
        homeView.mapOptionsComponent().setDelegate(this);
        displayTutorial();
    }

    private void displayTutorial() {
        SharedPreferences preferences = getActivity().getSharedPreferences(TUTORIAL_SHARED_PREFS, Context.MODE_PRIVATE);
        if (!preferences.contains(TUTORIAL_VERSION_KEY) ||
                (preferences.getInt(TUTORIAL_VERSION_KEY, 0)) != TUTORIAL_CURRENT_VERSION) {
            homeView.homeTutorialComponent().setVisibility(View.VISIBLE);
            preferences.edit()
                    .putInt(TUTORIAL_VERSION_KEY, TUTORIAL_CURRENT_VERSION)
                    .apply();
        }
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
    public void clipCurrentGeolocation() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        LatLng currentLatLan = homeView.googleMapComponent().getCurrentLatLan();
        String clipValue = currentLatLan.latitude + ", " + currentLatLan.longitude;
        ClipData clip = ClipData.newPlainText("Map target", clipValue);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(getActivity(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
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
            homeView.googleMapComponent().setMyLocationEnabled(true);
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), location -> {
                        if (location != null) {
                            homeView.googleMapComponent().setMarkerOption(new MarkerData(getActivity().getString(R.string.device_location_label), location.getLatitude(), location.getLongitude()));
                        } else {
                            homeView.googleMapComponent().setMarkerOption(new MarkerData(getActivity().getString(R.string.device_location_label), 40.71527999104979, -74.01358634844325));
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
            homeView.mapOptionsComponent().showError(true);
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

            homeView.googleMapComponent().setMarkerOption(new MarkerData(locationName, latitude, longitude));
            homeView.mapOptionsComponent().showError(false);
        } catch (Exception e) {
            e.printStackTrace();
            homeView.mapOptionsComponent().showError(true);
        }
    }
}
