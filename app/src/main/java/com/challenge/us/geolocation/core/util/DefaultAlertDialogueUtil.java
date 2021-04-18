package com.challenge.us.geolocation.core.util;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.core.permission.location.AccessPermission;

public class DefaultAlertDialogueUtil {

    public static void requestLocationDialogue(Activity activity, AccessPermission accessPermission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.access_location_title);
        builder.setMessage(R.string.access_device_location_msg);
        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> accessPermission.requestPermission(activity));
        builder.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void buildAlertMessageNoGps(Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.device_location_not_available_msg)
                .setCancelable(false)
                .setTitle(R.string.access_location_title)
                .setPositiveButton(android.R.string.ok, (dialog, id) -> activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton(android.R.string.cancel, (dialog, id) -> dialog.cancel());
        final androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
    }
}
