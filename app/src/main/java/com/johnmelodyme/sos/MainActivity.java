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
import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @AUTHOR: JOHN MELODY MELIISA
 * @PROJECT: S.O.S ANDROID
 * @INSPIRED_BY__MY_GIRLFRIEND: SIN DEE 🥰🥰🥰
 * @DESCRIPTION: CREATED FOR WOMEN SECURITY ::
 */
public class MainActivity extends AppCompatActivity {
    // BUTTERKNIFE::
    @BindView(R.id.result)
    TextView THE_LOCATION_RESULT;

    @BindView(R.id.lasttupdate)
    TextView THE_LAST_UPDATE;

    @BindView(R.id.start)
    Button START_LOCATION;

    @BindView(R.id.stop)
    Button STOP_LOCATION;

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
        System.out.println("WELCOME TO SOS");
        Log.w(TAG, "onStart():: " + "THE APPLICATION STARTED...");
        super.onStart();
        GPS_STATUS.setText(" ");
        for (int g = 0; g < 0b11; g++) {
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
        ButterKnife.bind(MainActivity.this);
        RESTORE_VALUES_FROM_BUNDLE(savedInstanceState);
        GPS_STATUS = findViewById(R.id.gps);
        START_LOCATION_UPDATES = findViewById(R.id.start);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        settingsClient = LocationServices.getSettingsClient(MainActivity.this);
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // WHEN LOCATION IS RECEIVE ::
                currentLocation = locationResult.getLastLocation();
                LastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                
                UPDATE_LOCATION_UI();
            }
        };

        RequestingLocationUpdates = false;
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();
    }

    // UPDATE THE USER INTERFACE THE LOCATION DATA AND TOGGLING THE BUTTONS:
    @SuppressLint("SetTextI18n")
    private void UPDATE_LOCATION_UI() {
        if(currentLocation != null){
            String LA, LO, TAGGER;
            LA = getResources().getString(R.string.Latitude);
            LO = getResources().getString(R.string.Longitude);
            TAGGER = getResources().getString(R.string.last_update);
            THE_LOCATION_RESULT.setText(LA + currentLocation.getLatitude() + LO + currentLocation.getLongitude());
            // GIVING A ```BLINK``` EFFECT ON TEXTVIEW:
            THE_LOCATION_RESULT.setAlpha(0);
            THE_LOCATION_RESULT.animate().alpha(1).setDuration(300);
            // LAST UPDATED TIME :
            THE_LAST_UPDATE.setText(TAGGER + " " + THE_LAST_UPDATE);
        }
        TOGGLE_BUTTON();
    }

    private void TOGGLE_BUTTON() {
        if (RequestingLocationUpdates){
            START_LOCATION.setEnabled(false);
            STOP_LOCATION.setEnabled(true);
        } else {
            START_LOCATION.setEnabled(true);
            STOP_LOCATION.setEnabled(false);
        }
    }

    // RESTORING VALUES FROM SAVED INSTANCE STATE:
    private void RESTORE_VALUES_FROM_BUNDLE(Bundle savedInstanceState){
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey("is_requesting_updates")){
                RequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }
            if (savedInstanceState.containsKey("last_known_location")){
                currentLocation = savedInstanceState.getParcelable("last_known_location");
            }
            if (savedInstanceState.containsKey("last_updated_on")) {
                LastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }
    }
}
