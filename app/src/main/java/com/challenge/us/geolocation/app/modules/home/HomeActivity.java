package com.challenge.us.geolocation.app.modules.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.app.components.GoogleMapComponent;

public class HomeActivity extends AppCompatActivity {

    private GoogleMapComponent mapComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startGoogleMapComponent();
    }

    private void startGoogleMapComponent() {
        mapComponent = findViewById(R.id.activity_home_google_map_component);
        mapComponent.start(getSupportFragmentManager());
    }
}
