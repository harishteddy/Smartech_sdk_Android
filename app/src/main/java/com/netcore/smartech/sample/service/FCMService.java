package com.netcore.smartech.sample.service;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.netcore.android.smartechpush.SmartPush;
import java.lang.ref.WeakReference;
public class FCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.v("Received Notification ",remoteMessage.getData().toString());
        boolean pushHandledBySmartech = SmartPush.getInstance(new WeakReference<>(getApplicationContext())).handlePushNotification(remoteMessage.getData().toString());
        Log.v("message ",remoteMessage.getData().toString());
        if (!pushHandledBySmartech){
            // Handle notification from other sources.
        }
    }
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.i("fcmtoken",s);
        SmartPush.getInstance(new WeakReference<>(this)).setDevicePushToken(s);

    }
}
