package com.netcore.smartech.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;
import com.netcore.smartech.sample.receiver.BroadcastReceiver;
import com.netcore.smartech.sample.utils.Keys;
import com.netcore.smartech.sample.utils.SharedPreferenceHelper;
import java.lang.ref.WeakReference;

import io.hansel.hanselsdk.Hansel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isUserLoggedIn = SharedPreferenceHelper.getBoolean(this, Keys.IS_USER_LOGGED_IN, false);
        String loggedInUserIdentity = SharedPreferenceHelper.getString(this, Keys.LOGGED_IN_USER_IDENTITY, null);
        if (isUserLoggedIn && !TextUtils.isEmpty(loggedInUserIdentity)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        Hansel.pairTestDevice(getIntent().getDataString());
        new BroadcastReceiver().onReceive(this, getIntent());
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update_identity: {
                String identity = edIdentity.getText().toString();
                if (!TextUtils.isEmpty(identity)) {
                    SharedPreferenceHelper.putBoolean(this, Keys.IS_USER_LOGGED_IN, true);
                    SharedPreferenceHelper.putString(this, Keys.LOGGED_IN_USER_IDENTITY, identity);
                    Smartech.getInstance(new WeakReference<>(this)).login(identity);
                    Smartech.getInstance(new WeakReference<>(this)).setUserIdentity(identity);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();


                } else {
                    Toast.makeText(this, R.string.enter_email_id, Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.btn_register: {
                startActivity(new Intent(this, RegisterActivity.class));
            }
            break;

            case R.id.btn_guest_login: {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            break;
        }
    }

    private void init() {
        edIdentity = findViewById(R.id.et_identity);
        findViewById(R.id.btn_update_identity).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.btn_guest_login).setOnClickListener(this);
    }
}