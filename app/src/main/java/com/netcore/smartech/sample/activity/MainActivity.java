package com.netcore.smartech.sample.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;

import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;
import com.netcore.smartech.sample.utils.Keys;
import com.netcore.smartech.sample.utils.SharedPreferenceHelper;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.hanselsdk.Hansel;
import io.hansel.hanselsdk.HanselActionListener;
import io.hansel.ujmtracker.HanselTracker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        requestPermissions();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_fcm_token: {
                /*String fcmToken = SmartechHelper.getDevicePushToken(this);
                if (!TextUtils.isEmpty(fcmToken)) {
                    Utility.copyToClipboard(this, "FCM Token", fcmToken);
                    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                }*/


            }
            break;

            case R.id.tv_guid: {
                /*String guid = SmartechHelper.getDeviceUniqueId(this);
                if (!TextUtils.isEmpty(guid)) {
                    Utility.copyToClipboard(this, "Smartech GUID", guid);
                    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                }*/

                //Create an instance of HanselActionListener


//Register the instance with this line:

                //Create an instance of HanselActionListener


               // Hansel.registerHanselActionListener("Action2", hanselActionListener);

                HashMap<String, Object> propertiesMap = new HashMap<>();
                propertiesMap.put("hari", "anything");
                HashMap<String, Object> hanselData = HanselTracker.logEvent("login", "fbs", propertiesMap);
            }
            break;




            case R.id.tv_add_to_cart: {

                HashMap<String, Object> payload = new HashMap<>();
                payload.put("name", "T-shirt");
                payload.put("prid", 2);
                payload.put("price", 15000.00);
                payload.put("size","xl" );
                payload.put("clor","Red");

                Smartech.getInstance(new WeakReference<>(this)).trackEvent("Add To Cart", payload);
                Toast.makeText(this, R.string.tracking_add_to_cart, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.tv_checkout: {



                HanselActionListener hanselActionListener = new HanselActionListener() {
                    @Override
                    public void onActionPerformed(String action) {
                   // perform action write code here
                        Toast.makeText(getApplicationContext(), "Harish", Toast.LENGTH_SHORT).show();
                    }
                };
                Hansel.registerHanselActionListener("PhysicsWallah Testing", hanselActionListener);

               /* HashMap<String, Object> payload = new HashMap<>();
                payload.put("name", "Mobile");
                payload.put("prid", 2);
                payload.put("price", 15000.00);
                payload.put("color", "red");
                payload.put("quantity", "2");

                Smartech.getInstance(new WeakReference<>(this)).trackEvent("Checkout", payload);
                Toast.makeText(this, R.string.tracking_checkout, Toast.LENGTH_SHORT).show();*/
            }
            break;

            case R.id.tv_add_to_wish_list: {
                Toast.makeText(this, R.string.tracking_add_to_wish_list, Toast.LENGTH_SHORT).show();
            }

            break;






















            case R.id.tv_update_profile: {
                // NOTE: User profile should be set only when user identity is present in Smartech SDK.
//                String identity = SmartechHelper.getUserIdentity(this);
//                if (!TextUtils.isEmpty(identity)) {
                startActivity(new Intent(this, ProfileActivity.class));
//                } else {
//                    Toast.makeText(this, R.string.please_login_first, Toast.LENGTH_SHORT).show();
//                }
            }
            break;

            case R.id.tv_clear_identity: {
                SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, false);
                SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, null);
                Smartech.getInstance(new WeakReference<>(this)).clearUserIdentity();
                Toast.makeText(this, R.string.user_identity_cleared, Toast.LENGTH_SHORT).show();
            }
            break;

            case R.id.tv_logout: {
                SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, false);
                SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, null);
                Smartech.getInstance(new WeakReference<>(this)).logoutAndClearUserIdentity(false);

                startActivity(new Intent(this, LoginActivity.class));
                Toast.makeText(this, R.string.you_are_logged_out, Toast.LENGTH_SHORT).show();
                finish();
            }
            break;

            case R.id.tv_set_location: {

                Location location = new Location("");
                location.setLatitude(18.9985652);
                location.setLongitude(72.8259925);
                Smartech.getInstance(new WeakReference<>(this)).setUserLocation(location);

                Toast.makeText(this, R.string.default_user_location_set, Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.sw_opt_pn: {
            }
            break;

            case R.id.sw_opt_in_app: {
            }
            break;

            case R.id.sw_opt_tracking: {
            }
            break;
        }
    }

    private void init() {
        /*String fcmToken = SmartechHelper.getDevicePushToken(this);
        if (!TextUtils.isEmpty(fcmToken)) {
            TextView tvFcmToken = findViewById(R.id.tv_fcm_token);
            tvFcmToken.setText(fcmToken);
            tvFcmToken.setOnClickListener(this);
        }

        String guid = SmartechHelper.getDeviceUniqueId(this);
        if (!TextUtils.isEmpty(guid)) {
            TextView tvGuid = findViewById(R.id.tv_guid);
            tvGuid.setText(guid);
            tvGuid.setOnClickListener(this);
        }*/

        findViewById(R.id.tv_add_to_cart).setOnClickListener(this);
        findViewById(R.id.tv_checkout).setOnClickListener(this);
        findViewById(R.id.tv_add_to_wish_list).setOnClickListener(this);

        findViewById(R.id.tv_update_profile).setOnClickListener(this);
        findViewById(R.id.tv_clear_identity).setOnClickListener(this);
        findViewById(R.id.tv_set_location).setOnClickListener(this);
        findViewById(R.id.tv_logout).setOnClickListener(this);

        SwitchCompat swOptPn, swOptInApp, swOptTracking;
        swOptPn = findViewById(R.id.sw_opt_pn);
        //swOptPn.setChecked(SmartechHelper.hasOptedPushNotification(this));
        swOptPn.setOnCheckedChangeListener(this);

        swOptInApp = findViewById(R.id.sw_opt_in_app);
        //swOptInApp.setChecked(SmartechHelper.hasOptedInAppMessage(this));
        swOptInApp.setOnCheckedChangeListener(this);

        swOptTracking = findViewById(R.id.sw_opt_tracking);
        //swOptTracking.setChecked(SmartechHelper.hasOptedTracking(this));
        swOptTracking.setOnCheckedChangeListener(this);
    }

    private void requestPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.READ_PHONE_STATE
        };

        ActivityCompat.requestPermissions(this, permissions, 1);
    }
}