package com.netcore.smartech.sample.activity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.hanselsdk.Hansel;
import io.hansel.hanselsdk.HanselActionListener;
import io.hansel.hanselsdk.HanselDeepLinkListener;

public class IgnoreViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignore_view);


        // layout1 and all its child will be ignored during screen capture on console.hansel.io
        findViewById(R.id.layout1).setTag(R.id.hansel_ignore_view, true);

        // Only layout2 will be ignored during screen capture on console.hansel.io
        // while its child will be captured and nudges can be placed over them
        findViewById(R.id.layout2).setTag(R.id.hansel_ignore_view_excluding_children, true);

        HashMap<String, Object> payload = new HashMap<>();
        Smartech.getInstance(new WeakReference<>(this)).trackEvent("invisiblecontainer", payload);




        HanselActionListener hanselActionListener = new HanselActionListener() {
            @Override
            public void onActionPerformed(String action) {
                // perform action write code here
                Toast.makeText(getApplicationContext(), "Harish", Toast.LENGTH_SHORT).show();
            }
        };
        Hansel.registerHanselActionListener("Nudge Action", hanselActionListener);


        Hansel.registerHanselDeeplinkListener(new HanselDeepLinkListener() {
            @Override
            public void onLaunchUrl(String url) {
                // Perform task based on url
                // this is the code for redirecting to browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                browserIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);

            }
        });


    }

}
