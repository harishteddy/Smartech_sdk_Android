package com.netcore.smartech.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;
import com.netcore.smartech.sample.utils.Utility;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.hanselsdk.Hansel;
import io.hansel.hanselsdk.HanselActionListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etIdentity, etFirstName, etLastName, etPhone, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


//Create an instance of HanselActionListener
        HanselActionListener hanselActionListener = new HanselActionListener() {
            @Override
            public void onActionPerformed(String action) {
                //Perform task based on action
            }
        };

//Register the instance with this line:
        Hansel.registerHanselActionListener("action Name", hanselActionListener);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear: {
                EditText[] inputs = {etIdentity, etFirstName, etLastName, etPhone, etCity};
                Utility.clearInputs(inputs);
            }
            break;

            case R.id.btn_register: {
                EditText[] inputs = {etIdentity, etFirstName, etLastName, etPhone, etCity};
                if (!Utility.areInputsEmpty(inputs)) {
                    Toast.makeText(this, R.string.registration_successful, Toast.LENGTH_SHORT).show();

                    //Track user identity like email or moblie or user id
                    Smartech.getInstance(new WeakReference<>(this)).login(etIdentity.getText().toString());
                    Smartech.getInstance(new WeakReference<>(this)).setUserIdentity(etIdentity.getText().toString());


                    HashMap<String, Object> payload = new HashMap<>();
                    payload.put("MOBILE",etPhone.getText().toString());
                    payload.put("FIRSTNAME",etFirstName.getText().toString());
                    payload.put("LASTNAME", etLastName.getText().toString());
                    payload.put("EMAIL", etIdentity.getText().toString());
                    payload.put("CITY", etCity.getText().toString());

                    Smartech.getInstance(new WeakReference<>(this)).updateUserProfile(payload);


                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, R.string.all_fields_are_required, Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private void init() {
        etIdentity = findViewById(R.id.et_identity);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone = findViewById(R.id.et_phone);
        etCity = findViewById(R.id.et_city);

        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }
}