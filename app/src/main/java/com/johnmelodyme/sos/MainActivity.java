package com.johnmelodyme.sos;
/*
* I CREATED THIS APPLICATION FOR SECURITY PURPOSES,
* FOR NOT SPYING PURPOSES BUT RATHER LOVE ONE CAN
* SEND IMMEDIATE REAL TIME LOCATION WHEN THEY ARE
* IN DANGER. I AM NOT RESPONSIBLE FOR ANY MALICIOUS
* USE OF THIS APPLICATION.
*
* (JOHN MELODY'S OPEN SOURCE PROJECT)
* ALL RIGHT RESERVED © JOHN MELODY MELISSA COPYRIGHT
 */
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

/**
 * @AUTHOR: JOHN MELODY MELIISA
 * @PROJECT: S.O.S ANDROID
 * @INSPIRED_BY__MY_GIRLFRIEND: SIN DEE 🥰🥰🥰
 * @DESCRIPTION: CREATED FOR WOMEN SECURITY ::
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private WifiManager WIFI_MANAGER;
    private final static int REQUEST_CHECK_SETTINGS_GPS= 0b1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS= 0b10;
    private int T;
    private TextView GPS_STATUS, LONG, LA, ANIM;
    private boolean gps;
    // GPS LOCATION API:
    private LocationListener locationListener;
    private LocationManager LOCATION_MANAGER;
    private Location LOCATION;
    private GoogleApiClient GOOGLE_API_CLIENT;
    private String IP_ADDRESS;

    @SuppressLint("SetTextI18n")
    @Override
    public void onStart(){
        Log.w(TAG, "onStart():: " + "THE APPLICATION STARTED...");
        super.onStart();
        GPS_STATUS.setText(" ");
        for (T = 0b0; T < 0b11; T += 0b1) {
            CheckGPS();
        }
        Log.w(TAG, "S.O.S: " + "CHECKING GPS....");
        ANIM.setText("IP: "+ IP_ADDRESS);
    }

    private void CheckGPS() {
        LOCATION_MANAGER = (LocationManager) MainActivity.this.getSystemService(MainActivity.this.LOCATION_SERVICE);
        gps = LOCATION_MANAGER.isProviderEnabled(LOCATION_MANAGER.GPS_PROVIDER);
        if (gps == true){
            GPS_STATUS.setText("GPS IS ENABLED");
            Log.w(TAG, "S.O.S: " + "GPS IS ENABLED");
        } else {
            GPS_STATUS.setTextColor(Color.BLACK);
            GPS_STATUS.setText("GPS IS NOT ENABLED");
            Log.w(TAG, "S.O.S: " + "GPS IS NOT ENABLED");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DECLARATION ==>
        WIFI_MANAGER = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (WIFI_MANAGER != null) {
            IP_ADDRESS = Formatter.formatIpAddress(WIFI_MANAGER.getConnectionInfo().getIpAddress());
        }
        GPS_STATUS = findViewById(R.id.gps);
        LONG = findViewById(R.id.LONG);
        LA = findViewById(R.id.LAT);
        ANIM = findViewById(R.id.anim);

    }

    @SuppressLint("SetTextI18n")
    public void onLocationChanged(Location location){
        String LA, LO;
        LA = getResources().getString(R.string.Latitude);
        LO = getResources().getString(R.string.Longitude);
        //RESULT_GPS.setText(LA + location.getLatitude() + "::" + LO + location.getLongitude());
    }


    // TOAST.MAKE_TEXT METHODS():
    public void TOASTER(String toast){
        Toast.makeText(MainActivity.this, toast,
                Toast.LENGTH_SHORT)
                .show();
    }
}