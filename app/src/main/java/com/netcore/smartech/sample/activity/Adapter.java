package com.netcore.smartech.sample.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netcore.android.Smartech;
import com.netcore.smartech.sample.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import io.hansel.hanselsdk.Hansel;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;

    public Adapter(List<ModelClass>userList) {
        this.userList=userList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = userList.get(position).getImageview();
        String name=userList.get(position).getTextview1();
        String msg=userList.get(position).getTextview2();
        String time=userList.get(position).getTextview3();
        String line=userList.get(position).getDivider();
        holder.setData(resource,name,msg,time,line);
        // setting item index position
        holder.saveHanselIndex(userList.get(position).getTextview1());
    }

    // creating this function attaching to this function
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.assignHanselIndex();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView textview3;
        private TextView divider;

       // creating variable for item view and Hansel Index
        private View itemView;
        private String hanselIndex;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            //here use xml ids
            //give different name not like constructor
            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textview);
            textView2=itemView.findViewById(R.id.textview2);
            textview3=itemView.findViewById(R.id.textview3);
            divider=itemView.findViewById(R.id.Divider);

        }

        // saving Hansel index

        public void saveHanselIndex(String hanselIndex){
            this.hanselIndex = hanselIndex;
        }
  // assiging Hansel index
        public void assignHanselIndex(){
           // Hansel.setHanselIndex(itemView, hanselIndex);
            Hansel.setCustomHanselIndex(itemView,hanselIndex);
        }

        public void setData(int resource, String name, String msg, String time,String line) {
            imageView.setImageResource(resource);
            textView.setText(name);
           textView2.setText(msg);
            textview3.setText(time);
            divider.setText(line);



        }
    }
}
