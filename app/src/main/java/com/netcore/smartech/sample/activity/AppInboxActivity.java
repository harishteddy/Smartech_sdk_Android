package com.netcore.smartech.sample.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netcore.android.smartechappinbox.SmartechAppInbox;
import com.netcore.android.smartechappinbox.network.listeners.SMTInboxCallback;
import com.netcore.android.smartechappinbox.network.model.SMTInboxMessageData;
import com.netcore.android.smartechappinbox.utility.SMTAppInboxRequestBuilder;
import com.netcore.android.smartechappinbox.utility.SMTInboxDataType;
import com.netcore.smartech.sample.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AppInboxActivity extends AppCompatActivity {

    Context mContext;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.recycler_notification)
    RecyclerView recyclerView;

    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;

    AppInboxAdapter adapter;

    private Unbinder _binder;
    SmartechAppInbox smartechAppInbox;

    private static final String TAG = "AppInboxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_inbox);
        _binder= ButterKnife.bind(this);
        this.mContext = this;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        toolbar.setTitle(getString(R.string.notification));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        smartechAppInbox = SmartechAppInbox.getInstance(new WeakReference<>(getApplicationContext()));

    }

    @Override
    protected void onResume(){
        super.onResume();
        loadNotifications();

    }

    void loadNotifications(){
        try{
                ArrayList<String> categoryList = new ArrayList<>();
               // categoryList.add("mawgif_promotion");
                categoryList.add("abc");

                SMTAppInboxRequestBuilder builder = new SMTAppInboxRequestBuilder.Builder(SMTInboxDataType.ALL)
                        .setCallback(new SMTInboxCallback() {
                            @Override
                            public void onInboxProgress() {
                            }

                            @Override
                            public void onInboxSuccess(@Nullable List<SMTInboxMessageData> list) {
                                try{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ll_nodata.setVisibility(View.GONE);
                                            recyclerView.setVisibility(View.VISIBLE);
                                            adapter=new AppInboxAdapter(list,mContext);
                                            recyclerView.setAdapter(adapter);
                                        }
                                    });
                                }
                                catch(Exception e){
                                    Log.e(TAG,e.getMessage());
                                }
                            }

                            @Override
                            public void onInboxFail() {

                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        ll_nodata.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }).setLimit(10).build();
                smartechAppInbox.getAppInboxMessages(builder);


        }
        catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
}