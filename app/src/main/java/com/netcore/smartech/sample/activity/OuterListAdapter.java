package com.netcore.smartech.sample.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.netcore.smartech.sample.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import io.hansel.hanselsdk.Hansel;

/**
 * Created by avin on 18/5/17.
 */

public class OuterListAdapter extends RecyclerView.Adapter<OuterListAdapter.OuterlistVH> {

    private Context mContext;
    private String[] items;
    private ArrayList<Integer> integerList;
    private RecyclerView mRecyclerView;

    private String[] array1 = new String[]{
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd",
            "innerlistitemOdd"
    };

    private String[] array2 = new String[]{
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven",
            "innerlistitemEven"
    };

    public OuterListAdapter(Context context, String[] items, ArrayList<Integer> integerArrayList) {
        this.items = items;
        this.mContext = context;
        integerList = integerArrayList;
    }


    @Override
    public OuterlistVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_text_item, null);
        OuterlistVH myViewHolder = new OuterlistVH(view);
        return myViewHolder;
    }

    @Override
    public final void onBindViewHolder(OuterlistVH holder, int position) {
        String hanselIndex = integerList.get(position).toString();
        holder.mTextView.setText(items[position] + "  " + hanselIndex);
        holder.mInnerListAdapter.changeList((position % 2 == 0) ? array1 : array2);
        holder.setIndex(position);
        holder.setHanselIndex(hanselIndex);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reshuffleTheView();
            }
        });
    }

    private void reshuffleTheView() {
        Collections.shuffle(integerList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.length;
    }

    public class OuterlistVH extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private RecyclerView mRecyclerView;
        private LinearLayoutManager horizontalManager;
        private InnerListAdapter mInnerListAdapter;
        private String hslIndex;

        public OuterlistVH(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textview);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.innerList);
            horizontalManager = new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(horizontalManager);
            mInnerListAdapter = new InnerListAdapter(itemView.getContext(), array1);
            mRecyclerView.setAdapter(mInnerListAdapter);
        }

        public void setHanselIndex(String hslIndex){
            this.hslIndex = hslIndex;
        }

        public void assignHanselIndex(){
            Hansel.setCustomHanselIndex(this.itemView, hslIndex);
        }

        public void setIndex(int index){
            mInnerListAdapter.setOuterIndex(index);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull @NotNull OuterlistVH holder) {
        super.onViewAttachedToWindow(holder);
        holder.assignHanselIndex();
    }
}
