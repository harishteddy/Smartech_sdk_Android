package com.netcore.smartech.sample.activity;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.netcore.android.smartechappinbox.network.model.SMTInboxMessageData;
import com.netcore.smartech.sample.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class AppInboxAdapter extends RecyclerView.Adapter<AppInboxAdapter.ViewHolder> {

    private static final String TAG = "AppInboxAdapter";
    private List<SMTInboxMessageData> list;
    Context mcontext;

    public AppInboxAdapter(List<SMTInboxMessageData> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ac_details_app_inbox_list_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                try{
                    int position=mViewHolder.getAdapterPosition();

                    try{
                        if(vd.intentvalues!=null)
                            vd.intentvalues.setTab_position(0);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    Intent in=new Intent(mcontext, ActivityDetails.class);
                    in.putExtra("LOGTYPE",data_filtered.get(position).getlog_main_type());
                    in.putExtra("LOGSUBTYPE",data_filtered.get(position).getlog_sub_type());
                    in.putExtra("LOGID",data_filtered.get(position).getlog_id());
                    mcontext.startActivity(in);
                }
                catch(Exception e){
                    e.printStackTrace();
                }*/
            }
        });

        return  mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try{
            holder.textViews.get(0).setText(list.get(position).getSmtPayload().getTitle());


            Date timeD = new Date(list.get(position).getSmtPayload().getTimestamp() * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            String Time = sdf.format(timeD);

            holder.textViews.get(1).setText(Time);

            holder.textViews.get(2).setText(list.get(position).getSmtPayload().getBody());

            Glide.with(mcontext).load(list.get(position).getSmtPayload().getMediaUrl()).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.imgNotification.setBackground(resource);
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        }
        catch(Exception e){
            Log.e(TAG,e.getMessage());
        }

    }

    @Override
    public int getItemCount() {

        if(list != null){
            return list.size();
        } else {
            return 0;
        }
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }


    public void addItem(List<SMTInboxMessageData> list, int index) {
        list.addAll(list);
        notifyItemInserted(index);
    }



    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        //   textviews (0)  - Title
        //   textviews (1)  - Date Time
        //   textviews (2)  - Message

        @BindViews({R.id.notification_title, R.id.notification_date_time,
                R.id.notification_message})
        List<TextView> textViews;
        @BindView(R.id.img_notificationImage)
        ImageView imgNotification;


        public ViewHolder(View base) {
            super(base);
            ButterKnife.bind(this, base);
        }
    }
}

