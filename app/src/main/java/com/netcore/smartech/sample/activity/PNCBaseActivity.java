package com.netcore.smartech.sample.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Akanksha on 2019-09-20
 */
public abstract class PNCBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            //HanselTracker.logEvent("hsl_log_pnc", "mxp", null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
