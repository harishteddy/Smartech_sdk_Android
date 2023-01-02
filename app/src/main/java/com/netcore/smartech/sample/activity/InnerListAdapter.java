package com.netcore.smartech.sample.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.netcore.smartech.sample.R;


/**
 * Created by avin on 18/5/17.
 */

public class InnerListAdapter extends RecyclerView.Adapter<InnerListAdapter.InnerListVH> {

    private Context mContext;
    private String[] items;
    private int outerIndex = -1;
//    private RecyclerView mRecyclerView;

    public InnerListAdapter(Context context, String[] items) {
        this.items = items;
        this.mContext = context;
    }

    public void setOuterIndex(int outerIndex) {
        this.outerIndex = outerIndex;
    }

    @Override
    public InnerListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.inner_list_item, null);
        InnerListVH myViewHolder = new InnerListVH(view);
        return myViewHolder;
    }

    @Override
    public final void onBindViewHolder(InnerListVH holder, int position) {
        holder.mTextView.setText(items[position]+"  "+position);
        holder.setIndex(position);
    }

    public void changeList(String[] items) {
        this.items = items;
        notifyDataSetChanged();
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        mRecyclerView = recyclerView;
//    }
//
//    @Override
//    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
//        super.onDetachedFromRecyclerView(recyclerView);
//        mRecyclerView = null;
//    }

    @Override
    public int getItemCount() {
        return (items == null) ? 0 : items.length;
    }

    public class InnerListVH extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private int innerIndex = -1;

        public InnerListVH(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textview);

//            mTextView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//                @Override
//                public void onScrollChanged() {
//                    if(outerIndex == 8 && innerIndex == 7) {
//                        int[] locOnScreen = new int[2];
//                        mTextView.getLocationOnScreen(locOnScreen);
//
//                        System.out.println("@DEBUG@ " + locOnScreen[0] + ", " + locOnScreen[1]);
//                    }
//                }
//            });
        }

        public void setIndex(int innerIndex){
            this.innerIndex = innerIndex;
        }
    }
}
