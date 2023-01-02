package com.netcore.smartech.sample.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class ListInListActivity extends PNCBaseActivity {

    RecyclerView outerList;
    LinearLayoutManager verticalManager;
    OuterListAdapter outerListAdapter;
    private ArrayAdapter<String> mArrayAdapter;
    private String[] array1 = new String[]{
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head",
            "Head"

    };
    private ArrayList<Integer> integerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       *//* setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);*/

        HashMap<String, Object> payload = new HashMap<>();
        Smartech.getInstance(new WeakReference<>(this)).trackEvent("dynamicview", payload);

        for (int i = 1; i <=20; i++) {
            integerList.add(i);
        }

        outerList = (RecyclerView) findViewById(R.id.outerlist);
        verticalManager = new LinearLayoutManager(this);
        outerList.setLayoutManager(verticalManager);
        Collections.shuffle(integerList);
        outerListAdapter = new OuterListAdapter(this, array1, integerList);
        outerList.setAdapter(outerListAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
            actionBar.setTitle("ListInListActivity");
        }

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pnc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.fire_event) {
            PropertiesSPHandler.fireSaveEvent(ListInListActivity.this);
            return true;
        } else if (id == R.id.fire2_event) {
            PropertiesSPHandler.fireSaveEvent2(ListInListActivity.this);
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
