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
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.LocationListener;

/**
 * @AUTHOR: JOHN MELODY MELIISA
 * @PROJECT: S.O.S ANDROID
 * @INSPIRED_BY__MY_GIRLFRIEND: SIN DEE 🥰🥰🥰
 * @DESCRIPTION: CREATED FOR WOMEN SECURITY ::
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private int g;
    private LocationManager LOCATION_MANAGER;
    private TextView GPS_STATUS, LAST_UPDATE, RESULT_GPS;
    private Button START_LOCATION_UPDATES, STOP_LOCATION_UPDATES, GET_LAST_UPDATE;
    private boolean gps;
    // GPS LOCATION API:
    private LocationListener locationListener;

    @Override
    public void onStart(){
        Log.w(TAG, "onStart():: " + "THE APPLICATION STARTED...");
        super.onStart();
        GPS_STATUS.setText(" ");
        for (g = 0b0; g < 0b11; g += 0b1) {
            CheckGPS();
        }
        Log.w(TAG, "S.O.S: " + "CHECKING GPS....");
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
        // DECLARATION:
        GPS_STATUS = findViewById(R.id.gps);
        LAST_UPDATE = findViewById(R.id.lasttupdate);
        START_LOCATION_UPDATES = findViewById(R.id.start);
        STOP_LOCATION_UPDATES = findViewById(R.id.stop);
        RESULT_GPS = findViewById(R.id.result);

        LOCATION_MANAGER = (LocationManager)  getSystemService(Context.LOCATION_SERVICE);


        START_LOCATION_UPDATES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void onLocationChanged(Location location){
        String LA, LO;
        LA = getResources().getString(R.string.Latitude);
        LO = getResources().getString(R.string.Longitude);
        RESULT_GPS.setText(LA + location.getLatitude() + "::" + LO + location.getLongitude());
    }


    // TOAST.MAKE_TEXT METHODS():
    public void TOASTER(String toast){
        Toast.makeText(MainActivity.this, toast,
                Toast.LENGTH_SHORT)
                .show();
    }
}