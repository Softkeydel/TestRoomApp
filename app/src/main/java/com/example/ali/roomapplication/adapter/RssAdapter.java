package com.example.ali.roomapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ali.roomapplication.R;
import com.example.ali.roomapplication.databinding.ListRowRssBinding;
import com.example.ali.roomapplication.model.Item;
import com.example.ali.roomapplication.model.Rss;

import java.util.List;



public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyViewHolder> {

    private Context mContext;
    private List<Rss> rssList;
    private LayoutInflater layoutInflater;
    private RssClickListener listener;


//    public RssAdapter(List<Item> itemList, PostsAdapterListener listener) {
//        this.itemList = itemList;
//        this.listener = listener;
//    }

    public RssAdapter(Context mContext, List<Rss> rssList) {
        this.mContext=mContext;
        this.rssList = rssList;
        this.listener= (RssClickListener) mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ListRowRssBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_row_rss, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setRss(rssList.get(position));
        holder.binding.tvCount.setText(String.format("%02d", position+1));

        holder.binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rssList.remove(position);
                notifyItemRangeRemoved(position,1);
                notifyItemRangeChanged(position,rssList.size());
            }
        });

        holder.binding.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(rssList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rssList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListRowRssBinding binding;

        public MyViewHolder(final ListRowRssBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public interface RssClickListener {
        void onItemClicked(Rss rss);
    }
}
