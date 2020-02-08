package com.domain.aly.followingapp.homeutil;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.aly.followingapp.R;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerPostRequest;
import com.domain.aly.followingapp.accelerometerutil.AccelerometerSensor;
import com.domain.aly.followingapp.apiinterface.ApiClient;
import com.domain.aly.followingapp.apiinterface.ApiManager;
import com.domain.aly.followingapp.apputil.UserCachedInfo;
import com.domain.aly.followingapp.followersutil.FollowersFragment;
import com.domain.aly.followingapp.followingutil.FollowingFragment;
import com.domain.aly.followingapp.lightutil.LightPostRequest;
import com.domain.aly.followingapp.lightutil.LightSensor;
import com.domain.aly.followingapp.locationutil.LocationPostRequest;
import com.domain.aly.followingapp.locationutil.LocationProvider;
import com.domain.aly.followingapp.loginutil.LoginActivity;
import com.domain.aly.followingapp.noiseutil.NoisePostRequest;
import com.domain.aly.followingapp.noiseutil.NoiseSensor;
import com.domain.aly.followingapp.notificationutil.NotificationsFragment;
import com.domain.aly.followingapp.reportutil.ReportFragment;
import com.domain.aly.followingapp.speedutil.SpeedPostRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity implements AccelerometerSensor.AccelerometerSensorListener, LightSensor.LightSensorListener, LocationProvider.LocationCallback {

    private static final int REQUEST_MICROPHONE = 100;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private ImageView ivReports, ivFollowingFollowers, ivNotifications;
    private TextView tvUserName, tvUserPhone;
    private BottomNavigationView bottomNavigation;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private AccelerometerSensor accelerometerSensor;
    private LightSensor lightSensor;
    private LocationProvider mLocationProvider;
    private double lat, lng;
    private DatabaseReference followingDatabase;
    private String userName;
    private Long userPhone;
    private Retrofit retrofit;
    private ApiManager apiManager;
    private NoiseSensor noiseSensor;


    private double lightValue, accelerometerValue, soundValue, speedValue, noiseValue;
    private UserCachedInfo userCachedInfo;
    private String mapsUrl;
    private float speed;
    private Handler mHandler;
    private final static int INTERVAL = 1000 * 60 * 1; //1 minutes
    private Runnable mHandlerTask;
    private Date currentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        retrofit = ApiClient.getClient();
        apiManager = new ApiManager(this);
        userCachedInfo = new UserCachedInfo(this);
        currentTime = Calendar.getInstance().getTime();


        followingDatabase = FirebaseDatabase.getInstance().getReference("followingRequest");
        //find all textViews.
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserPhone = (TextView) findViewById(R.id.tvUserPhone);


        checkUserIsLoggedIn();
        initializeButtonNavigationView();

        //location and speed sensor
        // checkLocationPermission();
        if (checkLocationPermission()) {
            isLocationEnabled();
            mLocationProvider = new LocationProvider(getApplicationContext(), this);

        }


        checkMicPermission();


        //accelerometer sensor
        accelerometerSensor = new AccelerometerSensor(getBaseContext());
        accelerometerSensor.setListener(this);


        //light sensor
        lightSensor = new LightSensor(getBaseContext());
        lightSensor.setListener(this);


        //noise sensor
        noiseSensor = new NoiseSensor(this);

        //Post after 1 min
        startHandler();
    }

    private void isLocationEnabled() {

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(this.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(this.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(this.getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

    }

    private void checkUserIsLoggedIn() {
        if (userCachedInfo.getPhone() != 0 && !userCachedInfo.getName().isEmpty()) {

            userName = userCachedInfo.getName();
            userPhone = userCachedInfo.getPhone();
            tvUserName.setText(userName);
            tvUserPhone.setText("0" + userPhone);
        } else {
            openLoginActivity();
        }
    }

    private void initializeButtonNavigationView() {
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.navigation_home_menu);
        bottomNavigation.getMenu().findItem(R.id.navigation_report).setChecked(true);
        fragmentManager = getSupportFragmentManager();
        fragment = new ReportFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_report:
                        fragment = new ReportFragment();
                        break;
                    case R.id.navigation_followers:
                        fragment = new FollowersFragment();
                        break;
                    case R.id.navigation_following:
                        fragment = new FollowingFragment();
                        break;
                    case R.id.navigation_notifications:
                        fragment = new NotificationsFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }

    private void openLoginActivity() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                LogOut();
                return true;
            case R.id.action_add_follower:
                sendNotificationToUser();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void sendNotificationToUser() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.add_follower_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        if (!userInputDialogEditText.getText().toString().isEmpty()) {
                            String followerNumber = userInputDialogEditText.getText().toString();
                            apiManager.IsUserExistRequest(Long.valueOf(followerNumber), userName, userPhone);
                        } else {
                            Toast.makeText(HomeActivity.this, "Check Inputs!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    private void LogOut() {
        UserCachedInfo userCachedInfo = new UserCachedInfo(this);
        userCachedInfo.deleteAll();
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometerSensor.register();
        lightSensor.register();
        noiseSensor.startRecorder();
        mLocationProvider.connect();

        //startHandler();
    }

    private void startHandler() {
        mHandler = new Handler();

        mHandlerTask = new Runnable() {
            @Override
            public void run() {
                Log.e("run", "started");
                accelerometerValue = userCachedInfo.getAccelerometerSensor();
                lightValue = userCachedInfo.getLightSensor();
                speedValue = userCachedInfo.getSpeedSensor();
                noiseValue = userCachedInfo.getNoiseSensor();
                apiManager.postAccelerometerRecord(userPhone, new AccelerometerPostRequest(accelerometerValue, currentTime));
                apiManager.postLightRecord(userPhone, new LightPostRequest(lightValue, currentTime));
                apiManager.postLocationRecord(userPhone, new LocationPostRequest(mapsUrl, currentTime));
                apiManager.postSpeedRecord(userPhone, new SpeedPostRequest(speedValue, currentTime));
                apiManager.postNoiseRecord(userPhone, new NoisePostRequest(noiseValue, currentTime));
                mHandler.postDelayed(mHandlerTask, INTERVAL);
            }
        };

        startRepeatingTask();
    }

    void startRepeatingTask() {
        mHandlerTask.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onAccelerometerSensorChanged(Double x, Double y, Double z) {
        //  Toast.makeText(this, "Accelerometer " + x, Toast.LENGTH_SHORT).show();
        double accelerometerValue = ((x * x) + (y * y) + (z * z)) / Math.pow(9.8, 2);
        userCachedInfo.saveAccelerometerSensor(userCachedInfo.getAccelerometerSensor() + (float) accelerometerValue);
        userCachedInfo.saveAccelerometerSensorCount(userCachedInfo.getAccelerometerSensorCount() + 1);

    }

    @Override
    public void onLightSensorChanged(double lux) {
        //Toast.makeText(this, "light " + lux, Toast.LENGTH_SHORT).show();
        userCachedInfo.saveLightSensor(userCachedInfo.getLightSensor() + (float) lux);
        userCachedInfo.saveLightSensorCount(userCachedInfo.getLightSensorCount() + 1);

    }

    @Override
    public void handleNewLocation(Location location) {
        speed = location.getSpeed();
        lat = location.getLatitude();
        lng = location.getLongitude();
        userCachedInfo.saveSpeedSensor(userCachedInfo.getSpeedSensor() + (float) speed);
        userCachedInfo.saveSpeedSensorCount(userCachedInfo.getSpeedensorCount() + 1);
        mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HomeActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        mLocationProvider = new LocationProvider(getApplicationContext(), this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                break;
//            case SEND_SMS_PERMISSIONS_REQUEST:
//                if (grantResults.length > 1 &&
//                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getActivity(), "Read SMS permission granted", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "Read SMS permission denied", Toast.LENGTH_SHORT).show();
//                }
//                break;
        }


    }

    private void checkMicPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_MICROPHONE);

        } else {


        }
    }


    //Add follower

}
