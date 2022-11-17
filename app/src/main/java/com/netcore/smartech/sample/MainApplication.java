package com.netcore.smartech.sample;

import static android.content.ContentValues.TAG;


import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.firebase.messaging.FirebaseMessaging;
import com.netcore.android.Smartech;
import java.lang.ref.WeakReference;

import com.netcore.android.inapp.InAppCustomHTMLListener;
import com.netcore.android.logger.SMTDebugLevel;
import com.netcore.android.smartechpush.SmartPush;
import com.netcore.android.smartechpush.notification.SMTNotificationOptions;
import com.netcore.smartech.sample.receiver.BroadcastReceiver;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import io.hansel.core.logger.HSLLogLevel;

// we need to add depandancy app level gradle then it will give application class inside the application class oncreate()
//deep link created and notification addedd and fcm token will generated

public class MainApplication extends android.app.Application implements InAppCustomHTMLListener {
    private static final String TAG = "smartech";
    @Override
    public void onCreate() {
        super.onCreate();
        //initialise the sdk
      Smartech smartech=  Smartech.getInstance(new WeakReference<>(this));
      smartech.initializeSdk(this);
      smartech.setInAppCustomHTMLListener(this);
      smartech.trackAppInstallUpdateBySmartech();//Tracking app install/update event.
      smartech.setDebugLevel(SMTDebugLevel.Level.VERBOSE);//Enabling Smartech sdk logs for testing.




        // fetch exist token from firebase app

        try {
            SmartPush smartPush = SmartPush.getInstance(new WeakReference<>(this));
            smartPush.fetchAlreadyGeneratedTokenFromFCM();
        } catch (Exception e) {

        }

        // enabling logs for nudges prodcut
        HSLLogLevel.all.setEnabled(true);
        HSLLogLevel.mid.setEnabled(true);
        HSLLogLevel.debug.setEnabled(true);

        /**
         * This method will register the broadcast receiver for foreground push notification and deep link clicks.
         **/
        //deeplink
        BroadcastReceiver deeplinkReceiver = new BroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.smartech.EVENT_PN_INBOX_CLICK");
        registerReceiver(deeplinkReceiver, filter);


        /**
         * This method will set the push notification icon to Smartech SDK.
         *
         * @param resId - Resource Id of the drawable to be set as notification icon.
         **/

       /* //notification icon
        SMTNotificationOptions options = new SMTNotificationOptions(this);
        options.setBrandLogo("logo"); //e.g.logo is sample name for brand logo
        options.setLargeIcon("icon_nofification");//e.g.ic_notification is sample name for large icon
        options.setSmallIcon("ic_action_play"); //e.g.ic_action_play is sample name for icon
        options.setSmallIconTransparent("ic_action_play"); //e.g.ic_action_play is sample name for transparent small icon
        options.setTransparentIconBgColor("#FF0000");
        options.setPlaceHolderIcon("ic_notification");//e.g.ic_notification is sample name for placeholder icon
        SmartPush.getInstance(new WeakReference(this)).setNotificationOptions(options);
*/
        // fetchFCMToken(this);



    }

    /**
     *
     *
     * This will fetch the FCM token and set to SDK if token present in SDK is not valid.
     **/
    //here fcm token will generated
    private static void fetchFCMToken(Context context) {
        try {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && !TextUtils.isEmpty(task.getResult())) {
                            /**
                             * This method will return the FCM token saved locally in the Smartech SDK.
                             **/
                            SmartPush smartech = SmartPush.getInstance(new WeakReference<>(context));
                            String fcmToken = task.getResult();
                            String currentToken = smartech.getDevicePushToken();

                            Log.i("TOKEN", "FCM Instance Id Token: " + fcmToken);
                            Log.i("TOKEN", "Current FCM Token: " + currentToken);

                            // This method will set the FCM token to the Smartech SDK.

                            if (TextUtils.isEmpty(currentToken)) {
                                smartech.setDevicePushToken(fcmToken);
                                Log.i("TOKEN", "New token set: " + fcmToken);
                            } else if (!currentToken.equals(fcmToken)) {
                                smartech.setDevicePushToken(fcmToken);
                                Log.i("TOKEN", "New token set: " + fcmToken);
                            } else {
                                Log.i("TOKEN", "Both tokens are same.");
                            }
                        } else {
                            Log.e("TOKEN", "Fetch FCM token failed: Task unsuccessful.");
                        }
                    });
        } catch (Exception e) {
            Log.e("TOKEN", "Fetch FCM token failed: " + e.getMessage());
        }
    }

    @Override
    public void customHTMLCallback(@Nullable HashMap<String, Object> hashMap) {
        Log.i("InAPP", hashMap.toString());
    }
}
