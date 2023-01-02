package com.netcore.smartech.sample.receiver;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.netcore.android.SMTBundleKeys;
import com.netcore.smartech.sample.activity.ProfileActivity;
import com.netcore.smartech.sample.activity.RegisterActivity;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundleExtra = intent.getExtras();
        if (bundleExtra != null) {
            if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK)) {
                String deepLinkvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK);
                if(deepLinkvalue!=null&& !deepLinkvalue.equals("")) {
                    Log.v("smartech_broadcast", "deeplink:" + deepLinkvalue);
                    if (deepLinkvalue.startsWith("sampleapp://profile")) {
                        openprofile(context);
                    } else if (deepLinkvalue.startsWith("https://sampleapp.com/login")) {
                        openprofile(context);
                    } else if (deepLinkvalue.startsWith("https://sampleapp.com/register")) {
                        openregister(context);
                    } else {


                   // this is the code for redirecting to browser
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkvalue));
                    browserIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                    }
                }
            } else {
                Log.v("smartech_broadcast", "does not have deeplink path.");

            }

            if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD)) {
                String customPayloadvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD);
                Log.v("smartech_broadcast","custompayload:"+customPayloadvalue);



            } else {
                Log.v("smartech_broadcast", "does not have custom payload.");
            }
        }

    }

    private void openregister(Context context) {
        Intent intent1= new Intent( context, RegisterActivity.class);
        intent1.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }

    private void openprofile(Context context) {
        Intent intent1= new Intent( context,ProfileActivity.class);
        intent1.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }
}
