package com.johnmelodyme.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.kongzue.dialog.v2.DialogSettings;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;

/**
 * @AUTHOR: JOHN MELODY MELIISA
 * @PROJECT: S.O.S ANDROID
 * @INSPIRED_BY_GIRLFRIEND
 * @DESCRIPTION: CREATED FOR WOMEN SECURITY ::
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private LocationManager LOCATION_MANAGER;
    private TextView GPS_STATUS;
    boolean gps;

    @Override
    public void onStart(){
        super.onStart();
        GPS_STATUS.setText(" ");
        for (int g = 0; g < 5; g++) {
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

    }
}
