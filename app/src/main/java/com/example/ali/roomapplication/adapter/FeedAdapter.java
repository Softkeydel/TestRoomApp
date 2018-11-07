package com.example.ali.roomapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ali.roomapplication.R;
import com.example.ali.roomapplication.databinding.ListRowItemBinding;
import com.example.ali.roomapplication.databinding.ListRowRssBinding;
import com.example.ali.roomapplication.model.Item;

import java.util.List;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private Context mContext;
    private List<Item> itemList;
    private LayoutInflater layoutInflater;


    public FeedAdapter(Context mContext, List<Item> itemList) {
        this.mContext=mContext;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }


        ListRowItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setItem(itemList.get(position));
        holder.binding.tvDateDigit.setText(String.format("%02d", position+1));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListRowItemBinding binding;

        public MyViewHolder(final ListRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

}
