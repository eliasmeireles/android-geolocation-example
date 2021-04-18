package com.challenge.us.geolocation.app.modules.home;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.challenge.us.geolocation.data.model.MarkerData;

public class HomeRouterImpl implements HomeRouter {

    private HomeView homeView;

    @Override
    public void bindWith(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void openMapWith(MarkerData data) {
        StringBuilder addressUrl = new StringBuilder("http://maps.google.com/maps?q=")
                .append(data.getLat())
                .append(",")
                .append(data.getLng())
                .append("(")
                .append(data.getLocationName())
                .append(")&iwloc=A&hl=es");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(addressUrl.toString()));
        Log.i(getClass().getSimpleName(), addressUrl.toString());
        homeView.getActivity().startActivity(intent);
    }
}