package com.roksky.form_renderer.viewholder;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Consumer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.roksky.form_renderer.R;


/**
 * Created by Jason Rogena - jrogena@ona.io on 11/24/17.
 */
public class GpsDialog extends Dialog implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final double MIN_ACCURACY = 30d;
    private final Activity context;
    private TextView dialogAccuracyTV, tvLocation;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private Consumer<GpsLocation> consumer;

    public GpsDialog(Activity context, Consumer<GpsLocation> consumer) {
        super(context);
        this.context = context;
        this.consumer = consumer;
        init();
    }

    protected void init() {
        this.setContentView(R.layout.dialog_gps);
        setTitle("Loading");
        this.setCancelable(false);
        this.lastLocation = null;
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                disconnectGoogleApiClient();
            }
        });
        Button okButton = (Button) this.findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndDismiss();
            }
        });
        Button cancelButton = (Button) this.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GpsDialog.this.dismiss();
            }
        });

        this.dialogAccuracyTV = (TextView) this.findViewById(R.id.accuracy);
        this.tvLocation = (TextView) this.findViewById(R.id.tv_location);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                initGoogleApiClient();
            }
        });
    }

    protected void saveAndDismiss() {
        updateLocationViews(lastLocation);
        GpsDialog.this.dismiss();
    }

    protected void initGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        googleApiClient.connect();
    }

    private void disconnectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            this.dismiss();
            ActivityCompat
                    .requestPermissions(
                            context,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            1234);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }


    private void updateLocationViews(Location location) {
        if (location != null) {
            location.getProvider();
            GpsLocation gpsLocation = new GpsLocation();
            gpsLocation.setLatitude(String.valueOf(location.getLatitude()));
            gpsLocation.setLongitude(String.valueOf(location.getLongitude()));
            gpsLocation.setAccuracy(String.valueOf(location.getAccuracy()));
            gpsLocation.setAltitude(String.valueOf(location.getAltitude()));

            consumer.accept(gpsLocation);
            if (tvLocation != null)
                tvLocation.setText("Lat : " + String.valueOf(location.getLatitude()) + " , Lon : " + String.valueOf(location.getLatitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Do nothing when the connection is suspended - This is bad and probably needs a review
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.dismiss();
        Toast.makeText(context, "Could not get your location", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            dialogAccuracyTV.setText(String.format("Accuracy", String.valueOf(location.getAccuracy()) + " m"));
        }

        lastLocation = location;
        if (lastLocation != null && lastLocation.getAccuracy() <= MIN_ACCURACY) {
            saveAndDismiss();
        }
    }
}
