package com.challenge.us.geolocation.app.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.challenge.us.geolocation.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapComponent extends ConstraintLayout implements OnMapReadyCallback {

    private final static String TAG = GoogleMapComponent.class.getSimpleName();

    private GoogleMap googleMap;

    public GoogleMapComponent(@NonNull Context context) {
        super(context);
        onCreate(context);
    }

    public GoogleMapComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public GoogleMapComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);
    }

    private void onCreate(Context context) {
        inflate(context, R.layout.component_google_map, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng sydney = new LatLng(-33.852, 151.211);
        setGeolocation(sydney, "Marker in Sydney");
    }

    public void cleaMarkers() {
        googleMap.clear();
    }

    public void addMarker(MarkerOptions marker) {
        googleMap.addMarker(marker);
    }

    public void setGeolocation(LatLng latLng, String label) {
        addMarker(new MarkerOptions().position(latLng).title(label));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    public void start(FragmentManager fragmentManager) {
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
                .findFragmentById(R.id.component_google_map_fragment_container);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e(TAG, "Failed to initialize the map!");
        }
    }
}
