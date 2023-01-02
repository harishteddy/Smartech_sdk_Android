package com.netcore.smartech.sample.activity;

import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dynamicview extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.netcore.smartech.sample.R.layout.dynamicview);


        HashMap<String, Object> payload = new HashMap<>();
        Smartech.getInstance(new WeakReference<>(this)).trackEvent("dynamicview", payload);
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mrecyclerView=findViewById(com.netcore.smartech.sample.R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(userList);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }
    private void initData() {
        userList = new ArrayList<>();

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Anjali","How are you?","10:45 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.bo,"Brijesh","I am fine","15:08 pm","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.boy,"Sam","You Know?","1:02 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Divya","How are you?","12:55 pm","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Simran","This is Easy","13:50 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.boy,"Karan","I am Don","1:08 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.bo,"Sameer","You Know this?","4:02 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Baby","How ?","11:55 pm","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Anjali","How are you?","10:45 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.bo,"Brijesh","I am fine","15:08 pm","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.boy,"Sam","You Know?","1:02 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Divya","How are you?","12:55 pm","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Simran","This is Easy","13:50 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.boy,"Karan","I am Don","1:08 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.bo,"Sameer","You Know this?","4:02 am","_______________________________________"));

        userList.add(new ModelClass(com.netcore.smartech.sample.R.drawable.gi,"Baby","How ?","11:55 pm","_______________________________________"));




    }


}
