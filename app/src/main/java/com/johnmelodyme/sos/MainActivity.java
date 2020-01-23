package com.johnmelodyme.sos;

import androidx.annotation.BinderThread;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import java.text.DateFormat;
import java.util.Date;


/**
 * @AUTHOR: JOHN MELODY MELIISA
 * @PROJECT: S.O.S ANDROID
 * @INSPIRED_BY__MY_GIRLFRIEND
 * @DESCRIPTION: CREATED FOR WOMEN SECURITY ::
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 0b10011100010000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 0b1001110001000;
    private static final int REQUEST_CHECK_SETTINGS = 0b1100100;
    private String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private LocationManager LOCATION_MANAGER;
    private TextView GPS_STATUS;
    private Button START_LOCATION_UPDATES;
    private Boolean RequestingLocationUpdates;
    private boolean gps;
    private String LastUpdateTime;
    // GPS LOCATION API:
    private FusedLocationProviderClient fusedLocationProviderClient;
    private SettingsClient settingsClient;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;
    private LocationCallback locationCallback;
    private Location currentLocation;

    @Override
    public void onStart(){
        Log.w(TAG, "onStart():: " + "THE APPLICATION STARTED...");
        super.onStart();
        GPS_STATUS.setText(" ");
        for (int g = 0; g < 3; g++) {
            CheckGPS();
        }
    }

    private void CheckGPS() {
        LOCATION_MANAGER = (LocationManager) MainActivity.this.getSystemService(MainActivity.this.LOCATION_SERVICE);
        gps = LOCATION_MANAGER.isProviderEnabled(LOCATION_MANAGER.GPS_PROVIDER);
        if (gps == true){
            GPS_STATUS.setText("GPS IS ENABLED");
            Log.w(TAG, "GPS: " + "GPS IS ENABLED");
        } else {
            GPS_STATUS.setTextColor(Color.BLACK);
            GPS_STATUS.setText("GPS IS NOT ENABLED");
            Log.w(TAG, "GPS: " + "GPS IS NOT ENABLED");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GPS_STATUS = findViewById(R.id.gps);
        START_LOCATION_UPDATES = findViewById(R.id.start);

        Context context;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        settingsClient = LocationServices.getSettingsClient(MainActivity.this);
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // WHEN LOCATION IS RECEIVE ::
                currentLocation = locationResult.getLastLocation();
                LastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            }
        };
    }
}
